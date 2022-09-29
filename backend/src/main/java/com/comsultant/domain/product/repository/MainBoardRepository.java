package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.MainBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MainBoardRepository extends JpaRepository<MainBoard, Long>, JpaSpecificationExecutor<MainBoard> {
    Page<MainBoard> findAll(Specification<MainBoard> spec, Pageable pageable);
    Optional<MainBoard> findByIdx(long idx);
    @Query("select distinct c.corp from MainBoard c")
    List<String> findDistinctCorp();
    @Query("select distinct c.cpuSocket from MainBoard c")
    List<String> findDistinctCpuSocket();
    @Query("select distinct c.type from MainBoard c")
    List<String> findDistinctType();
    @Query("select distinct c.detailChipset from MainBoard c")
    List<String> findDistinctDetailChipset();
}
