package com.julan.sp3.controller.admin;

import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/redis")
public class RedisController {


    @Resource
    RedisTemplate<String, String> redisTemplate;

    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() {
        redisTemplate.opsForValue().set("Hui", "Lee");
        return ResultJson.success(redisTemplate.opsForValue().get("Hui"));
    }
}
