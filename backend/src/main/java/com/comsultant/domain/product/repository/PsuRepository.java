package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Psu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PsuRepository extends JpaRepository<Psu, Long>, JpaSpecificationExecutor<Psu> {
    Page<Psu> findAll(Specification<Psu> spec, Pageable pageable);
    Optional<Psu> findByIdx(long idx);
    @Query("select distinct c.corp from Psu c")
    List<String> findDistinctCorp();
    @Query("select distinct c.type from Psu c")
    List<String> findDistinctType();
    @Query("select distinct c.ratedPower from Psu c")
    List<Integer> findDistinctRatedPower();
}
