package com.julan.sp3.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column
    private String password;

    @Column
    private String avatar;

    @Column
    private int role_id;

    @Column
    private String keywords;

    @Column
    private int is_admin;

    @Column
    private int status;


    @Column
    private String ip;

    @Column
    private String location;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expired_at;

    @PrePersist
    protected void onCreate() {
        keywords = username + mobile;
        expired_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        keywords = username + mobile;
        expired_at = LocalDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        expired_at = LocalDateTime.now();
    }
}
