package com.comsultant.domain.product.service;

import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.mapper.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.ProductErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //생성자 주입. final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성. AutoWired 불필요
@Slf4j //로깅 어노테이션
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CasesRepository casesRepository;
    private final CpuRepository cpuRepository;
    private final CoolerRepository coolerRepository;
    private final HddRepository hddRepository;
    private final MainBoardRepository mainBoardRepository;
    private final PsuRepository psuRepository;
    private final RamRepository ramRepository;
    private final SsdRepository ssdRepository;
    private final VgaRepository vgaRepository;

    @Override
    public ProductDto getProduct(long idx) {
        return ProductMapper.mapper.toDto(productRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CpuDto getCpu(long idx) {
        return CpuMapper.mapper.toDto(cpuRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public RamDto getRam(long idx) {
        return RamMapper.mapper.toDto(ramRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CasesDto getCases(long idx) {
        return CasesMapper.mapper.toDto(casesRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public HddDto getHdd(long idx) {
        return HddMapper.mapper.toDto(hddRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public MainBoardDto getMainBoard(long idx) {
        return MainBoardMapper.mapper.toDto(mainBoardRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CoolerDto getCooler(long idx) {
        return CoolerMapper.mapper.toDto(coolerRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public PsuDto getPsu(long idx) {
        return PsuMapper.mapper.toDto(psuRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public SsdDto getSsd(long idx) {
        return SsdMapper.mapper.toDto(ssdRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public VgaDto getVga(long idx) {
        return VgaMapper.mapper.toDto(vgaRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public Object getObject(long type, long idx) {
        Object obj = null;

        if(type == 1) { // 1.cpu, 2.ram, 3.hdd, 4.ssd, 5.psu, 6.cooler, 7.cases, 8.mainboard, 9.vga
            obj = getCpu(idx);
        }else if(type == 2) {
            obj = getRam(idx);
        }else if(type == 3) {
            obj = getHdd(idx);
        }else if(type == 4) {
            obj = getSsd(idx);
        }else if(type == 5) {
            obj = getPsu(idx);
        }else if(type == 6) {
            obj = getCooler(idx);
        }else if(type == 7) {
            obj = getCases(idx);
        }else if(type == 8) {
            obj = getMainBoard(idx);
        }else if(type == 9) {
            obj = getVga(idx);
        }
        return obj;
    }
}
