package com.julan.sp3.controller.admin;


import com.julan.sp3.job.CreateDelayRoleJob;
import com.julan.sp3.job.CreateUserJob;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.util.api.ResultJson;

import jakarta.annotation.Resource;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/queue")
public class QueueController {


    @Resource
    private UserRepository userRepository;

    @Resource
    private CreateDelayRoleJob createRoleJob;


    @Resource
    private CreateUserJob createUserJob;


    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() {

//        userRepository.findById(2L).ifPresent(user -> createUserJob.dispatch(user.getId().toString()));

        createRoleJob.dispatch("Role is coming...");
        return ResultJson.success();
    }

}
