package com.debug.middleware.server.service.redis;

import com.debug.middleware.model.entity.Item;
import com.debug.middleware.model.mapper.ItemMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存穿透service
 * Created by Administrator on 2019/3/17.
 *
 * @author lmmarise.j
 * @version $Id: $Id
 */
@Service
public class CachePassService {

    private static final Logger log = LoggerFactory.getLogger(CachePassService.class);

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /** Constant <code>keyPrefix="item:"</code> */
    public static final String keyPrefix = "item:";

    /**
     * 获取商品详情-如果缓存有，则从缓存中获取；如果没有，则从数据库查询，并将查询结果塞入缓存中
     *
     * @param itemCode a {@link java.lang.String} object.
     * @return a {@link com.debug.middleware.model.entity.Item} object.
     * @throws java.lang.Exception if any.
     */
    public Item getItemInfo(String itemCode) throws Exception {
        Item item;
        final String key = keyPrefix + itemCode;
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // 缓存未命中
        if (!redisTemplate.hasKey(key)) {
            log.info("---获取商品详情-从数据库中查询---商品编号为：{} ", itemCode);
            item = itemMapper.selectByCode(itemCode);
            // 数据库中存在
            if (item != null) {
                valueOperations.set(key, objectMapper.writeValueAsString(item));
            }
            // 数据库中不存在该对象，为缓存设置空对象，避免打到数据库；过期失效时间TTL设置为30分钟-当然实际情况要根据实际业务决定
            else {
                valueOperations.set(key, objectMapper.writeValueAsString(new Item()), 30L, TimeUnit.MINUTES);
            }
        }
        item = objectMapper.readValue(valueOperations.get(key), Item.class);     // 从缓存中查询该商品详情
        return item;
    }

    /**
     * <p>update.</p>
     *
     * @param item a {@link com.debug.middleware.model.entity.Item} object.
     */
    public void update(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
        redisTemplate.expire(keyPrefix + item.getCode(), 0, TimeUnit.MICROSECONDS);
    }

    /**
     * <p>addItem.</p>
     *
     * @param item a {@link com.debug.middleware.model.entity.Item} object.
     */
    public void addItem(Item item) {
        itemMapper.insert(item);
        try {
            redisTemplate.opsForValue().set(keyPrefix + item.getCode(), objectMapper.writeValueAsString(item));
        } catch (JsonProcessingException ignored) {
        }
    }

    /**
     * <p>deleteItem.</p>
     *
     * @param itemCode a {@link java.lang.String} object.
     */
    public void deleteItem(String itemCode)  {
        itemMapper.deleteByCode(itemCode);
        redisTemplate.expire(keyPrefix + itemCode, 0, TimeUnit.MICROSECONDS);
    }
}


























