package com.comsultant.domain.builder.service;

import com.comsultant.domain.builder.dto.RecommendBuilderDto;
import com.comsultant.domain.builder.dto.RecommendBuilderResponseDto;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.error.exception.CommentApiException;
import com.comsultant.global.error.model.CommentErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    private final MongoTemplate mongoTemplate;

    private final CpuRepository cpuRepository;
    private final RamRepository ramRepository;
    private final HddRepository hddRepository;
    private final SsdRepository ssdRepository;
    private final PsuRepository psuRepository;
    private final CoolerRepository coolerRepository;
    private final CasesRepository casesRepository;
    private final MainBoardRepository mainBoardRepository;
    private final VgaRepository vgaRepository;

    @Override
    public List<RecommendBuilderResponseDto> getRecommendBuilder(AccountDetails accountDetails, RecommendBuilderDto dto) {

        /*
          사용자가 입력한 조건
          부품 수, 사용 용도, 프로그램, 가격
         */
        Criteria defaultCriteria = Criteria.where("cpu_cnt").is(dto.getCpu_cnt())
                .and("ram_cnt").is(dto.getRam_cnt())
                .and("hdd_cnt").is(dto.getHdd_cnt())
                .and("ssd_cnt").is(dto.getSsd_cnt())
                .and("psu_cnt").is(dto.getPsu_cnt())
                .and("cooler_cnt").is(dto.getCooler_cnt())
                .and("cases_cnt").is(dto.getCases_cnt())
                .and("mainboard_cnt").is(dto.getMainboard_cnt())
                .and("vga_cnt").is(dto.getVga_cnt())
                .and("use").is(dto.getUse())
                .and("price").gte(dto.getLowPrice()).lte(dto.getHighPrice());

        // 프로그램은 선택사항
        if(dto.getProgram() != null && dto.getProgram().length() != 0) {
            defaultCriteria.and("program").is(dto.getProgram());
        }

        /*
          선택한부품 조건
         */
        Map<String, Integer> products = dto.getProds();
        for(String key : products.keySet()) {
            defaultCriteria.and(key).gte(products.get(key));
        }

        Query defaultQuery = Query.query(defaultCriteria).with(Sort.by(Sort.Direction.DESC, "cnt"));

        List<RecommendBuilderDto> result = mongoTemplate.find(
                defaultQuery,
                RecommendBuilderDto.class,
                "builder"
        );

        // 최대 10개로 제한
        if(result.size() > 10) {
            result = new ArrayList<>(result.subList(0, 10));
        }


        /*
            응답 설정 ( 시간 고려해서 프론트에서 처리해야 할 수 있음 )
         */
        List<RecommendBuilderResponseDto> response = new ArrayList<>();
        for (RecommendBuilderDto r : result) {
//            System.out.printf("%s , %d : %d\n", r.getId(), r.getCnt(), r.getPrice());
            HashMap<String, Integer> prodDetail[] = new HashMap[9];
            for(int i=0; i<9; i++) {
                prodDetail[i] = new HashMap<>();
            }

            String prods[] = r.getId().split(",");
            for(String prod : prods) {
                if(prod.charAt(0) == '|') {
                    break;
                }
                int category = 0;
                int prod_cnt = 1;
                String prod_idx;
                if(!prod.contains(";")) {
                    String[] prod_split = prod.split("_");
                    category = Integer.parseInt(prod_split[0]);
                    prod_idx = prod_split[1];
                } else {
                    category = Integer.parseInt(String.valueOf(prod.charAt(0)));
                    int startIdx = 2;
                    int endIdx = prod.indexOf(";");
                    prod_cnt = this.countChar(prod, ';') + 1;
                    prod_idx = prod.substring(startIdx, endIdx);
                }

                String prodName = prod_idx + "_" + this.getProductNameByIdx(Long.parseLong(prod_idx), category);

                // 같은 키는 없다.
                prodDetail[category-1].put(prodName, prod_cnt);
            }
            response.add(RecommendBuilderResponseDto.builder()
                    .prodDetail(prodDetail)
                    .price(r.getPrice())
                    .priceDate(r.getPrice_date()).build());
        }
        return response;
    }

    public int countChar(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    public String getProductNameByIdx(long idx, int productCategory) {
        Map<String, String> ret = new HashMap<>();
        if (productCategory == 1) {
            Cpu cpu = cpuRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return cpu.getName();
        } else if (productCategory == 2) {
            Ram product = ramRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 3) {
            Hdd product = hddRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 4) {
            Ssd product = ssdRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 5) {
            Psu product = psuRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 6) {
            Cooler product = coolerRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 7) {
            Cases product = casesRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else if (productCategory == 8) {
            MainBoard product = mainBoardRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        } else {
            Vga product = vgaRepository.findByIdx(idx).orElseThrow(
                    () -> new CommentApiException(CommentErrorCode.PRODUCT_NOT_FOUND)
            );
            return product.getName();
        }
    }
}
