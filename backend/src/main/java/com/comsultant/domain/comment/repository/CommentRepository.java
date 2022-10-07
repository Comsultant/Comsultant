package com.comsultant.domain.comment.repository;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

   Page<Comment> findAllByAccount(Account account, Pageable pageable);
   Page<Comment> findAllByProduct(Product product, Pageable pageable);
}
