package com.julan.sp3.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.pojo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonPropertyOrder(value = {"username", "id"})
public class UserListVO {
    private long id;
    private String username;
    //输出别名
    @JsonProperty("phone")
    private String mobile;
    private Boolean status;
    //自定义数据格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated_at;
    //dataList 不为空的时候输出
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> dataList;

    //自定义输出内容
    public String getStatus() {
        if (this.status) {
            return "正常";
        } else {
            return "禁用";
        }
    }
}
