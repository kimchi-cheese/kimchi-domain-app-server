package com.kimcheese.kimchidomainappserver.core.redis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class RedisService {

    @Value("${spring.data.redis.awaitCount}")
    private int awaitCount;

    @Value("${spring.data.redis.awaitMs}")
    private Long awaitMs;

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Async
    public CompletableFuture<Object> getValueFromRedis(String key) {
        Object value = hashOperations.entries("people:5"); // Redis에서 값을 가져오는 작업
        hashOperations.put("people:dragosn","lemon","engel");
        return CompletableFuture.completedFuture("string");
    }

    @Async
    public CompletableFuture<Map<String,String>> getGenAiImage(String id) throws Exception{

        for(int i=0; i<awaitCount; i++){
            Thread.sleep(awaitMs);
            Map<String,String> data = hashOperations.entries(id);
            System.out.println(id+":"+data);

            if (!data.isEmpty()){
                redisTemplate.delete(id);
                return CompletableFuture.completedFuture(data);
            }

        }
        return CompletableFuture.completedFuture(null);

    }
}
