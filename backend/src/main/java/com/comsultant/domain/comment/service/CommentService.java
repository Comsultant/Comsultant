package com.comsultant.domain.comment.service;

import com.comsultant.domain.comment.dto.CommentDto;

public interface CommentService {

    boolean createComment(Long userIdx, Long productIdx, CommentDto commentDto);

    boolean deleteComment(Long userIdx, Long commentIdx);

}
