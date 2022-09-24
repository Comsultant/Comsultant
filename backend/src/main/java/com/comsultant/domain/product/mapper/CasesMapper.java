package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.CasesDto;
import com.comsultant.domain.product.entity.Cases;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CasesMapper extends EntityMapper<CasesDto, Cases> {

    CasesMapper mapper = Mappers.getMapper(CasesMapper.class);

    @Override
    CasesDto toDto(final Cases entity);

    @Override
    Cases toEntity(final CasesDto dto);

    List<CasesDto> toDtoList(List<Cases> entityList);

    List<Cases> toEntityList(List<CasesDto> dtoList);
}
