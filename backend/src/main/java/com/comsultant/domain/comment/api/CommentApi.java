package com.comsultant.domain.comment.api;

import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;
import com.comsultant.domain.comment.service.CommentService;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentApi {

    private final CommentService commentService;

    private final ResponseProperties responseProperties;

    @PostMapping("/{productIdx}")
    public ResponseEntity<MessageResponse> createComment(@PathVariable("productIdx") Long productIdx, @RequestBody CommentDto commentDto, @AuthenticationPrincipal AccountDetails accountDetails) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        boolean result = commentService.createComment(accountDetails.getAccount(), productIdx, commentDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("")
    public ResponseEntity<CommentResponse.GetComments> getComments(@RequestParam int page, @RequestParam boolean desc, @AuthenticationPrincipal AccountDetails accountDetails) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        CommentResponse.GetComments response = commentService.getComments(accountDetails.getAccount(), page, desc);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
