package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.comment.repository.CommentRepository;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Override
    public boolean createComment(Long accountIdx, Long productIdx, CommentDto commentDto) {
        // TODO : account, product 못 찾을 시 예외 처리 필요
        Account account = accountRepository.findById(accountIdx).orElseThrow();
        Product product = productRepository.findById(productIdx).orElseThrow();
        Comment savedComment = commentRepository.save(commentToEntity(account, product, commentDto));
        if (savedComment.getIdx() == 0)
            return false;
        return true;
    }

    @Override
    public CommentResponse.GetComments getComments(Long accountIdx, int page, boolean desc) {
        // TODO : account, product 못 찾을 시 예외 처리 필요, 페이지 음수일 때 예외 처리 필요
        Account account = accountRepository.findById(accountIdx).orElseThrow();
        Pageable pageable;

        if (desc)
            pageable = PageRequest.of(page, 10, Sort.by("idx").descending());
        else
            pageable = PageRequest.of(page, 10);

        Page<Comment> pageComments = commentRepository.findAllByAccount(account, pageable);
        List<Comment> comments = pageComments.getContent();
        int totalPages = pageComments.getTotalPages();
        // TODO : 제품 정보 넣기
        return CommentResponse.GetComments.build(comments, totalPages);
    }

    @Override
    public boolean updateComment(Long accountIdx, Long commentIdx, CommentDto commentDto) {
        // TODO : account, comment 못 찾을 시 예외 처리 필요
        Account account = accountRepository.findById(accountIdx).orElseThrow();
        Comment comment = commentRepository.findById(commentIdx).orElseThrow();
        // TODO : 작성자와 다른 경우 예외 처리 필요
        if (!comment.getAccount().equals(account))
            return false;

        comment.updateInfo(commentDto.getContent());
        Comment savedComment = commentRepository.save(comment);
        if (savedComment.getIdx() == 0)
            return false;
        return true;
    }

    @Override
    public boolean deleteComment(Long accountIdx, Long commentIdx) {
        // TODO : account, comment 못 찾을 시 예외 처리 필요
        Account account = accountRepository.findById(accountIdx).orElseThrow();
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
