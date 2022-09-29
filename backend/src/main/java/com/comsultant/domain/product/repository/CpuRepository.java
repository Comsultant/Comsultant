package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CpuRepository extends JpaRepository<Cpu, Long>, JpaSpecificationExecutor<Cpu> {
    Page<Cpu> findAll(Specification<Cpu> spec, Pageable pageable); //페이지네이션
    Optional<Cpu> findByIdx(long idx);

    @Query("select distinct c.corp from Cpu c")
    List<String> findDistinctCorp();
    @Query("select distinct c.intelCpu from Cpu c")
    List<String> findDistinctIntelCpu();
    @Query("select distinct c.amdCpu from Cpu c")
    List<String> findDistinctAmdCpu();
    @Query("select distinct c.socket from Cpu c")
    List<String> findDistinctSocket();
    @Query("select distinct c.core from Cpu c")
    List<String> findDistinctCore();
}
