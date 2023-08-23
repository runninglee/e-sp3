package com.julan.sp3.bo.user;

import com.julan.sp3.bo.user.group.MobileGroup;
import com.julan.sp3.bo.user.group.PasswordGroup;
import com.julan.sp3.bo.user.group.StatusGroup;
import com.julan.sp3.bo.user.group.UsernameGroup;
import jakarta.validation.GroupSequence;

@GroupSequence({UsernameGroup.class, MobileGroup.class, PasswordGroup.class, StatusGroup.class})
public interface CreateUserBoGroup {
}
