package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.CpuDto;
import com.comsultant.domain.product.entity.Cpu;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CpuMapper extends EntityMapper<CpuDto, Cpu> {

    CpuMapper mapper = Mappers.getMapper(CpuMapper.class);

    @Override
    @Mapping(source = "product.idx", target = "idx") // 변수명이 다를 경우. source = Entity, target = DTO
    @Mapping(target = "wish", ignore = true)
    CpuDto toDto(final Cpu entity);

    @Override
    @Mapping(source = "idx", target = "product.idx") // source = DTO, target = Entity
    Cpu toEntity(final CpuDto dto);

    List<CpuDto> toDtoList(List<Cpu> entityList);

    List<Cpu> toEntityList(List<CpuDto> dtoList);
}
