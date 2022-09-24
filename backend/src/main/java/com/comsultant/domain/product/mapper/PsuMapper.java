package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.PsuDto;
import com.comsultant.domain.product.entity.Psu;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PsuMapper extends EntityMapper<PsuDto, Psu> {

    PsuMapper mapper = Mappers.getMapper(PsuMapper.class);

    @Override
    PsuDto toDto(final Psu entity);

    @Override
    Psu toEntity(final PsuDto dto);

    List<PsuDto> toDtoList(List<Psu> entityList);

    List<Psu> toEntityList(List<PsuDto> dtoList);
}
