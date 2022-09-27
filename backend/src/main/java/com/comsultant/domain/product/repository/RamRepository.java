package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RamRepository extends JpaRepository<Ram, Long> {
    Optional<Ram> findByIdx(long idx);
}
