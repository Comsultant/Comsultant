package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.SsdDto;
import com.comsultant.domain.product.entity.Ssd;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SsdMapper extends EntityMapper<SsdDto, Ssd> {

    SsdMapper mapper = Mappers.getMapper(SsdMapper.class);

    @Override
    SsdDto toDto(final Ssd entity);

    @Override
    Ssd toEntity(final SsdDto dto);

    List<SsdDto> toDtoList(List<Ssd> entityList);

    List<Ssd> toEntityList(List<SsdDto> dtoList);
}
