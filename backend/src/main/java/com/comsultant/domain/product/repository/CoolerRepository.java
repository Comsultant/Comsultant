package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoolerRepository extends JpaRepository<Cooler, Long> {
    Optional<Cooler> findByIdx(long idx);
}
