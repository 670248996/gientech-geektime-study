package com.gientech.iot.redisdemo;

import com.gientech.iot.redisdemo.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: redis演示应用程序
 * @author: 王强
 * @dateTime: 2022-11-26 20:36:29
 */
@SpringBootApplication
public class RedisDemoApplication implements ApplicationRunner {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rankList();
        generatorId();
    }

    /**
     * 1. 分数排名
     */
    public void rankList() {
        // 初始化用户分数Map
        Map<String, Integer> userScoreMap = new HashMap<>(10);
        userScoreMap.put("user1", 60);
        userScoreMap.put("user2", 60);
        userScoreMap.put("user3", 65);
        userScoreMap.put("user4", 90);
        userScoreMap.put("user5", 50);
        userScoreMap.put("user6", 80);
        userScoreMap.put("user7", 70);
        // 遍历Map放入redis
        for (Map.Entry<String, Integer> entry : userScoreMap.entrySet()) {
            redisTemplate.opsForZSet().add("userScoreRank", entry.getKey() + ":" + entry.getValue(), entry.getValue().doubleValue());
        }
        // 倒序获取排名（由大到小）
        Set<String> topRank = redisTemplate.opsForZSet().reverseRange("userScoreRank", 0, 2);
        // 正序获取排名（由小到大）
        Set<String> lastRank = redisTemplate.opsForZSet().range("userScoreRank", 0, 2);
        System.out.println("用户分数排名前三名：" + topRank);
        System.out.println("用户分数排名后三名：" + lastRank);
    }

    /**
     * 2. 生成全局id
     */
    public void generatorId() {
        System.out.println(idGenerator.nextId());
    }
}
