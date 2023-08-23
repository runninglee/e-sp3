package com.julan.sp3.bo.user;

import com.julan.sp3.bo.user.group.*;
import jakarta.validation.GroupSequence;

@GroupSequence({UsernameGroup.class, MobileGroup.class, PasswordGroup.class, StatusGroup.class, AvatarGroup.class})
public interface CreateUserBoGroup {
}
