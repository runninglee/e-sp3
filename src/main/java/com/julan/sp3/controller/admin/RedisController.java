package com.julan.sp3.controller.admin;

import com.julan.sp3.util.api.ResultJson;
import com.julan.sp3.util.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;


    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() {
        redisUtil.put("Hui", "Hui", 20);
        return ResultJson.success(redisUtil.get("Hui"));
    }

    @GetMapping("cache/{id}")
    @Cacheable(value = "redis", key = "#id", condition = "#id ==1")
    @ResponseBody
    public String cache(@PathVariable String id) {
        return id;
    }


    @PostMapping("cache/{id}")
    @CachePut(value = "redis", key = "#id")
    @ResponseBody
    public String put(@PathVariable String id) {
        return id;
    }

    @DeleteMapping("cache/{id}")
    @CacheEvict(value = "redis", key = "#id")
    @ResponseBody
    public String delete(@PathVariable String id) {
        return id;
    }

    @PutMapping("cache/{id}")
    @Caching(cacheable = @Cacheable(cacheNames = "user", key = "#id"), evict = @CacheEvict(cacheNames = "role", key = "#id"))
    @ResponseBody
    public String caching(@PathVariable String id) {
        return id;
    }


}
