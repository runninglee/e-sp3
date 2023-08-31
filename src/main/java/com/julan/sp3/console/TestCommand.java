package com.julan.sp3.console;

import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

@ShellComponent
@ShellCommandGroup("hotfix:")
public class TestCommand {
    
    @Resource
    JdbcTemplate jdbcTemplate;

    // value是命令描述，key是命令
    // 调用方式 show 39 18937166730
    // 调用方式 add --id 39 --mobile 18937166730
    // key默认是方法名，prefix默认前缀是--
    @ShellMethod(value = "查询指定ID和手机号的用户", key = "show")
    public Map<String, Object> show(@ShellOption(defaultValue = "39") String id, @ShellOption(defaultValue = "13554864077") String mobile) {
        return jdbcTemplate.queryForMap("Select * from user where id=? or mobile=? limit 1", id, mobile);
    }

    @ShellMethod(value = "查看当前的系统时间")
    public String date() {
        return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
