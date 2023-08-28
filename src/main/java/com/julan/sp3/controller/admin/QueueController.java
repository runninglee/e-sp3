package com.julan.sp3.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.model.vo.user.UserVO;
import com.julan.sp3.service.impl.user.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;

import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/queue")
public class QueueController {


    @Resource
    private AmqpTemplate amqpTemplate;


    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() {
        amqpTemplate.convertAndSend("topic", "disses");
        return ResultJson.success();
    }

}
