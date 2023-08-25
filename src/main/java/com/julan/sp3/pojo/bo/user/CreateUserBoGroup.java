package com.julan.sp3.pojo.bo.user;

import com.julan.sp3.pojo.bo.user.group.*;
import jakarta.validation.GroupSequence;

@GroupSequence({UsernameGroup.class, MobileGroup.class, PasswordGroup.class, StatusGroup.class, AvatarGroup.class})
public interface CreateUserBoGroup {
}
