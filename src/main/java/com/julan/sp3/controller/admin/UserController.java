package com.julan.sp3.controller.admin;

import com.julan.sp3.bo.user.CreateUserBo;
import com.julan.sp3.util.api.ResultJson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    @GetMapping
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "详情" + id;
    }

    @PostMapping
    @ResponseBody
    public String store() {
        return "store";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String update(@PathVariable long id) {
        return "更新:" + id;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        return "更新:" + id;
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
        return id.toString() + params.toString();
    }

    @PostMapping("/t/save")
    @ResponseBody
    public ResultJson<Object> t(@Validated CreateUserBo createUserBo) {
//                GraceException.display("飒飒",555);
        return ResultJson.success(createUserBo);
    }
}
