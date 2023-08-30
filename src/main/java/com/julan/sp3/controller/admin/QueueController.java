package com.julan.sp3.controller.admin;


import com.julan.sp3.job.DelayUserJob;
import com.julan.sp3.job.SyncUserJob;
import com.julan.sp3.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/queue")
public class QueueController {

    @Resource
    private DelayUserJob delayUserJob;
    
    @Resource
    private SyncUserJob syncUserJob;

    @GetMapping
    @ResponseBody
    public ResultJson<Object> index() {
        delayUserJob.dispatch("Role is coming...", 5);
        return ResultJson.success();
    }

    @PostMapping
    @ResponseBody
    public ResultJson<Object> store() {
        syncUserJob.dispatch("这是推送同步队列消息");
        return ResultJson.success();
    }
}
