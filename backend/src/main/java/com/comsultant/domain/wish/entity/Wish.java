package com.comsultant.domain.wish.entity;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.entity.Product;
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
@DynamicUpdate //실제 값이 변경된 컬럼으로만 update 쿼리를 만든듦
@DynamicInsert // null이 아닌 컬럼들만 포함하여 insert 쿼리를 만듦
@EntityListeners(AuditingEntityListener.class) // Auditing 기능: db 기록(수정일 등)에 자동으로 시간을 매핑하여 db의 테이블에 넣어줌
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만듦
@Getter
@Builder
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 db에 위임 (mysql: auto_increment)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY) //다대일. 영속성, 지연주입
    @JoinColumn(name = "account_idx", nullable = false) // account의 idx에 join
    private Account account;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_idx", nullable = false)
    private Product product;

    @Column(name = "create_date", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate //생성시간 자동 입력
    private LocalDateTime createDate;
}
