package com.comsultant.domain.wish.mapper;

import com.comsultant.domain.wish.dto.WishDto;
import com.comsultant.domain.wish.entity.Wish;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WishMapper extends EntityMapper<WishDto, Wish> {
    WishMapper mapper = Mappers.getMapper(WishMapper.class);

    @Override
    @Mapping(target = "accountIdx", constant = "0L") //보안때문에 accountIdx 0으로 설정
    @Mapping(source = "product.idx", target = "productIdx")
    WishDto toDto(final Wish entity);

    @Override
    @Mapping(source = "accountIdx", target = "account.idx")
    @Mapping(source = "productIdx", target = "product.idx")
    Wish toEntity(final WishDto dto);
}
