package com.julan.sp3.repository.user;


import com.julan.sp3.pojo.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> hasKeywords(String keywords) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("keywords"), "%" + keywords + "%");
    }

    public static Specification<User> hasStatus(int status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);

    }

//    public static Specification<BaseEntity> hasProductInCategory(String category) {
//        return (root, query, criteriaBuilder) -> {
//            Join<BaseEntity, User> productJoin = root.join("products", JoinType.INNER);
//            return criteriaBuilder.equal(productJoin.get("category"), category);
//        };
//    }
//
//    public static Specification<BaseEntity> hasProductPriceGreaterThan(double price) {
//        return (root, query, criteriaBuilder) -> {
//            Join<BaseEntity, User> productJoin = root.join("products", JoinType.INNER);
//            return criteriaBuilder.greaterThan(productJoin.get("price"), price);
//        };
//    }

}
