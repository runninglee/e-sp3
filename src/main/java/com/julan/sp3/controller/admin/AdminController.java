package com.julan.sp3.controller.admin;

import com.julan.sp3.model.entity.User;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.util.api.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/t")
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @ResponseBody
    public ResultJson<List<User>> index() {
        return ResultJson.success(userRepository.findUsersByStatus(0));
    }

    @GetMapping("/api")
    @ResponseBody
    public ResultJson<Object> api() {
        return ResultJson.success();
    }

}
