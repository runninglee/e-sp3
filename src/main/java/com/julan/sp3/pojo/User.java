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

    private String password;

    private String avatar;

    private int role_id;

    private String keywords;

    @Column(name = "is_admin")
    private boolean is_admin;

    @Column(name = "status")
    private boolean status;

    @Column(name = "expired_at")
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
