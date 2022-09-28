package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CpuRepository extends JpaRepository<Cpu, Long>, JpaSpecificationExecutor<Cpu> {
    Page<Cpu> findAll(Specification<Cpu> spec, Pageable pageable); //페이지네이션
    Optional<Cpu> findByIdx(long idx);
}
