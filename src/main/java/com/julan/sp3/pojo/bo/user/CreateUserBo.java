package com.julan.sp3.pojo.bo.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.pojo.bo.user.group.*;
import com.julan.sp3.util.validator.Mobile.Mobile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"username", "password", "status", "mobile"})
public class CreateUserBo {

    @NotBlank(message = "用户名不能为空", groups = UsernameGroup.class)
    private String username;

    @NotBlank(message = "用户密码不能为空", groups = PasswordGroup.class)
    private String password;

    @NotBlank(message = "手机号码不能为空", groups = MobileGroup.class)
    @Size(min = 11, max = 11, message = "手机号码长度不正确", groups = MobileGroup.class)
    @Mobile(groups = MobileGroup.class)
    private String mobile;

//    @NotBlank(message = "身份证号不能为空", groups = AvatarGroup.class)
//    @IdCard(groups = AvatarGroup.class)
//    private String avatar;

    @NotNull(message = "用户状态不能为空", groups = StatusGroup.class)
    private int status;

    private String avatar;

    private int is_admin;

    private String ip;

    private int role_id;

    private String location;

    private LocalDateTime created_at;


}





