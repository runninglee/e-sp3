package com.julan.sp3.controller.admin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.julan.sp3.job.CreateRoleJob;
import com.julan.sp3.job.CreateUserJob;
import com.julan.sp3.model.entity.User;
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
    private CreateRoleJob createRoleJob;


    @Resource
    private CreateUserJob createUserJob;


    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() throws JsonProcessingException {

        User user = userRepository.findById(2L).orElse(null);

        if(user != null){
            createUserJob.dispatch(user);
        }



        createRoleJob.dispatch("Role is coming...");
        return ResultJson.success();
    }

}
