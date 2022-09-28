package com.comsultant.domain.builder.entity;

import com.comsultant.domain.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
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
public class MyBuilder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createDate;

    public void modifyName(String name) {
        this.name = name;
    }
}
