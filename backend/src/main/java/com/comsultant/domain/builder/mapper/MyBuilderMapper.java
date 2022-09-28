package com.comsultant.domain.builder.mapper;

import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.entity.MyBuilder;
import com.comsultant.global.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MyBuilderMapper extends EntityMapper<MyBuilderDto, MyBuilder> {
    MyBuilderMapper mapper = Mappers.getMapper(MyBuilderMapper.class);

    @Override
    @Mapping(target = "accountIdx", constant = "0L")
    MyBuilderDto toDto(final MyBuilder entity);

    @Override
    @Mapping(source = "accountIdx", target = "account.idx")
    MyBuilder toEntity(final MyBuilderDto dto);

}
