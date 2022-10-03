package com.comsultant.domain.builder.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.*;
import com.comsultant.domain.builder.entity.BuilderProduct;
import com.comsultant.domain.builder.entity.MyBuilder;
import com.comsultant.domain.builder.mapper.BuilderProductMapper;
import com.comsultant.domain.builder.mapper.MyBuilderMapper;
import com.comsultant.domain.builder.repository.BuilderProductRepository;
import com.comsultant.domain.builder.repository.MyBuilderRepository;
import com.comsultant.domain.comment.service.CommentServiceImpl;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import com.comsultant.domain.product.service.ProductService;
import com.comsultant.global.error.exception.BuilderApiException;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.BuilderErrorCode;
import com.comsultant.global.error.model.ProductErrorCode;
import com.comsultant.global.properties.ConstProperties;
import com.comsultant.infra.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuilderServiceImpl implements BuilderService {

    private final MyBuilderRepository myBuilderRepository;
    private final BuilderProductRepository builderProductRepository;
    private final ProductRepository productRepository;

    private final KafkaProducerService kafkaProducerService;
    private final CommentServiceImpl commentServiceImpl;
    private final ProductService productService;
    private static boolean[] isCategory;

    private static boolean[] isSame;

    private static String[] categories = {"0", "cpu", "ram", "hdd", "ssd", "psu", "cooler", "cases", "mainboard", "vga"};

    private static int[] sortNums = {1, 2, 5, 8, 3, 4, 6, 7, 9};

    private final ConstProperties constProperties;

    @Transactional
    @Override
    public boolean createMyBuilder(Account account, MyBuilderDto myBuilderDto) {
        // 캡처가 아닌 저장이면
        if (!myBuilderDto.isCapture()) {
            MyBuilder myBuilder;
            // 있던 빌더라면 연결된 아이템들 삭제
            if (myBuilderDto.getIdx() != 0) {
                myBuilder = myBuilderRepository.findById(myBuilderDto.getIdx()).orElseThrow(
                        () -> new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND)
                );
                builderProductRepository.deleteAllByMyBuilder(myBuilder);
            }
            // 새 빌더라면 마이빌더 생성
            else {
                myBuilderDto.updateUserInfo(account.getIdx());
                myBuilder = myBuilderRepository.save(MyBuilderMapper.mapper.toEntity(myBuilderDto));
            }
            // builderProduct 저장
            if (myBuilderDto.getBuilderProducts() != null) {
                for (BuilderProductDto builderProductDto : myBuilderDto.getBuilderProducts()) {
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
        }

        // 카프카 데이터 전송
        if (myBuilderDto.isKafka()) {
            String toKafka = toKafka(myBuilderDto, account);
            if (toKafka == null) {
                throw new BuilderApiException(BuilderErrorCode.Category_NOT_ENOUGH);
            }
            // TODO : 나중에 test에서 builder로 수정 필요
            else if (!kafkaProducerService.sendMessage("test", toKafka)) {
                throw new BuilderApiException(BuilderErrorCode.Kafka_SEND_FAIL);
            }
        }

        return true;
    }

    @Override
    public boolean captureBuilder(MyBuilderDto myBuilderDto) {
        Account account = new Account();
        // 카프카 데이터 전송
        String toKafka = toKafka(myBuilderDto, account);
        if (toKafka == null) {
            throw new BuilderApiException(BuilderErrorCode.Category_NOT_ENOUGH);
        }
//         TODO : 나중에 test에서 builder로 수정 필요
        else if (!kafkaProducerService.sendMessage("test", toKafka)) {
            throw new BuilderApiException(BuilderErrorCode.Kafka_SEND_FAIL);
        }

        return true;
    }

    @Override
    public MyBuilderDetailListDto getMyBuilderPageList(Account account, int page) {
        if (account == null || account.getIdx() == 0) {
            return null;
        }
        Pageable pageable = PageRequest.of(page, constProperties.getBuilderListSize());
        Page<MyBuilder> pageMyBuilders = myBuilderRepository.findAllByAccount(account, pageable);
        List<MyBuilder> myBuilders = pageMyBuilders.getContent();
        int totalPage = pageMyBuilders.getTotalPages();
        List<MyBuilderDetailDto> result = makeMyBuilderDetailDtoList(myBuilders);
        return MyBuilderDetailListDto.builder()
                .myBuilderDetailDtoList(result)
                .totalPage(totalPage)
                .build();
    }

    @Override
    public MyBuilderDetailDto getBuilder(long myBuilderIdx, Account account) {
        if (account == null || account.getIdx() == 0) {
            return null;
        }
        MyBuilder myBuilder = myBuilderRepository.findById(myBuilderIdx).orElseThrow(() -> new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND));
        List<BuilderProduct> builderProducts = builderProductRepository.findAllByMyBuilder(myBuilder);
        List<BuilderProductDetailDto> result = new ArrayList<>();
        for (BuilderProduct builderProduct : builderProducts) {
            Product product = productRepository.findById(builderProduct.getProduct().getIdx())
                    .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND));
            Map<String, String> realProduct = commentServiceImpl.getProductNameImgByIdx(product.getIdx(), product.getCategory());
            List<Integer> dateAndPrice = productService.getProductPriceOne(categories[product.getCategory()], product.getIdx());
            int price = 0;
            if (dateAndPrice != null) {
                price = dateAndPrice.get(1);
            }
            BuilderProductDetailDto ret = BuilderProductDetailDto.builder()
                    .category(product.getCategory())
                    .cnt(builderProduct.getCnt())
                    .productIdx(product.getIdx())
                    .productName(realProduct.get("name"))
                    .price(price)
                    .build();
            result.add(ret);
        }

        return MyBuilderDetailDto.builder()
                .myBuilderDto(MyBuilderMapper.mapper.toDto(myBuilder))
                .builderProductDetailDtos(result)
                .build();
    }

    @Override
    public MyBuilderDetailListDto getMyBuilderDetails(Account account) {
        List<MyBuilder> myBuilders = myBuilderRepository.findAllByAccount(account);
        List<MyBuilderDetailDto> result = makeMyBuilderDetailDtoList(myBuilders);
        return MyBuilderDetailListDto.builder()
                .myBuilderDetailDtoList(result)
                .build();
    }

    private List<MyBuilderDetailDto> makeMyBuilderDetailDtoList(List<MyBuilder> myBuilders) {
        List<MyBuilderDetailDto> result = new ArrayList<>();

        for (MyBuilder myBuilder : myBuilders) {
            List<BuilderProduct> builderProducts = builderProductRepository.findAllByMyBuilder(myBuilder);
            List<BuilderProductDetailDto> detailResult = new ArrayList<>();
            for (BuilderProduct builderProduct : builderProducts) {
                Product product = productRepository.findById(builderProduct.getProduct().getIdx())
                        .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND));
                Map<String, String> realProduct = commentServiceImpl.getProductNameImgByIdx(product.getIdx(), product.getCategory());
                List<Integer> dateAndPrice = productService.getProductPriceOne(categories[product.getCategory()], product.getIdx());
                int price = 0;
                if (dateAndPrice != null) {
                    price = dateAndPrice.get(1);
                }
                BuilderProductDetailDto ret = BuilderProductDetailDto.builder()
                        .category(product.getCategory())
                        .cnt(builderProduct.getCnt())
                        .productIdx(product.getIdx())
                        .productName(realProduct.get("name"))
                        .price(price)
                        .build();
                detailResult.add(ret);
            }
            MyBuilderDetailDto myBuilderDetailDto = MyBuilderDetailDto.builder()
                    .myBuilderDto(MyBuilderMapper.mapper.toDto(myBuilder))
                    .builderProductDetailDtos(detailResult)
                    .build();
            result.add(myBuilderDetailDto);
        }

        return result;
    }

    @Override
    @Transactional
    public boolean reNameMyBuilder(Account account, long myBuilderIdx, MyBuilderDto myBuilderDto) {
        if (account == null || account.getIdx() == 0) {
            return false;
        }
        MyBuilder myBuilder = myBuilderRepository.findById(myBuilderIdx).orElseThrow(
                () -> new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND)
        );
        myBuilder.modifyName(myBuilderDto.getName());
        return true;
    }

    @Transactional
    @Override
    public boolean deleteMyBuilder(Account account, long myBuilderIdx) {
        if (account == null || account.getIdx() == 0) {
            return false;
        }
        MyBuilder myBuilder = myBuilderRepository.findById(myBuilderIdx).orElseThrow(
                () -> new BuilderApiException(BuilderErrorCode.Builder_NOT_FOUND)
        );
        if (myBuilder.getAccount().getIdx() != account.getIdx()) {
            return false;
        } else {
            builderProductRepository.deleteAllByMyBuilder(myBuilder);
            myBuilderRepository.deleteById(myBuilderIdx);
            return true;
        }
    }

    public String toKafka(MyBuilderDto myBuilderDto, Account account) {
        List<Product> products = new ArrayList<>();
        // 카테고리 (1,2,5,8 있는지 체크 예정)
        isCategory = new boolean[10];
        int dtoLen = myBuilderDto.getBuilderProducts().size();
        int len = dtoLen;
        if (dtoLen < 4) {
            return null;
        }

        for (BuilderProductDto builderProductDto : myBuilderDto.getBuilderProducts()) {
            len += builderProductDto.getCnt() - 1;
        }
        isSame = new boolean[len];
        BuilderProductDto builderProductDto;


        List<BuilderProductDto> builderProductDtos = sortBuilderProductDto(myBuilderDto.getBuilderProducts());

        builderProductDto = builderProductDtos.get(0);
        Product product = productRepository.findById(builderProductDto.getProductIdx()).orElseThrow(
                () -> new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND)
        );
        products.add(product);
        isCategory[product.getCategory()] = true;
        int dtoIdx = 1;
        for (int i = 1; i < dtoLen; i++) {
            builderProductDto = builderProductDtos.get(i);
            product = productRepository.findById(builderProductDto.getProductIdx()).orElseThrow(
                    () -> new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND)
            );

            products.add(product);
            dtoIdx++;
            if (builderProductDto.getProductIdx() == builderProductDtos.get(i - 1).getProductIdx()) {
                isSame[dtoIdx] = true;
            }
            isCategory[product.getCategory()] = true;
            for (int j = 1; j < builderProductDto.getCnt(); j++) {
                products.add(product);
                isSame[dtoIdx++] = true;
            }
        }

        // 물품 별 시세
        int[] prices = new int[len];
        int[] date = new int[len];
        for (int i = 0; i < len; i++) {
            if (isSame[i]) {
                prices[i] = prices[i - 1];
            } else {
                List<Integer> dateAndPrice = productService.getProductPriceOne(categories[products.get(i).getCategory()], products.get(i).getIdx());
                if (dateAndPrice == null) {
                    prices[i] = 0;
                    date[i] = 0;
                } else {
                    prices[i] = dateAndPrice.get(1);
                    date[i] = dateAndPrice.get(0);
                }
            }
        }

        // 같은 물건은 가격 합함
        List<Integer> sumPrices = new ArrayList<>();
        if (isCategory[1] && isCategory[2] && isCategory[5] && isCategory[8]) {
            for (int i = 0; i < len; i++) {
                if (isSame[i]) {
                    int rem = sumPrices.remove(sumPrices.size() - 1);
                    rem += prices[i];
                    sumPrices.add(rem);
                } else {
                    sumPrices.add(prices[i]);
                }
            }

            return toString(products, myBuilderDto, account, sumPrices, date);
        }

        return null;
    }

    public String toString(List<Product> products, MyBuilderDto myBuilderDto, Account account, List<Integer> prices, int[] date) {
        StringBuilder sb = new StringBuilder();

        // 부품 아이디들
        // isSame[i]면 구분자 ;로 추가
        int len = products.size();
        Product product = products.get(0);
        sb.append(product.getCategory()).append("_").append(product.getIdx());
        for (int productIdx = 1; productIdx < len; productIdx++) {
            product = products.get(productIdx);
            if (isSame[productIdx]) {
                sb.append(";");
            } else {
                sb.append(",");
            }
            sb.append(product.getCategory()).append("_").append(product.getIdx());
        }
        sb.append("|");

        int pLen = prices.size();
        for (int priceIdx = 0; priceIdx < pLen; priceIdx++) {
            sb.append(prices.get(priceIdx)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("|");

        // 사용 분야
        sb.append(myBuilderDto.getUse()).append("|");
        // 사용 프로그램
        if (myBuilderDto.getProgram() != null) {
            sb.append(myBuilderDto.getProgram()).append("|");
        } else {
            sb.append("0|");
        }

        // 물품 가격 날짜
        for (int dateIdx = 0; dateIdx < len; dateIdx++) {
            if (!isSame[dateIdx]) {
                String dateS = Integer.toString(date[dateIdx]);
                if (dateS.length() >= 8) {
                    sb.append(dateS.substring(0, 4)).append(".").append(dateS.substring(4, 6)).append(".").append(dateS.substring(6, 8)).append(",");
                } else {
                    sb.append(0);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1).append("|");
        // 출생년도
        if (account != null) {
            sb.append(account.getBirthYear());
        } else {
            sb.append("0");
        }
        return sb.toString();
    }

    private List<BuilderProductDto> sortBuilderProductDto(List<BuilderProductDto> builderProductDtos) {

        List<PriorityQueue<BuilderProductDto>> ques = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ques.add(
                    new PriorityQueue<>(new Comparator<BuilderProductDto>() {
                        @Override
                        public int compare(BuilderProductDto o1, BuilderProductDto o2) {
                            if (o1.getProductIdx() > o2.getProductIdx())
                                return 1;
                            else
                                return -1;
                        }
                    }));
        }

        int len = builderProductDtos.size();

        for (int i = 0; i < len; i++) {
            BuilderProductDto builderProductDto = builderProductDtos.get(i);
            Product product = productRepository.findById(builderProductDto.getProductIdx()).orElseThrow(
                    () -> new BuilderApiException(BuilderErrorCode.PRODUCT_NOT_FOUND)
            );
            ques.get(product.getCategory()).add(builderProductDto);
        }

        List<BuilderProductDto> result = new ArrayList<>();
        for (int i : sortNums) {
            int size = ques.get(i).size();
            for (int j = 0; j < size; j++) {
                result.add(ques.get(i).poll());
            }
        }

        return result;
    }


}
