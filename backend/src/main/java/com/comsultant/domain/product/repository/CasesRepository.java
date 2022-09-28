package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CasesRepository extends JpaRepository<Cases, Long>, JpaSpecificationExecutor<Cases> {
    Page<Cases> findAll(Specification<Cases> spec, Pageable pageable);
    Optional<Cases> findByIdx(long idx);
}
