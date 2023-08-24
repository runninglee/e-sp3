package com.julan.sp3.controller.admin;

import com.julan.sp3.bo.user.*;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.User;
import com.julan.sp3.service.impl.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResultJson<Page<User>> index(QueryUserBo queryUserBo) {
        return ResultJson.success(userService.getList(queryUserBo));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultJson<User> show(@PathVariable long id) {
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
