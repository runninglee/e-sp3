package com.julan.sp3.service.impl;

import com.julan.sp3.bo.user.CreateUserBo;
import com.julan.sp3.bo.user.UpdateUserBo;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.User;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public User create(CreateUserBo createUserBo) {
        User user = new User();
        BeanUtils.copyProperties(createUserBo, user);
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User update(UpdateUserBo updateUserBo) {
        User user = userRepository.findById(updateUserBo.getId()).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        BeanUtils.copyProperties(updateUserBo, user);
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        userRepository.deleteById(id);
    }

    public User find(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        return user;
    }
}
