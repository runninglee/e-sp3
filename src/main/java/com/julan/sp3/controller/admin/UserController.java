package com.julan.sp3.controller.admin;

import com.julan.sp3.pojo.request.user.*;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.entity.User;
import com.julan.sp3.service.impl.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultJson<Object> index(UserQuery userQuery)  {
        return ResultJson.success(userService.getList(userQuery));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultJson<Object> show(@PathVariable long id) {
        return ResultJson.success(userService.find(id));
    }

    @PostMapping
    @ResponseBody
    public ResultJson<User> store(@Validated(CreateUserGroup.class) CreateUserRequest createRequest) {
        return ResultJson.success(userService.create(createRequest));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResultJson<User> update(@PathVariable long id, @Validated(UpdateUserGroup.class) UpdateUserRequest updateRequest) {
        updateRequest.setId(id);
        return ResultJson.success(userService.update(updateRequest));
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
