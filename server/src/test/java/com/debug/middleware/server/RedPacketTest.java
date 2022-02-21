package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/23.
 */

import com.debug.middleware.server.utils.RedPacketUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/23 16:57
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedPacketTest {

    private static final Logger log = LoggerFactory.getLogger(RedisTest2.class);

    //二倍均值法自测
    @Test
    @Repeat(10)
    public void one() {
        //总金额单位为分
        int amout = new Random().nextInt(1000) + 1000;
        //总人数-红包个数
        int total = new Random().nextInt(10) + 10;
        //得到随机金额列表
        List<Integer> list = RedPacketUtil.divideRedPackage(amout, total);

        //用于统计生成的随机金额之和是否等于总金额
        int sum = 0;
        //遍历输出每个随机金额
        for (int i : list) {
            log.info("随机金额为：{}分，即 {}元", i, new BigDecimal(i).divide(new BigDecimal(100)));
            sum += i;
        }
        log.info("红包生成总额：{}分，红包分配总额：{}分", amout, sum);
        Assert.assertEquals("红包未被完全分配", sum, amout);
    }

    @Test(expected = IllegalArgumentException.class)
    public void two() {
        RedPacketUtil.divideRedPackage(90, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void three() {
        RedPacketUtil.divideRedPackage(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void four() {
        RedPacketUtil.divideRedPackage(90, 0);
    }
}


























