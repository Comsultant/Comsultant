package com.comsultant.domain.product.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.dto.filterResponse.*;
import com.comsultant.domain.product.dto.request.*;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.global.config.security.AccountDetails;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(long idx);

    CpuDto getCpu(long idx, AccountDetails accountDetails);

    RamDto getRam(long idx, AccountDetails accountDetails);

    CasesDto getCases(long idx, AccountDetails accountDetails);

    HddDto getHdd(long idx, AccountDetails accountDetails);

    MainBoardDto getMainBoard(long idx, AccountDetails accountDetails);

    CoolerDto getCooler(long idx, AccountDetails accountDetails);

    PsuDto getPsu(long idx, AccountDetails accountDetails);

    SsdDto getSsd(long idx, AccountDetails accountDetails);

    VgaDto getVga(long idx, AccountDetails accountDetails);

    Object getObject(long type, long idx, AccountDetails accountDetails);

    ProductListDto getCpuList(CpuRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getRamList(RamRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getVgaList(VgaRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getPsuList(PsuRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getMainBoardList(MainBoardRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getCoolerList(CoolerRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getCasesList(CasesRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getHddList(HddRequest request, int page, int desc, AccountDetails accountDetails);

    ProductListDto getSsdList(SsdRequest request, int page, int desc, AccountDetails accountDetails);

    FilterCpuResponse getCpufilter();

    FilterVgaResponse getVgafilter();

    FilterRamResponse getRamfilter();

    FilterHddResponse getHddfilter();

    FilterSsdResponse getSsdfilter();

    FilterPsuResponse getPsufilter();

    FilterCasesResponse getCasesfilter();

    FilterCoolerResponse getCoolerfilter();

    FilterMainBoardResponse getMainBoardfilter();

    PriceDto getProductPriceDto(String category, long productId);

    List<Integer> getProductPriceOne(String category, long productId);

    Pageable getPageable(int page, int desc);

    PriceDto getProductPeriodPriceDto(long productId, int period);

    boolean isWish(Account account, Product product);
}
