package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Vga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VgaRepository extends JpaRepository<Vga, Long>, JpaSpecificationExecutor<Vga> {
    Page<Vga> findAll(Specification<Vga> spec, Pageable pageable);
    Optional<Vga> findByIdx(long idx);
    @Query("select distinct c.corp from Vga c")
    List<String> findDistinctCorp();
    @Query("select distinct c.chipsetCorp from Vga c")
    List<String> findDistinctChipsetCorp();
    @Query("select distinct c.nvidia from Vga c")
    List<String> findDistinctNvidia();
    @Query("select distinct c.amd from Vga c")
    List<String> findDistinctAmd();
    @Query("select distinct c.memoryVolume from Vga c")
    List<Double> findDistinctMemoryVolume();

}
