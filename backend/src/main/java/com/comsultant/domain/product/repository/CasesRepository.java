package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CasesRepository extends JpaRepository<Cases, Long> {
    @Override
    Optional<Cases> findById(Long aLong);
}
