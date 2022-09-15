package com.comsultant.domain.comment.entity;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.entity.Product;
import lombok.*;
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
@Setter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_idx", nullable = false)
    private Product product;

    @Column(name = "content", columnDefinition = "VARCHAR(255)", nullable = false)
    private String content;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createDate;

}
