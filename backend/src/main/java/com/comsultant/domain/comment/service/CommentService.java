package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentResponse;

public interface CommentService {

    boolean createComment(Account account, Long productIdx, CommentDto commentDto);

    boolean updateComment(Account account, Long commentIdx, CommentDto commentDto);

    boolean deleteComment(Account account, Long commentIdx);

    CommentResponse.GetComments getComments(Account account, int page, boolean desc);

}
