package com.julan.sp3.repository.user;

import com.julan.sp3.pojo.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}
