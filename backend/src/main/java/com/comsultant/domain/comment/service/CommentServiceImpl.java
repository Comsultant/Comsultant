package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.dto.CommentDetailDto;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentListDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.comment.mapper.CommentMapper;
import com.comsultant.domain.comment.repository.CommentRepository;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.global.error.exception.CommentApiException;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.CommentErrorCode;
import com.comsultant.global.error.model.ProductErrorCode;
import com.comsultant.global.properties.ConstProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final ConstProperties constProperties;

    private final CpuRepository cpuRepository;
    private final RamRepository ramRepository;
    private final HddRepository hddRepository;
    private final SsdRepository ssdRepository;
    private final PsuRepository psuRepository;
    private final CoolerRepository coolerRepository;
    private final CasesRepository casesRepository;
    private final MainBoardRepository mainBoardRepository;
    private final VgaRepository vgaRepository;

    @Override
    public CommentDto createComment(Account account, long productIdx, CommentDto commentDto) {
        if (account == null || account.getIdx() == 0) {
            return null;
        }

        commentDto.updateUserInfo(account.getIdx(), productIdx);
        if (!productRepository.existsById(productIdx)) {
            throw new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND);
        }

        Comment savedComment = commentRepository.save(CommentMapper.mapper.toEntity(commentDto));
        if ( savedComment.getIdx() != 0 ) {
            return CommentMapper.mapper.toDto(savedComment);
        } else {
            return null;
        }
    }

    @Override
    public CommentListDto getComments(Account account, int page, boolean desc) {
        if (account == null || account.getIdx() == 0) {
            return null;
        }
        Pageable pageable;

        if (desc) {
            pageable = PageRequest.of(page, constProperties.getCommentListSize(), Sort.by("idx").descending());
        } else {
            pageable = PageRequest.of(page, constProperties.getCommentListSize());
        }

        Page<Comment> pageComments = commentRepository.findAllByAccount(account, pageable);
        List<Comment> comments = pageComments.getContent();
        int totalPages = pageComments.getTotalPages();

        List<CommentDetailDto> result = new ArrayList<>();

        for (Comment comment : comments) {
            Map<String, String> product = this.getProductNameImgByIdx(comment.getProduct().getIdx(), comment.getProduct().getCategory());
            CommentDetailDto ret = CommentDetailDto.builder()
                    .commentDto(CommentMapper.mapper.toDto(comment))
                    .productImg(Integer.parseInt(product.get("img")))
                    .productName(product.get("name"))
                    .category(comment.getProduct().getCategory())
                    .build();
            result.add(ret);
        }

        return CommentListDto.builder()
                .commentDetailDtoList(result)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public CommentListDto getProductComments(long productIdx, int page, boolean desc) {
        Pageable pageable;

        if (desc) {
            pageable = PageRequest.of(page, constProperties.getCommentListSize(), Sort.by("idx").descending());
        } else {
            pageable = PageRequest.of(page, constProperties.getCommentListSize());
        }

        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND));

        Page<Comment> pageComments = commentRepository.findAllByProduct(product, pageable);
        List<Comment> comments = pageComments.getContent();
        int totalPages = pageComments.getTotalPages();

        List<CommentDetailDto> result = new ArrayList<>();

        for (Comment comment : comments) {
            String nickName = comment.getAccount().getNickname();
            CommentDetailDto ret = CommentDetailDto.builder()
                    .commentDto(CommentMapper.mapper.toDto(comment))
                    .build();
            ret.getCommentDto().updateNickName(nickName);
            result.add(ret);
        }

        return CommentListDto.builder()
                .commentDetailDtoList(result)
                .totalPage(totalPages)
                .build();
    }

    @Override
    @Transactional()
    public boolean updateComment(Account account, long commentIdx, CommentDto commentDto) {
        if (account == null || account.getIdx() == 0) {
            return false;
        }
        Comment comment = commentRepository.findById(commentIdx).orElseThrow(
                () -> new CommentApiException(CommentErrorCode.COMMENT_NOT_FOUND)
        );

        if (comment.getAccount().getIdx() != account.getIdx()) {
            return false;
        } else {
            comment.updateInfo(commentDto.getContent());
            return true;
        }
    }

    @Override
    public boolean deleteComment(Account account, long commentIdx) {
        if (account == null || account.getIdx() == 0) {
            return false;
        }
        Comment comment = commentRepository.findById(commentIdx).orElseThrow(
                () -> new CommentApiException(CommentErrorCode.COMMENT_NOT_FOUND)
        );
        if (comment.getAccount().getIdx() != account.getIdx()) {
            return false;
        } else {
            commentRepository.deleteById(commentIdx);
            return true;
        }
    }

    public Map<String, String> getProductNameImgByIdx(long idx, int productCategory) {
        Map<String, String> ret = new HashMap<>();
        if (productCategory == 1) {
            Cpu cpu = cpuRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", cpu.getName());
            ret.put("img", String.valueOf(cpu.getImgCnt()));
            return ret;
        } else if (productCategory == 2) {
            Ram product = ramRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 3) {
            Hdd product = hddRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 4) {
            Ssd product = ssdRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 5) {
            Psu product = psuRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 6) {
            Cooler product = coolerRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 7) {
            Cases product = casesRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else if (productCategory == 8) {
            MainBoard product = mainBoardRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        } else {
            Vga product = vgaRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            ret.put("name", product.getName());
            ret.put("img", String.valueOf(product.getImgCnt()));
            return ret;
        }
    }
}
