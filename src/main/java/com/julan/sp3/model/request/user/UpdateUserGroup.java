package com.julan.sp3.model.request.user;

import com.julan.sp3.model.request.user.group.MobileGroup;
import com.julan.sp3.model.request.user.group.StatusGroup;
import com.julan.sp3.model.request.user.group.UsernameGroup;
import jakarta.validation.GroupSequence;

@GroupSequence({UsernameGroup.class, MobileGroup.class, StatusGroup.class})
public interface UpdateUserGroup {
}
