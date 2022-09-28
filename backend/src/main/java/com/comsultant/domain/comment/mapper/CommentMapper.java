package com.comsultant.domain.comment.mapper;

import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {
    CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @Override
    @Mapping(target = "accountIdx", constant = "0L")
    @Mapping(source = "product.idx", target = "productIdx")
    CommentDto toDto(final Comment entity);

    @Override
    @Mapping(source = "accountIdx", target = "account.idx")
    @Mapping(source = "productIdx", target = "product.idx")
    Comment toEntity(final CommentDto dto);

}
