package com.comsultant.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long idx;
    private Long accountIdx;
    private Long productIdx;
    private String content;
    private String createDate;
}
