package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.ProductDto;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    ProductDto toDto(final Product entity);

    @Override
    Product toEntity(final ProductDto dto);

    List<ProductDto> toDtoList(List<Product> entityList);

    List<Product> toEntityList(List<ProductDto> dtoList);
}
