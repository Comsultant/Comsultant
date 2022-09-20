package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Psu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsuRepository extends JpaRepository<Psu, Long> {
}
