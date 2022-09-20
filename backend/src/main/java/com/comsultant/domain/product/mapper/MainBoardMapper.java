package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.MainBoardDto;
import com.comsultant.domain.product.entity.MainBoard;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MainBoardMapper extends EntityMapper<MainBoardDto, MainBoard> {

    MainBoardMapper mapper = Mappers.getMapper(MainBoardMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    MainBoardDto toDto(final MainBoard entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    MainBoard toEntity(final MainBoardDto dto);

    List<MainBoardDto> toDtoList(List<MainBoard> entityList);

    List<MainBoard> toEntityList(List<MainBoardDto> dtoList);
}
