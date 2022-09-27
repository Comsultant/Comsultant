package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.CoolerDto;
import com.comsultant.domain.product.entity.Cooler;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoolerMapper extends EntityMapper<CoolerDto, Cooler> {

    CoolerMapper mapper = Mappers.getMapper(CoolerMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    CoolerDto toDto(final Cooler entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    Cooler toEntity(final CoolerDto dto);

    List<CoolerDto> toDtoList(List<Cooler> entityList);

    List<Cooler> toEntityList(List<CoolerDto> dtoList);
}
