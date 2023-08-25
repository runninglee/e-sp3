package com.julan.sp3.service.impl;

import com.julan.sp3.pojo.bo.user.CreateUserBo;
import com.julan.sp3.pojo.bo.user.QueryUserBo;
import com.julan.sp3.pojo.bo.user.UpdateUserBo;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.pojo.entity.User;
import com.julan.sp3.pojo.vo.user.UserVO;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.service.BaseService;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Object> getList(QueryUserBo bo) {
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (bo.getKeywords() != null) {
                Predicate whereKeywords = cb.like(root.get("keywords"), "%" + bo.getKeywords() + "%");
                predicateList.add(whereKeywords);
            }
            if (bo.getStatus() >= 0) {
                Predicate whereStatus = cb.equal(root.get("status"), bo.getStatus());
                predicateList.add(whereStatus);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Pageable pageable = PageRequest.of(bo.getPage(), bo.getPageSize(), Sort.by(Sort.Direction.fromString(bo.getDirection()), bo.getOrder()));
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.stream().map((user) -> modelMapper.map(user, UserVO.class)).collect(Collectors.toList());
    }

    public UserVO find(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            GraceException.display("数据不存在");
        }
        return modelMapper.map(user, UserVO.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User create(CreateUserBo createUserBo) {
        return userRepository.save(modelMapper.map(createUserBo, User.class));
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
