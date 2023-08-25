package com.julan.sp3.pojo.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.pojo.entity.User;
import com.julan.sp3.pojo.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "username", "phone", "status", "created_at", "updated_at"})
public class UserVO implements BaseVO {
    private long id;
    private String username;
    //输出别名
    @JsonProperty("phone")
    private String mobile;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime updated_at;

    //dataList 不为空的时候输出
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> dataList;

    //自定义输出内容
    public String getStatus() {
        if (this.status == 1) {
            return "正常";
        } else {
            return "禁用";
        }
    }
}
