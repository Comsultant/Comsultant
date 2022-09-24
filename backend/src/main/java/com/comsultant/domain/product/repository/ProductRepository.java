package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {
    Optional<Product> findByIdx(long idx);
}
