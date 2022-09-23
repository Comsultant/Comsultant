package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.account.repository.AccountRepository;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.comment.mapper.CommentMapper;
import com.comsultant.domain.comment.repository.CommentRepository;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import com.comsultant.global.error.exception.CommentApiException;
import com.comsultant.global.error.model.CommentErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Override
    public boolean createComment(Account account, Long productIdx, CommentDto commentDto) {
        if(account == null || account.getIdx() == 0) {
            return false;
        }

        commentDto.updateUserInfo(account.getIdx(), productIdx);
        if(!productRepository.existsById(productIdx)) {
            throw new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND);
        }

        Comment savedComment = commentRepository.save(CommentMapper.mapper.toEntity(commentDto));
        return savedComment.getIdx() != 0;
    }

    @Override
    public CommentResponse.GetComments getComments(Account account, int page, boolean desc) {
        // TODO : account, product 못 찾을 시 예외 처리 필요, 페이지 음수일 때 예외 처리 필요
        if(account == null || account.getIdx() == 0) {
            return null;
        }
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
    public boolean updateComment(Account account, Long commentIdx, CommentDto commentDto) {
        // TODO : account, comment 못 찾을 시 예외 처리 필요
        if(account == null || account.getIdx() == 0) {
            return false;
        }
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
    public boolean deleteComment(Account account, Long commentIdx) {
        // TODO : account, comment 못 찾을 시 예외 처리 필요
        if(account == null || account.getIdx() == 0) {
            return false;
        }
        Comment comment = commentRepository.findById(commentIdx).orElseThrow();
        // TODO : 작성자와 다른 경우 예외 처리 필요
        if (!comment.getAccount().equals(account))
            return false;

        commentRepository.deleteById(commentIdx);
        return true;
    }
}
