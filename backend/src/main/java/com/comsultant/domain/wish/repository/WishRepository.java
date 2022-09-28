package com.comsultant.domain.wish.repository;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.wish.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

    Page<Wish> findAllByAccount(Account account, Pageable pageable); //페이지네이션
    Wish findByAccountAndProduct(Account account, Product product);
}
