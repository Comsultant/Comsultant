package com.comsultant.domain.comment.dto;

import com.comsultant.domain.comment.entity.Comment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class CommentResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetComment {
        private long commentIdx;
        private String content;
        private String productIdx;
        private String productName;
        private String productImg;
        public static GetComment build(Comment comment) {
            return GetComment.builder()
                    .commentIdx(comment.getIdx())
                    .content(comment.getContent())
                    //.productName()
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetComments {
        private List<GetComment> comments;
        private int totalPages;
        public static CommentResponse.GetComments build(List<Comment> comments, int totalPages) {
            return CommentResponse.GetComments.builder()
                    .comments(comments.stream().map(CommentResponse.GetComment::build).collect(Collectors.toList()))
                    .totalPages(totalPages)
                    .build();
        }
    }

}
