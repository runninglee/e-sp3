package com.julan.sp3.controller.admin;

import com.julan.sp3.pojo.bo.user.*;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.entity.User;
import com.julan.sp3.pojo.vo.user.UserVO;
import com.julan.sp3.service.impl.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @ResponseBody
    public ResultJson<Object> index(QueryUserBo queryUserBo)  {
        return ResultJson.success(userService.getList(queryUserBo));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultJson<Object> show(@PathVariable long id) {
        return ResultJson.success(userService.find(id));
    }

    @PostMapping
    @ResponseBody
    public ResultJson<User> store(@Validated(CreateUserBoGroup.class) CreateUserBo createUserBo) {
        return ResultJson.success(userService.create(createUserBo));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResultJson<User> update(@PathVariable long id, @Validated(UpdateUserBoGroup.class) UpdateUserBo updateUserBo) {
        updateUserBo.setId(id);
        return ResultJson.success(userService.update(updateUserBo));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultJson<Object> destroy(@PathVariable long id) {
        userService.delete(id);
        return ResultJson.success();
    }

    @PostMapping("/t/grace")
    @ResponseBody
    public String grace(@RequestParam Map<String, Object> params) {
        GraceException.display("飒飒" + params.get("name"), 555);
        return params.toString();
    }
}
