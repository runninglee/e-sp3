package com.julan.sp3.service.impl.user;

import com.julan.sp3.event.CreateUserEvent;
import com.julan.sp3.exception.GraceException;
import com.julan.sp3.model.entity.User;
import com.julan.sp3.model.request.user.CreateUserRequest;
import com.julan.sp3.model.request.user.UpdateUserRequest;
import com.julan.sp3.model.request.user.UserQuery;
import com.julan.sp3.model.vo.user.UserVO;
import com.julan.sp3.repository.user.UserRepository;
import com.julan.sp3.service.BaseService;
import com.julan.sp3.util.api.ResultJson;
import com.julan.sp3.util.page.PageUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements BaseService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ApplicationContext applicationContext;


    @PersistenceContext
    EntityManager entityManager;


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
        User user = userRepository.save(modelMapper.map(createRequest, User.class));
        applicationContext.publishEvent(new CreateUserEvent(this, user));
        return user;
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

    //获取布尔权限
    public boolean hasHandlePermission(String permission) {
        return permission.equals("user.create");
    }

    //获取数据权限
    public boolean hasDataPermission(String permission, String entity) {

        Query query = entityManager.createNativeQuery("select * FROM user");
        System.out.println(query.getFirstResult());

        return permission.equals("user.list");
    }

}
