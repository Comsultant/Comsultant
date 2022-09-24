package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.CpuDto;
import com.comsultant.domain.product.entity.Cpu;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CpuMapper extends EntityMapper<CpuDto, Cpu> {

    CpuMapper mapper = Mappers.getMapper(CpuMapper.class);

    @Override
    CpuDto toDto(final Cpu entity);

    @Override
    Cpu toEntity(final CpuDto dto);

    List<CpuDto> toDtoList(List<Cpu> entityList);

    List<Cpu> toEntityList(List<CpuDto> dtoList);
}
