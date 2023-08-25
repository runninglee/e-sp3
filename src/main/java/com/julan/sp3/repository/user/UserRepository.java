package com.julan.sp3.repository.user;

import com.julan.sp3.pojo.entity.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    //from是实例，不是表名
    @Query(value = "select u from User u where u.status =?1")
    List<User> findUsersByStatus(int status);

}
