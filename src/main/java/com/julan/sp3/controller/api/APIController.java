package com.julan.sp3.controller.api;

import com.julan.sp3.annotation.BoolPermission;
import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.util.api.ResultJson;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/t")
public class APIController {

    @GetMapping
    @ResponseBody
    @BoolPermission("user.create")
    public ResultJson<Object> index(@RequestParam Map<String, Object> params) {
        return ResultJson.success("BOOLEAN PERMISSION"+params.toString());
    }

    @PostMapping
    @ResponseBody
    @DataPermission(value = "user.list")
    public ResultJson<Object> store() {
        return ResultJson.success("DATA PERMISSION");
    }
}
