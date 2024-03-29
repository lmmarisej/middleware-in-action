package com.debug.middleware.server.service.lock;/**
 * Created by Administrator on 2019/4/20.
 */

import com.debug.middleware.model.entity.UserReg;
import com.debug.middleware.model.mapper.UserRegMapper;
import com.debug.middleware.server.controller.lock.dto.UserRegDto;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 处理用户注册信息提交服务Service
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/20 20:24
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Service
public class UserRegService {
    //定义日志实例
    private static final Logger log = LoggerFactory.getLogger(UserRegService.class);
    //定义用户注册Mapper操作接口实例
    @Autowired
    private UserRegMapper userRegMapper;
    //定义Redis的操作组件实例
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 处理用户提交注册的请求-不加分布式锁
     */
    public void userRegNoLock(UserRegDto dto) throws Exception {
        UserReg reg = userRegMapper.selectByUserName(dto.getUserName());        //根据用户名查询用户实体信息
        if (reg == null) {
            log.info("---不加分布式锁---,当前用户名为：{} ", dto.getUserName());
            UserReg entity = new UserReg();
            BeanUtils.copyProperties(dto, entity);
            entity.setCreateTime(new Date());
            userRegMapper.insertSelective(entity);
        } else {
            throw new Exception("用户信息已经存在!");
        }
    }


    /**
     * 处理用户提交注册的请求-加分布式锁
     */
    public void userRegWithLock(UserRegDto dto) throws Exception {
        final String key = dto.getUserName() + "-lock";             //精心设计并构造SETNX操作中的Key
        final String value = System.nanoTime() + "" + UUID.randomUUID();
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        Boolean res = valueOperations.setIfAbsent(key, value);      //调用SETNX操作获取锁，如果返回true，则获取锁成功
        if (res) {
            stringRedisTemplate.expire(key, 20L, TimeUnit.SECONDS);     //为了防止出现死锁的状况，加上EXPIRE操作，即Key的过期时间,在这里设置为20s
            try {
                UserReg reg = userRegMapper.selectByUserName(dto.getUserName());
                if (reg == null) {
                    log.info("---加了分布式锁---,当前用户名为：{} ", dto.getUserName());
                    UserReg entity = new UserReg();         // 创建用户注册实体信息
                    BeanUtils.copyProperties(dto, entity);  // 将提交的用户注册请求实体信息中对应的字段取值 复制到新创建的用户注册实体的相应字段中
                    entity.setCreateTime(new Date());       // 设置注册时间
                    userRegMapper.insertSelective(entity);  // 插入用户注册信息
                } else {
                    throw new Exception("用户信息已经存在!");   //如果用户名已被注册，则抛出异常
                }
            } finally {
                if (value.equals(valueOperations.get(key).toString())) {    //不管发生任何情况，都需要在redis加锁成功并访问操作完共享资源后释放锁
                    stringRedisTemplate.delete(key);
                }
            }
        }
    }


    //定义ZooKeeper客户端CuratorFramework实例
    @Autowired
    private CuratorFramework client;
    //ZooKeeper分布式锁的实现原理是由ZNode节点的创建与删除跟监听机制构成的
    //而ZNoe节点将对应一个具体的路径-跟Unix文件夹路径类似-需要以 / 开头
    private static final String pathPrefix = "/middleware/zkLock/";

    /**
     * 处理用户提交注册的请求-加ZooKeeper分布式锁
     */
    public void userRegWithZKLock(UserRegDto dto) throws Exception {
        //创建ZooKeeper互斥锁组件实例，需要将监控用的客户端实例、精心构造的共享资源 作为构造参数
        InterProcessMutex mutex = new InterProcessMutex(client, pathPrefix + dto.getUserName() + "-lock");
        try {
            //采用互斥锁组件尝试获取分布式锁-其中尝试的最大时间在这里设置为10s
            //当然,具体的情况需要根据实际的业务而定
            if (mutex.acquire(10L, TimeUnit.SECONDS)) {
                //根据用户名查询用户实体信息
                UserReg reg = userRegMapper.selectByUserName(dto.getUserName());
                //如果当前用户名还未被注册，则将当前用户信息注册入数据库中
                if (reg == null) {
                    log.info("---加了ZooKeeper分布式锁---,当前用户名为：{} ", dto.getUserName());
                    //创建用户注册实体信息
                    UserReg entity = new UserReg();
                    //将提交的用户注册请求实体信息中对应的字段取值
                    //复制到新创建的用户注册实体的相应字段中
                    BeanUtils.copyProperties(dto, entity);
                    //设置注册时间
                    entity.setCreateTime(new Date());
                    //插入用户注册信息
                    userRegMapper.insertSelective(entity);
                } else {
                    //如果用户名已被注册，则抛出异常
                    throw new Exception("用户信息已经存在!");
                }
            } else {
                throw new RuntimeException("获取ZooKeeper分布式锁失败!");
            }
        } finally {
            mutex.release();
        }
    }


    //定义redisson的客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 处理用户提交注册的请求-加Redisson分布式锁
     *
     * @param dto a {@link com.debug.middleware.server.controller.lock.dto.UserRegDto} object.
     * @throws java.lang.Exception if any.
     */
    public void userRegRedisson(UserRegDto dto) throws Exception {
        //定义锁的名称
        final String lockName = "redissonOneLock-" + dto.getUserName();
        //获取分布式锁实例
        RLock lock = redissonClient.getLock(lockName);
        try {
            //操作共享资源之前上锁
            //在这里可以通过lock.lock()方法也可以通过调用如下的方法,
            //即上锁之后，不管何种状况，10s后会自动释放
            lock.lock(10, TimeUnit.SECONDS);

            //根据用户名查询用户实体信息
            UserReg reg = userRegMapper.selectByUserName(dto.getUserName());
            //如果当前用户名还未被注册，则将当前用户信息注册入数据库中
            if (reg == null) {
                log.info("---加了Redisson分布式锁之一次性锁---,当前用户名为：{} ", dto.getUserName());
                //创建用户注册实体信息
                UserReg entity = new UserReg();
                //将提交的用户注册请求实体信息中对应的字段取值
                //复制到新创建的用户注册实体的相应字段中
                BeanUtils.copyProperties(dto, entity);
                //设置注册时间
                entity.setCreateTime(new Date());
                //插入用户注册信息
                userRegMapper.insertSelective(entity);
            } else {
                //如果用户名已被注册，则抛出异常
                throw new Exception("用户信息已经存在!");
            }
        } catch (Exception e) {
            log.error("---获取Redisson的分布式锁失败!---");
            throw e;
        } finally {
            if (lock != null) {
                lock.unlock();

                //在某些严格的业务场景下，也可以调用强制释放分布式锁的方法
                //lock.forceUnlock();
            }
        }
    }


}























