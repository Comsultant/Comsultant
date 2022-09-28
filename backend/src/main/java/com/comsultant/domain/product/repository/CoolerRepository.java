package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cooler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CoolerRepository extends JpaRepository<Cooler, Long>, JpaSpecificationExecutor<Cooler> {
    Page<Cooler> findAll(Specification<Cooler> spec, Pageable pageable);
    Optional<Cooler> findByIdx(long idx);
}
