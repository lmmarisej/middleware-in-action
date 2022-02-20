package com.debug.middleware.server.service.redis;

import com.debug.middleware.model.entity.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

import static com.debug.middleware.server.service.redis.CachePassService.keyPrefix;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/20 5:58 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CachePassServiceTest {
    private static final Logger log = LoggerFactory.getLogger(CachePassServiceTest.class);

    @Resource
    private CachePassService cachePassService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    private Item item;

    @Before
    public void setUp() {
        item = new Item();
        item.setCode("book_cxk_00001");
        item.setName("《iKun日记》");
        item.setCreateTime(new Date());
    }

    @SneakyThrows
    @Test
    public void validateInsertedItemCached() {
        cachePassService.addItem(item);     // 向数据库插入后，会被Redis缓存
        String cacheItemInfo = redisTemplate.opsForValue().get(keyPrefix + item.getCode());
        Assert.assertEquals(objectMapper.readValue(cacheItemInfo, Item.class).getCode(), item.getCode());
    }

    @Test
    public void forbidCachePenetrate() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Item itemInfo = cachePassService.getItemInfo(uuid);     // 必定不存在，将会命中一个避免缓存穿透的空对象
        Assert.assertTrue(itemInfo.getId() == null || itemInfo.getId() == 0);
        Assert.assertTrue(itemInfo.getCode() == null || "".equals(itemInfo.getCode()));
        Assert.assertTrue(itemInfo.getName() == null || "".equals(itemInfo.getName()));
    }

    @After
    public void unload() {
        cachePassService.deleteItem(item.getCode());        // item被删除后，缓存一并删除
        Assert.assertNull(redisTemplate.opsForValue().get(keyPrefix + item.getCode()));        // 验证缓存被删除
    }
}
