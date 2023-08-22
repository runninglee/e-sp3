package com.julan.sp3.bo.user;

import lombok.Data;

@Data
public class CreateUserBo {

    private String username;

    private String password;

    private String mobile;

    private boolean status;
}
