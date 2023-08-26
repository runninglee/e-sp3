package com.julan.sp3.service.impl;


import com.julan.sp3.exception.GraceException;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.model.request.user.CreateUserRequest;
import com.julan.sp3.model.request.user.UpdateUserRequest;
import com.julan.sp3.model.request.user.UserQuery;
import com.julan.sp3.model.vo.user.UserVO;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.service.BaseService;
import com.julan.sp3.util.page.PageUtil;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements BaseService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Object getList(UserQuery userQuery) {
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (userQuery.getKeywords() != null) {
                Predicate whereKeywords = cb.like(root.get("keywords"), "%" + userQuery.getKeywords() + "%");
                predicateList.add(whereKeywords);
            }
            if (userQuery.getStatus() >= 0) {
                Predicate whereStatus = cb.equal(root.get("status"), userQuery.getStatus());
                predicateList.add(whereStatus);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Pageable pageable = PageRequest.of(userQuery.getPage() - 1, userQuery.getPageSize(), Sort.by(Sort.Direction.fromString(userQuery.getDirection()), userQuery.getOrder()));
        return PageUtil.pretty(userRepository.findAll(spec, pageable), UserVO.class);
    }

    //    @Cacheable(value = "user", key = "#id")
    public UserVO find(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        return modelMapper.map(user, UserVO.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User create(CreateUserRequest createRequest) {
        return userRepository.save(modelMapper.map(createRequest, User.class));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User update(UpdateUserRequest updateRequest) {
        User user = userRepository.findById(updateRequest.getId()).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        modelMapper.map(updateRequest, user);
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
