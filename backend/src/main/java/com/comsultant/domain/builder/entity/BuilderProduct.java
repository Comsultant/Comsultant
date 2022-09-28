package com.comsultant.domain.builder.entity;

import com.comsultant.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "builder_product")
public class BuilderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    @ManyToOne(targetEntity = MyBuilder.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "my_builder_idx", nullable = false)
    private MyBuilder myBuilder;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_idx", nullable = false)
    private Product product;

    @Column(name = "cnt", columnDefinition = "INT")
    private int cnt;
}
