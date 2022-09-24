package com.comsultant.domain.product.entity;

import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Product {

    @Id //기본키
    @Column(name = "idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private long idx;

    // ReadOnly
    @Column(name="category", insertable = false, updatable = false)
    protected int category;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "img_cnt", columnDefinition = "INT")
    private int imgCnt;

    protected Product(int category, String name, int imgCnt) {
        this.category = category;
        this.imgCnt = imgCnt;
        this.name = name;
    }
}
