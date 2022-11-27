package com.gientech.iot.redisdemo.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @description: 生成全局id
 * @author: 王强
 * @dateTime: 2022-11-26 16:43:01
 */
@Component
public class IdGenerator {

    private final StringRedisTemplate stringRedisTemplate;

    public IdGenerator(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * redis生成全局唯一id
     */
    public Long nextId() {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong("GlobalID",
                Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()));
        long incrementAndGet = redisAtomicLong.incrementAndGet();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String prefix = sdf.format(new Date());
        // 设置5位，不够则前面补零
        String orderId = prefix + String.format("%1$05d", incrementAndGet);
        redisAtomicLong.expire(2, TimeUnit.SECONDS);
        return Long.valueOf(orderId);
    }
}
