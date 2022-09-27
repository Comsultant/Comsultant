package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.HddDto;
import com.comsultant.domain.product.entity.Hdd;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HddMapper extends EntityMapper<HddDto, Hdd> {

    HddMapper mapper = Mappers.getMapper(HddMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    HddDto toDto(final Hdd entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    Hdd toEntity(final HddDto dto);

    List<HddDto> toDtoList(List<Hdd> entityList);

    List<Hdd> toEntityList(List<HddDto> dtoList);
}
