package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.comment.repository.CommentRepository;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Override
    public boolean createComment(Long userIdx, Long productIdx, CommentDto commentDto) {
        // TODO : account, product 못 찾을 시 예외 처리 필요
        Account account = accountRepository.findById(userIdx).orElseThrow();
        Product product = productRepository.findById(productIdx).orElseThrow();
        Comment savedComment = commentRepository.save(commentToEntity(account, product, commentDto));
        if (savedComment.getIdx() == 0)
            return false;
        return true;
    }

    @Override
    public boolean deleteComment(Long userIdx, Long commentIdx) {
        // TODO : account, comment 못 찾을 시 예외 처리 필요
        Account account = accountRepository.findById(userIdx).orElseThrow();
        Comment comment = commentRepository.findById(commentIdx).orElseThrow();
        // TODO : 작성자와 다른 경우 예외 처리 필요
        if (!comment.getAccount().equals(account))
            return false;

        commentRepository.deleteById(commentIdx);
        return true;
    }

    public static Comment commentToEntity(Account account, Product product, CommentDto commentDto) {
        return Comment.builder()
                .account(account)
                .product(product)
                .content(commentDto.getContent())
                .build();
    }
}
