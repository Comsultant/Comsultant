package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CasesRepository extends JpaRepository<Cases, Long>, JpaSpecificationExecutor<Cases> {
    Page<Cases> findAll(Specification<Cases> spec, Pageable pageable);
    Optional<Cases> findByIdx(long idx);
    @Query("select distinct c.corp from Cases c")
    List<String> findDistinctCorp();
    @Query("select distinct c.classType from Cases c")
    List<String> findDistinctClassType();
    @Query("select distinct c.size from Cases c")
    List<String> findDistinctSize();
    @Query("select distinct c.powerSize from Cases c")
    List<String> findDistinctPowerSize();
}
