package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RamRepository extends JpaRepository<Ram, Long>, JpaSpecificationExecutor<Ram> {
    Page<Ram> findAll(Specification<Ram> spec, Pageable pageable);
    Optional<Ram> findByIdx(long idx);
    @Query("select distinct c.corp from Ram c")
    List<String> findDistinctCorp();
    @Query("select distinct c.useDevice from Ram c")
    List<String> findDistinctUseDevice();
    @Query("select distinct c.type from Ram c")
    List<String> findDistinctType();
    @Query("select distinct c.memoryVolume from Ram c")
    List<Double> findDistinctMemoryVolume();
}
