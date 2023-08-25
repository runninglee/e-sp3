package com.julan.sp3.model.request.user;

import com.julan.sp3.model.request.user.group.*;
import jakarta.validation.GroupSequence;

@GroupSequence({UsernameGroup.class, MobileGroup.class, PasswordGroup.class, StatusGroup.class, AvatarGroup.class})
public interface CreateUserGroup {
}
