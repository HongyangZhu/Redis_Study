package com.zhuhy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuhy.pojo.User;
import com.zhuhy.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        // opsForValue 操作字符串 类似String
        // opsForList() 操作List
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog
        redisTemplate.opsForValue().set("key1", "value1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

    @Test
    public void Test01() throws JsonProcessingException {
        User user = new User("中文", 3);
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.getConnectionFactory().getConnection().flushDb();
        redisTemplate.opsForValue().set("user", jsonUser);
        System.out.println(redisTemplate.opsForValue().get("user"));

    }

    @Test
    public void Test02() {
        redisUtil.set("name", "zhuhy", 30);
        redisUtil.set("YYYY", "123456");
        redisUtil.del("name");
    }
}
