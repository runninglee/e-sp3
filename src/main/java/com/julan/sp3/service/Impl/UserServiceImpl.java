package com.julan.sp3.service.impl;

import com.julan.sp3.bo.user.CreateUserBo;
import com.julan.sp3.bo.user.QueryUserBo;
import com.julan.sp3.bo.user.UpdateUserBo;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.User;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.repository.user.UserSpecifications;
import com.julan.sp3.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements BaseService {


    @Autowired
    private UserRepository userRepository;

    public Page<User> getList(QueryUserBo queryUserBo) {

//        Specification<User> spec = (root, query, criteriaBuilder) -> {
//
//            if(queryUserBo.getKeywords() != null){
//                criteriaBuilder.like(root.get("keywords"), "%" + queryUserBo.getKeywords() + "%");
//            }
//
//            if(queryUserBo.getStatus() >=0){
//                criteriaBuilder.equal(root.get("status"), queryUserBo.getStatus());
//            }
//            return criteriaBuilder.like(root.get("keywords"), "%" + queryUserBo.getKeywords() + "%");
//        };


        Specification<User> spec = Specification.where(null);

        spec.and(UserSpecifications.hasKeywords(queryUserBo.getKeywords()));

        spec.and(UserSpecifications.hasStatus(queryUserBo.getStatus()));

        Pageable pageable = PageRequest.of(queryUserBo.getPage(), queryUserBo.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        return userRepository.findAll(spec, pageable);
    }

    public User find(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        return user;
    }

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
}
