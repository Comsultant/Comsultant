package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.dto.CommentDetailDto;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.comment.mapper.CommentMapper;
import com.comsultant.domain.comment.repository.CommentRepository;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.global.error.exception.CommentApiException;
import com.comsultant.global.error.model.CommentErrorCode;
import com.comsultant.global.properties.ConstProperties;

import java.util.ArrayList;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final CpuRepository cpuRepository;
    private final RamRepository ramRepository;
    private final HddRepository hddRepository;
    private final SsdRepository ssdRepository;
    private final PsuRepository psuRepository;
    private final CoolerRepository coolerRepository;
    private final CasesRepository casesRepository;
    private final MainBoardRepository mainBoardRepository;
    private final VgaRepository vgaRepository;
    private final ConstProperties constProperties;


    @Override
    public boolean createComment(Account account, long productIdx, CommentDto commentDto) throws Throwable {
        if(account == null || account.getIdx() == 0) {
            return false;
        }
        commentDto.updateUserInfo(account.getIdx(), productIdx);

        Product product = (Product) productRepository.findByIdx(productIdx).orElseThrow(
                () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
        );

        Comment savedComment = commentRepository.save(CommentMapper.toEntity(commentDto, product));
        return savedComment.getIdx() != 0;
    }

    @Override
    public List<CommentDetailDto> getComments(Account account, int page, boolean desc) {
        if(account == null || account.getIdx() == 0) {
            return Collections.emptyList();
        }
        Pageable pageable;

        if (desc) {
            pageable = PageRequest.of(page, constProperties.getCommentListSize(), Sort.by("idx").descending());
        } else {
            pageable = PageRequest.of(page, constProperties.getCommentListSize());
        }

        System.out.println(page);
        System.out.println(constProperties.getCommentListSize());

        Page<Comment> pageComments = commentRepository.findAllByAccount(account, pageable);
        List<Comment> comments = pageComments.getContent();
        int totalPages = pageComments.getTotalPages();

        List<CommentDetailDto> result = new ArrayList<>();

        for (Comment comment : comments) {
//            int productCategory = comment.getProduct().getCategory();
//            long productIdx = comment.getProduct().getIdx();
            Product product = comment.getProduct();
//
//            CommentDetailDto ret = CommentDetailDto.builder()
//                    .content(comment.getContent())
//                    .createDate(comment.getCreateDate().toString())
//                    .productIdx(productIdx)
//                    .productName(detail.getName())
//                    .productCategory(productCategory)
//                    .productImg(detail.getImgCnt())
//                    .build();
//            result.add(ret);
        }

        return result;
    }

    @Override
    public boolean updateComment(Account account, long commentIdx, CommentDto commentDto) {
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
    public boolean deleteComment(Account account, long commentIdx) {
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

    private Product getProductByIdx(long idx, int productCategory) {
        if(productCategory == 1) {
            return cpuRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 2) {
            return ramRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 3) {
            return hddRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 4) {
            return ssdRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 5) {
            return psuRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 6) {
            return coolerRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 7) {
            return casesRepository.findByIdx(idx).orElse(null);
        } else if(productCategory == 8) {
            return mainBoardRepository.findByIdx(idx).orElse(null);
        } else {
            return vgaRepository.findByIdx(idx).orElse(null);
        }
    }

}
