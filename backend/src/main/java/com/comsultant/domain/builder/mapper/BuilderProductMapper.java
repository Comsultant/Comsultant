package com.comsultant.domain.builder.mapper;

import com.comsultant.domain.builder.dto.BuilderProductDto;
import com.comsultant.domain.builder.entity.BuilderProduct;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuilderProductMapper extends EntityMapper<BuilderProductDto, BuilderProduct> {
    BuilderProductMapper mapper = Mappers.getMapper(BuilderProductMapper.class);

    @Override
    @Mapping(source = "myBuilder.idx", target = "myBuilderIdx")
    @Mapping(source = "product.idx", target = "productIdx")
    BuilderProductDto toDto(final BuilderProduct entity);

    @Override
    @Mapping(source = "myBuilderIdx", target = "myBuilder.idx")
    @Mapping(source = "productIdx", target = "product.idx")
    BuilderProduct toEntity(final BuilderProductDto dto);

}
