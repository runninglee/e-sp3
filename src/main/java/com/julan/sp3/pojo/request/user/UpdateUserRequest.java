package com.julan.sp3.pojo.request.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julan.sp3.pojo.request.user.group.MobileGroup;
import com.julan.sp3.pojo.request.user.group.StatusGroup;
import com.julan.sp3.pojo.request.user.group.UsernameGroup;
import com.julan.sp3.util.validator.Mobile.Mobile;
import jakarta.persistence.Id;
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
@JsonPropertyOrder(value = {"username", "password", "mobile", "status"})
public class UpdateUserRequest {

    @Id
    private long id;

    @NotBlank(message = "用户名不能为空", groups = UsernameGroup.class)
    private String username;

    @NotBlank(message = "手机号码不能为空", groups = MobileGroup.class)
    @Size(min = 11, max = 11, message = "手机号码长度不正确", groups = MobileGroup.class)
    @Mobile(groups = MobileGroup.class)
    private String mobile;

    @NotNull(message = "用户状态不能为空", groups = StatusGroup.class)
    private int status;

    private String avatar;

    private int is_admin;

    private String password;

    private String ip;

    private int role_id;

    private String location;

    private LocalDateTime updated_at;
}





