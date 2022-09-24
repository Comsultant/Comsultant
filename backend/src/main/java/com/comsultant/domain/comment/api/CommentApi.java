package com.comsultant.domain.comment.api;

import com.comsultant.domain.comment.dto.CommentDetailDto;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;
import com.comsultant.domain.comment.service.CommentService;
import com.comsultant.global.common.response.ListResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.ParameterUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentApi {

    private final CommentService commentService;

    private final ResponseProperties responseProperties;

    @PostMapping("/{productIdx}")
    public ResponseEntity<MessageResponse> createComment(@PathVariable("productIdx") Long productIdx, @RequestBody CommentDto commentDto, @AuthenticationPrincipal AccountDetails accountDetails) throws Throwable {
        boolean result = commentService.createComment(accountDetails.getAccount(), productIdx, commentDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ListResponse<CommentDetailDto>> getComments
            (@RequestParam(required = false) String pageParam, @RequestParam(required = false) String descParam, @AuthenticationPrincipal AccountDetails accountDetails) {

        int page = ParameterUtil.checkPage(pageParam);
        boolean desc = ParameterUtil.checkDesc(descParam);

        List<CommentDetailDto> result = commentService.getComments(accountDetails.getAccount(), page, desc);

        return ResponseEntity.status(HttpStatus.OK).body(ListResponse.of(HttpStatus.OK, responseProperties.getSuccess(), null));
    }

    @PutMapping("/{commentIdx}")
    public ResponseEntity<MessageResponse> updateComment(@PathVariable("commentIdx") Long commentIdx, @RequestBody CommentDto commentDto, @AuthenticationPrincipal AccountDetails accountDetails) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        boolean result = commentService.updateComment(accountDetails.getAccount(), commentIdx, commentDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentIdx") Long commentIdx, @AuthenticationPrincipal AccountDetails accountDetails) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        boolean result = commentService.deleteComment(accountDetails.getAccount(), commentIdx);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

}
