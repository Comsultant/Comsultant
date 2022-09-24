package com.comsultant.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListDto {
    private List<CommentDetailDto> commentDetailDtoList;
    private int totalPage;
}
