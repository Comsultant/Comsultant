package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RamRepository extends JpaRepository<Ram, Long>, JpaSpecificationExecutor<Ram> {
    Page<Ram> findAll(Specification<Ram> spec, Pageable pageable);
    Optional<Ram> findByIdx(long idx);
}
