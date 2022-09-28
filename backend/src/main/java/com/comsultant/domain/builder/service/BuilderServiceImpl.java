package com.comsultant.domain.builder.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.entity.MyBuilder;
import com.comsultant.domain.builder.mapper.MyBuilderMapper;
import com.comsultant.domain.builder.repository.BuilderRepository;
import com.comsultant.domain.builder.repository.MyBuilderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuilderServiceImpl implements BuilderService {

    private final BuilderRepository builderRepository;

    private final MyBuilderRepository myBuilderRepository;


/*    @Override
    public boolean createBuilder(BuilderDto builderDto) {
        Builder savedBuilder = builderRepository.save(builderToEntity(builderDto));
        System.out.println(savedBuilder.get_id() + " " + savedBuilder.getName() + " " + savedBuilder.getCreateDate());
        return true;
    }*/

    @Override
    public boolean createMyBuilder(Account account, MyBuilderDto myBuilderDto) {
        if (account == null || account.getIdx() == 0) {
            return false;
        }
        myBuilderDto.updateUserInfo(account.getIdx());
        MyBuilder savedMyBuilder = myBuilderRepository.save(MyBuilderMapper.toEntity(myBuilderDto));

        return savedMyBuilder.getIdx() != 0;
    }

/*    public static Builder builderToEntity(BuilderDto builderDto) {
        return Builder.builder()
                .name(builderDto.getName())
                .build();
    }*/
}
