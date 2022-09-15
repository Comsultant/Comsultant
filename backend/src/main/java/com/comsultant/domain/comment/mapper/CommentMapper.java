package com.comsultant.domain.comment.mapper;

import com.comsultant.domain.comment.dto.CommentDto;
import com.comsultant.domain.comment.entity.Comment;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {
    CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @Override
    CommentDto toDto(final Comment entity);

    @Override
    Comment toEntity(final CommentDto dto);

}
