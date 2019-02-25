package com.hank.PizzSystem.hank_PizzaSystem;

import com.hank.PizzSystem.hank_PizzaSystem.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        //前一句覆盖后一句
        redisTemplate.opsForValue().set("fiona", "redisTest");
        redisTemplate.opsForValue().set("fiona", "hello");

        Assert.assertEquals("hello", redisTemplate.opsForValue().get("fiona"));
    }

    @Test
    public void testObj() {
        User user = new User("redisTest@126.com", "smile", "youknow", "know", "2020");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.fiona", user);
        User u = operations.get("com.fiona");
        System.out.println("user: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user = new User("redisTest@126.com", "expire", "youknow", "expire", "2020");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 10000, TimeUnit.MILLISECONDS);
        Thread.sleep(10);
        boolean exists = redisTemplate.hasKey("expire");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deletekey", "redisTest");
         redisTemplate.delete("deletekey");
        boolean exists = redisTemplate.hasKey("deletekey");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }
}