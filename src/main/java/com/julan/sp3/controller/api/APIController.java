package com.julan.sp3.controller.api;

import com.julan.sp3.annotation.BoolPermission;
import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/t")
public class APIController {

    @Resource
    private UserRepository userRepository;

    @GetMapping
    @ResponseBody
    @BoolPermission("user.list")
    public ResultJson<List<User>> index() {
        return ResultJson.success(userRepository.findUsersByStatus(0));
    }

    @PostMapping
    @ResponseBody
    @DataPermission(value = "user.list", category = "source", id = "{id}")
    public ResultJson<List<User>> store() {
        return ResultJson.success(userRepository.findUsersByStatus(0));
    }
}
