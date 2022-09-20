package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Ssd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SsdRepository extends JpaRepository<Ssd, Long> {
}
