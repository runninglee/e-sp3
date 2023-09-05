package com.julan.sp3.controller.api;

import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.annotation.HandlePermission;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.security.InvalidKeyException;

@RestController
@RequestMapping("/api/t")
public class APIController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping
    @ResponseBody
    @HandlePermission("user.create")
    public ResultJson<Object> index(@RequestParam Map<String, Object> params) throws NoSuchAlgorithmException, InvalidKeyException {
//        return ResultJson.success(HashMacUtil.hashMac("3226ff1362636fb2e1ae64ace264baeb","9lqcpao1rr5hdkgwhvb6gs4peqcouvqu"));
//        return ResultJson.success(HashMacUtil.md5("HuiLee"));
    }

    @PostMapping
    @ResponseBody
    @DataPermission(value = "user.list", entity = "User")
    public ResultJson<Object> store(@RequestParam String name) {
        List<Map<String, Object>> users = jdbcTemplate.queryForList("Select * from user where keywords like ?", "%" + name + "%");
        return ResultJson.success(users);
    }
}
