package com.julan.sp3.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julan.sp3.bo.user.CreateUserBo;
import com.julan.sp3.bo.user.CreateUserBoGroup;
import com.julan.sp3.bo.user.UpdateUserBo;
import com.julan.sp3.bo.user.UpdateUserBoGroup;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.User;
import com.julan.sp3.service.Impl.UserServiceImpl;
import com.julan.sp3.util.api.ResultJson;
import com.julan.sp3.vo.user.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/users")
public class UserController extends AdminController {

    @Autowired
    private UserServiceImpl userService;
    private Object UserListVO;

    @GetMapping
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResultJson<User> show(@PathVariable long id) {
        User user = userService.find(id);
        return ResultJson.success(user);
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


    @GetMapping("/t/{id}")
    @ResponseBody
    public String t(@PathVariable String id) {
        return id;
    }

    @PostMapping("/t")
    @ResponseBody
    public String t(@RequestBody Map<String, Object> params) {
        return params.toString();
    }

    @PutMapping("/t/{id}")
    @ResponseBody
    public String t(@PathVariable Long id, @RequestParam Map<String, Object> params) {
        return id.toString();
    }

    @PostMapping("/t/grace")
    @ResponseBody
    public String grace(@RequestParam Map<String, Object> params) {
        GraceException.display("飒飒" + params.toString(), 555);
        return params.toString();
    }

    @PostMapping("/t/save")
    @ResponseBody
    public ResultJson<Object> t(@Validated(CreateUserBoGroup.class) CreateUserBo createUserBo) {
        return ResultJson.success(createUserBo);
    }
}
