package com.julan.sp3.controller.api;

import com.julan.sp3.annotation.DataPermission;
import com.julan.sp3.annotation.HandlePermission;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/t")
public class APIController {

    @PersistenceContext
    EntityManager entityManager;

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping
    @ResponseBody
    @HandlePermission("user.create")
    public ResultJson<Object> index(@RequestParam Map<String, Object> params) {
        return ResultJson.success("BOOLEAN PERMISSION" + params.toString());
    }

    @PostMapping
    @ResponseBody
    @DataPermission(value = "user.list", entity = "User")
    public ResultJson<Object> store() {
//        Query query = entityManager.createNativeQuery("SELECT * FROM User WHERE id!=:id and mobile = :mobile", User.class);
//        query.setParameter("id", 65L).setParameter("mobile", "13554864077");
//        return ResultJson.success(query.getResultList());
        //查单条
//        Map<String, Object> user = jdbcTemplate.queryForMap("Select * from user where id=? and mobile=? limit 1", 39, "13554864077");
        //查多条
        List<Map<String, Object>> users = jdbcTemplate.queryForList("Select * from user where keywords like ?", "%HuiLee%");
        return ResultJson.success(users);
    }
}
