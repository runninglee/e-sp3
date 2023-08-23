package com.julan.sp3.bo.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.bo.user.group.MobileGroup;
import com.julan.sp3.bo.user.group.PasswordGroup;
import com.julan.sp3.bo.user.group.StatusGroup;
import com.julan.sp3.bo.user.group.UsernameGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误", groups = MobileGroup.class)
    private String mobile;

    @NotNull(message = "用户状态不能为空", groups = StatusGroup.class)
    private boolean status;
}





