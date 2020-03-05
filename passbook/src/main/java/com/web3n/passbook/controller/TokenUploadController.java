package com.web3n.passbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @create 2020-03-05 15:46
 * PassTemplate Token Upload
 */
@Slf4j
@Controller
public class TokenUploadController {
    /** redis 客户端  redisTemplate 的 key 会被 spring 包一层 */
    /** StringRedisTemplate 的 key 是实际赋值的 key */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 将 token 写入 redis
     * @param path {@link Path}
     * @param key redis key
     * @return true/false
     */
    private boolean writeTokenToRedis(Path path, String key){
        /** 用 Set 保证不重复 */
        Set<String> tokens;
        try(Stream<String> stream = Files.lines(path)) {
            tokens  = stream.collect(Collectors.toSet());
        } catch (IOException ex){
            return false;
        }
        if(!CollectionUtils.isEmpty(tokens)){
            /** 如果是集群则不支持 Pipelined */
            redisTemplate.executePipelined(
                    (RedisCallback<Object>) connection -> {
                        for (String token : tokens) {
                            connection.sAdd(key.getBytes(), token.getBytes());
                        }
                        return null;
                    }
            );
            return true;
        }
        return false;
    };
}
