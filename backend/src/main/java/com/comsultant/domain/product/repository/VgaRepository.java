package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Vga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VgaRepository extends JpaRepository<Vga, Long> {
    Optional<Vga> findByIdx(long idx);
}
