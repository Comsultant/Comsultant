package com.comsultant.domain.product.mapper;

import com.comsultant.domain.product.dto.CategoryDto;
import com.comsultant.domain.product.entity.Category;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    CategoryDto toDto(final Category entity);

    @Override
    Category toEntity(final CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> entityList);

    List<Category> toEntityList(List<CategoryDto> dtoList);
}
