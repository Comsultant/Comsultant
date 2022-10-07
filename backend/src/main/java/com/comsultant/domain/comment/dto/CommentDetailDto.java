package com.comsultant.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDetailDto {
    private CommentDto commentDto;
    private int productImg;
    private String productName;
    private int category;
}
