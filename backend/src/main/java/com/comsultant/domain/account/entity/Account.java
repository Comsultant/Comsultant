package com.comsultant.domain.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "nickname", unique = true, nullable = false, columnDefinition = "VARCHAR(20)")
    private String nickname;

    @Column(name = "password", columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "sns_type", columnDefinition = "INT")
    private int snsType;

    @Column(name = "birth_year", columnDefinition = "INT")
    private int birthYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "VARCHAR(20) DEFAULT 'ROLE_USER'")
    private AccountRole role;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modify_date", columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public void modifyAccount(String nickname, int birthYear) {
        this.nickname = nickname;
        this.birthYear = birthYear;
    }

    public void modifyPassword(String password) {
        this.password = password;
    }
}
