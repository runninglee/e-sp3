package com.julan.sp3.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
public class User {
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录手机号
     */
    private String mobile;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 0 是禁用,1 是启用
     */
    private Boolean status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
