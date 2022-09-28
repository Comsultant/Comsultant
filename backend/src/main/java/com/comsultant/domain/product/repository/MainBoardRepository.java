package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.MainBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MainBoardRepository extends JpaRepository<MainBoard, Long>, JpaSpecificationExecutor<MainBoard> {
    Page<MainBoard> findAll(Specification<MainBoard> spec, Pageable pageable);
    Optional<MainBoard> findByIdx(long idx);
}
