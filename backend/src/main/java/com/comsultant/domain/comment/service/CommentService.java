package com.comsultant.domain.comment.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.dto.CommentDetailDto;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.dto.CommentListDto;

import java.util.List;

public interface CommentService {

    boolean createComment(Account account, long productIdx, CommentDto commentDto) throws Throwable;

    boolean updateComment(Account account, long commentIdx, CommentDto commentDto);

    boolean deleteComment(Account account, long commentIdx);

    CommentListDto getComments(Account account, int page, boolean desc);

}
