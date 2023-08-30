package com.julan.sp3.controller.api;

import com.julan.sp3.annotation.BoolPermission;
import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.util.api.ResultJson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/t")
public class APIController {


    @GetMapping
    @ResponseBody
    @BoolPermission("user.list")
    public ResultJson<Object> index() {
        return ResultJson.success("BOOLEAN PERMISSION");
    }

    @PostMapping
    @ResponseBody
    @DataPermission(value = "user.list", category = "source", id = "{id}")
    public ResultJson<Object> store() {
        return ResultJson.success("DATA PERMISSION");
    }
}
