package com.comsultant.domain.comment.service;

import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;

public interface CommentService {

    boolean createComment(Long userIdx, Long productIdx, CommentDto commentDto);

    boolean updateComment(Long userIdx, Long commentIdx, CommentDto commentDto);

    boolean deleteComment(Long userIdx, Long commentIdx);

    CommentResponse.GetComments getComments(Long accountIdx, int page, boolean desc);

}
