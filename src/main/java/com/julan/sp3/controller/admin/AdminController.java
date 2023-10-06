package com.julan.sp3.controller.admin;

import com.julan.sp3.config.CustomConfig;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/t")
public class AdminController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private CustomConfig customConfig;


    @GetMapping
    @ResponseBody
    public ResultJson<List<User>> index() {
        return ResultJson.success(userRepository.findUsersByStatus(0));
    }

    @GetMapping("/api")
    @ResponseBody
    public ResultJson<Object> api() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", customConfig.getName());
        hashMap.put("author", customConfig.getAuthor());
        hashMap.put("users", customConfig.getUsers());
        return ResultJson.success(hashMap);
    }

    @GetMapping("/json")
    @ResponseBody
    public ResultJson<Object> json() {
        return ResultJson.success();
    }

}
