package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Vga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface VgaRepository extends JpaRepository<Vga, Long>, JpaSpecificationExecutor<Vga> {
    Page<Vga> findAll(Specification<Vga> spec, Pageable pageable);
    Optional<Vga> findByIdx(long idx);
}
