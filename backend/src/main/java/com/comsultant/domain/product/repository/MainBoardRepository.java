package com.comsultant.domain.product.repository;

import com.comsultant.domain.product.entity.MainBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainBoardRepository extends JpaRepository<MainBoard, Long> {
}
