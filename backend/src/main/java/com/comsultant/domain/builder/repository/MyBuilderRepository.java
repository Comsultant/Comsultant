package com.comsultant.domain.builder.repository;

import com.comsultant.domain.builder.entity.MyBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBuilderRepository extends JpaRepository<MyBuilder, Long> {
}
