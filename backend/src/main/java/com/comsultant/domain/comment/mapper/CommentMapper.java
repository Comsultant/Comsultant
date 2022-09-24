package com.comsultant.domain.comment.mapper;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.domain.product.entity.Product;

import java.time.LocalDateTime;

public class CommentMapper {

    public static CommentDto toDto(final Comment entity) {
        return CommentDto.builder()
                .idx(entity.getIdx())
                .accountIdx(entity.getAccount().getIdx())
                .productIdx(entity.getProduct().getIdx())
                .content(entity.getContent())
                .createDate(entity.getCreateDate().toString())
                .build();
    }

    public static Comment toEntity(final CommentDto dto, Product product) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.account( commentDtoToAccount( dto ) );
        comment.product( product );
        if ( dto.getIdx() != null ) {
            comment.idx( dto.getIdx() );
        }
        comment.content( dto.getContent() );
        if ( dto.getCreateDate() != null ) {
            comment.createDate( LocalDateTime.parse( dto.getCreateDate() ) );
        }

        return comment.build();
    }

    protected static Account commentDtoToAccount(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        if ( commentDto.getAccountIdx() != null ) {
            account.idx( commentDto.getAccountIdx() );
        }

        return account.build();
    }


}
