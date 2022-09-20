package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CpuRepository extends JpaRepository<Cpu, Long> {
}
