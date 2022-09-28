package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Psu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PsuRepository extends JpaRepository<Psu, Long>, JpaSpecificationExecutor<Psu> {
    Page<Psu> findAll(Specification<Psu> spec, Pageable pageable);
    Optional<Psu> findByIdx(long idx);
}
