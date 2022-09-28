package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Hdd;
import com.comsultant.domain.product.entity.Vga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HddRepository extends JpaRepository<Hdd, Long>, JpaSpecificationExecutor<Hdd> {
    Page<Hdd> findAll(Specification<Hdd> spec, Pageable pageable);
    Optional<Hdd> findByIdx(long idx);
}
