package com.comsultant.domain.builder.repository;

import com.comsultant.domain.builder.entity.BuilderProduct;
import com.comsultant.domain.builder.entity.MyBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuilderProductRepository extends JpaRepository<BuilderProduct, Long> {
    List<BuilderProduct> deleteAllByMyBuilder(MyBuilder myBuilder);
}
