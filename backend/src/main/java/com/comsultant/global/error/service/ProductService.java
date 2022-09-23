package com.comsultant.global.error.service;

import com.comsultant.domain.product.dto.*;

public interface ProductService {
    ProductDto getProduct(long idx);
    CategoryDto getCategory(long idx);
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
}
