package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.MainBoardDto;
import com.comsultant.domain.product.entity.MainBoard;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MainBoardMapper extends EntityMapper<MainBoardDto, MainBoard> {

    MainBoardMapper mapper = Mappers.getMapper(MainBoardMapper.class);

    @Override
    MainBoardDto toDto(final MainBoard entity);

    @Override
    MainBoard toEntity(final MainBoardDto dto);

    List<MainBoardDto> toDtoList(List<MainBoard> entityList);

    List<MainBoard> toEntityList(List<MainBoardDto> dtoList);
}
