package com.comsultant.domain.builder.repository;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.entity.MyBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBuilderRepository extends JpaRepository<MyBuilder, Long> {

    List<MyBuilder> findAllByAccount(Account account);

    Page<MyBuilder> findAllByAccount(Account account, Pageable pageable);
}
