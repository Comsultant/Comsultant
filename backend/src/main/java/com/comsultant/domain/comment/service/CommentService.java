package com.comsultant.domain.comment.service;

import com.comsultant.domain.comment.dto.CommentDto;

public interface CommentService {

    boolean createComment(Long userIdx, Long boardIdx, CommentDto commentDto);

}
