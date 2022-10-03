package com.comsultant.domain.product.service;

import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.dto.filterResponse.*;
import com.comsultant.domain.product.dto.request.*;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto getProduct(long idx);
    CpuDto getCpu(long idx);
    RamDto getRam(long idx);
    CasesDto getCases(long idx);
    HddDto getHdd(long idx);
    MainBoardDto getMainBoard(long idx);
    CoolerDto getCooler(long idx);
    PsuDto getPsu(long idx);
    SsdDto getSsd(long idx);
    VgaDto getVga(long idx);

    Object getObject(long type, long idx);

    ProductListDto getCpuList(CpuRequest request, int page, int desc);
    ProductListDto getRamList(RamRequest request, int page, int desc);
    ProductListDto getVgaList(VgaRequest request, int page, int desc);
    ProductListDto getPsuList(PsuRequest request, int page, int desc);
    ProductListDto getMainBoardList(MainBoardRequest request, int page, int desc);
    ProductListDto getCoolerList(CoolerRequest request, int page, int desc);
    ProductListDto getCasesList(CasesRequest request, int page, int desc);
    ProductListDto getHddList(HddRequest request, int page, int desc);
    ProductListDto getSsdList(SsdRequest request, int page, int desc);
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
    int getProductPriceOne(String category, long productId);
    public Pageable getPageable(int page, int desc);
}
