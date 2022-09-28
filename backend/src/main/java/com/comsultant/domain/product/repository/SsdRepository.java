package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Ssd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SsdRepository extends JpaRepository<Ssd, Long>, JpaSpecificationExecutor<Ssd> {
    Page<Ssd> findAll(Specification<Ssd> spec, Pageable pageable);
    Optional<Ssd> findByIdx(long idx);
}
