package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.RamDto;
import com.comsultant.domain.product.entity.Ram;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RamMapper extends EntityMapper<RamDto, Ram> {

    RamMapper mapper = Mappers.getMapper(RamMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    RamDto toDto(final Ram entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    Ram toEntity(final RamDto dto);

    List<RamDto> toDtoList(List<Ram> entityList);

    List<Ram> toEntityList(List<RamDto> dtoList);
}
