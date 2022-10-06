package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.PsuDto;
import com.comsultant.domain.product.entity.Psu;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PsuMapper extends EntityMapper<PsuDto, Psu> {

    PsuMapper mapper = Mappers.getMapper(PsuMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    @Mapping(target = "wish", ignore = true)
    PsuDto toDto(final Psu entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    Psu toEntity(final PsuDto dto);

    List<PsuDto> toDtoList(List<Psu> entityList);

    List<Psu> toEntityList(List<PsuDto> dtoList);
}
