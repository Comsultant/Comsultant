package com.comsultant.domain.comment.api;

import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.service.CommentService;
import com.comsultant.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentApi.class);

    private final CommentService commentService;

    @PostMapping("/{productIdx}")
    public ResponseEntity<MessageResponse> createComment(@PathVariable("productIdx") Long productIdx, @RequestBody CommentDto commentDto) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        boolean result = commentService.createComment(1L, productIdx, commentDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "fail"));
        }
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentIdx") Long commentIdx) {
        // TODO : 토큰에서 유저 정보 꺼내서 사용
        boolean result = commentService.deleteComment(1L, commentIdx);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, "fail"));
        }
    }

}
