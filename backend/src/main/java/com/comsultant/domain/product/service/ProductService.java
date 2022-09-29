package com.comsultant.domain.product.service;

import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.dto.filterResponse.*;
import com.comsultant.domain.product.dto.request.*;

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

    ProductListDto getCpuList(CpuRequest request, int page);
    ProductListDto getRamList(RamRequest request, int page);
    ProductListDto getVgaList(VgaRequest request, int page);
    ProductListDto getPsuList(PsuRequest request, int page);
    ProductListDto getMainBoardList(MainBoardRequest request, int page);
    ProductListDto getCoolerList(CoolerRequest request, int page);
    ProductListDto getCasesList(CasesRequest request, int page);
    ProductListDto getHddList(HddRequest request, int page);
    ProductListDto getSsdList(SsdRequest request, int page);
    FilterCpuResponse getCpufilter();
    FilterVgaResponse getVgafilter();
    FilterRamResponse getRamfilter();
    FilterHddResponse getHddfilter();
    FilterSsdResponse getSsdfilter();
    FilterPsuResponse getPsufilter();
    FilterCasesResponse getCasesfilter();
    FilterCoolerResponse getCoolerfilter();
    FilterMainBoardResponse getMainBoardfilter();
}
