package com.comsultant.domain.builder.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.BuilderProductDto;
import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.entity.BuilderProduct;
import com.comsultant.domain.builder.entity.MyBuilder;
import com.comsultant.domain.builder.mapper.BuilderProductMapper;
import com.comsultant.domain.builder.mapper.MyBuilderMapper;
import com.comsultant.domain.builder.repository.BuilderProductRepository;
import com.comsultant.domain.builder.repository.MyBuilderRepository;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import com.comsultant.global.error.exception.BuilderApiException;
import com.comsultant.global.error.model.BuilderErrorCode;
import com.comsultant.infra.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuilderServiceImpl implements BuilderService {

//    private final BuilderRepository builderRepository;

    private final MyBuilderRepository myBuilderRepository;

    private final BuilderProductRepository builderProductRepository;

    private final ProductRepository productRepository;

    private final KafkaProducerService kafkaProducerService;

    private static boolean[] isCategory;

/*    @Override
    public boolean createBuilder(BuilderDto builderDto) {
        Builder savedBuilder = builderRepository.save(builderToEntity(builderDto));
        System.out.println(savedBuilder.get_id() + " " + savedBuilder.getName() + " " + savedBuilder.getCreateDate());
        return true;
    }*/

    @Transactional
    @Override
    public boolean createMyBuilder(Account account, MyBuilderDto myBuilderDto) {
        boolean isLogin = true;
        if (account == null || account.getIdx() == 0) {
            isLogin = false;
        }

        if (isLogin) { // 로그인 했고
            MyBuilder myBuilder;
            if (myBuilderDto.getIdx() != 0) { // 있던 빌더라면
                if (!myBuilderRepository.existsById(myBuilderDto.getIdx())) {
                    throw new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND);
                }
                myBuilder = myBuilderRepository.findById(myBuilderDto.getIdx()).orElseThrow(
                        () -> new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND)
                );
                builderProductRepository.deleteAllByMyBuilder(myBuilder); // 연결된 아이템들 삭제
            } else { // 새 빌더라면 마이빌더 생성
                myBuilderDto.updateUserInfo(account.getIdx());
                myBuilder = myBuilderRepository.save(MyBuilderMapper.mapper.toEntity(myBuilderDto));
            }

            for (BuilderProductDto builderProductDto : myBuilderDto.getBuilderProducts()) { // builderProduct 저장
                if (!productRepository.existsById(builderProductDto.getProductIdx())) {
                    throw new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND);
                }
                builderProductDto.updateMyBuilderInfo(myBuilder.getIdx());
                BuilderProduct savedBuilderProduct = builderProductRepository.save(BuilderProductMapper.mapper.toEntity(builderProductDto));
                if (savedBuilderProduct.getIdx() == 0) {
                    return false;
                }
            }
        }
        if (myBuilderDto.isKafka()) { // 추천 저장할 거면
            String toKafka = toKafka(myBuilderDto, account);
            if (toKafka == null)
                throw new BuilderApiException(BuilderErrorCode.Category_NOT_ENOUGH);
            else if (!kafkaProducerService.sendMessage("test", toKafka))
                throw new BuilderApiException(BuilderErrorCode.Kafka_SEND_FAIL);
        }

        return true;
    }

    public String toKafka(MyBuilderDto myBuilderDto, Account account) {
        List<Product> products = new ArrayList<>(); // 하둡에 보낼 리스트
        isCategory = new boolean[10]; // 카테고리 초기화 (1,2,5,8 있는지 체크 예정)
        for (BuilderProductDto builderProductDto : myBuilderDto.getBuilderProducts()) { // product 리스트 생성
            Product product = productRepository.findById(builderProductDto.getProductIdx()).orElseThrow(
                    ()-> new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND)
            );
            if (product == null)
                throw new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND);
            isCategory[product.getCategory()] = true;
            for (int i = 0; i < builderProductDto.getCnt(); i++)
                products.add(product);
        }

        if (isCategory[1] && isCategory[2] && isCategory[5] && isCategory[8]) {
            return toString(products, myBuilderDto, account);
        }

        return null;
    }


    public String toString(List<Product> products, MyBuilderDto myBuilderDto, Account account) {
        StringBuilder sb = new StringBuilder();

        for (Product product : products) // 부품 아이디들
            sb.append(product.getCategory()).append("_").append(product.getIdx()).append(",");
        sb.deleteCharAt(sb.length() - 1).append("|");

        for (Product product : products) // TODO : 물품들 가격 추가
            sb.append(product.getCategory()).append(",");
        sb.deleteCharAt(sb.length() - 1).append("|");

        sb.append(myBuilderDto.getUse()).append("|"); // 사용 분야
        if (myBuilderDto.getProgram() != null)
            sb.append(myBuilderDto.getProgram()).append("|"); // 사용 프로그램
        else sb.append("0|");
        sb.append("date").append("|"); // TODO : 물품 가격 날짜
        if (account != null) // 출생년도
            sb.append(account.getBirthYear());
        else
            sb.append("0");

        return sb.toString();
    }

/*    public static Builder builderToEntity(BuilderDto builderDto) {
        return Builder.builder()
                .name(builderDto.getName())
                .build();
    }*/
}
