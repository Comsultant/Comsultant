package com.comsultant.global.util;

import com.comsultant.domain.builder.dto.BuilderProductDto;
import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.domain.product.service.ProductService;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.ProductErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompatibilityUtil {
    private final ProductService productService;
    private final CpuRepository cpuRepository;
    private final RamRepository ramRepository;
    private final HddRepository hddRepository;
    private final SsdRepository ssdRepository;
    private final PsuRepository psuRepository;
    private final CoolerRepository coolerRepository;
    private final CasesRepository casesRepository;
    private final MainBoardRepository mainBoardRepository;
    private final VgaRepository vgaRepository;

    public boolean checkCompatibility(List<BuilderProductDto> builderProducts){
        Cpu cpu = null;
        ArrayList<Ram> ramList = new ArrayList<>();
        ArrayList<Hdd> hddList = new ArrayList<>();
        ArrayList<Ssd> ssdList = new ArrayList<>();
        Psu psu = null;
        Cooler cooler = null;
        Cases cases = null;
        MainBoard mb = null;
        Vga vga = null;

        // 제품 검색
        for(BuilderProductDto product : builderProducts){
            long pIdx = product.getProductIdx();
            long type = productService.getProduct(pIdx).getCategory();
            switch ((int) type){
                case 1:
                    cpu = cpuRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    break;
                case 2:
                    Ram ram = ramRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    ramList.add(ram);
                    break;
                case 3:
                    Hdd hdd = hddRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    hddList.add(hdd);
                    break;
                case 4:
                    Ssd ssd = ssdRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    ssdList.add(ssd);
                    break;
                case 5:
                    psu = psuRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    break;
                case 6:
                    Cooler tmp = coolerRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    if(tmp.getType())
                    break;
                case 7:
                    cases = casesRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    break;
                case 8:
                    mb = mainBoardRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    break;
                case 9:
                    vga = vgaRepository.findByIdx(pIdx).orElseThrow(
                            () -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)
                    );
                    break;
                default:
                    break;
            }
        }

        // 호환성 검사
        // cpu<->mainboard 제조사 소켓 비교
        if(!cpu.getCorp().equals(mb.getCorp()) || !cpu.getSocket().equals(mb.getCpuSocket()))
            return false;

        // mainboard<->cases 크기(ATX) 비교
        switch (mb.getFormFactor()){


        }

        // mainboard<->ram 규격 개수 비교
        int cnt = 0;
        for(Ram ram : ramList){
            cnt += ram.getLamCnt();
            if(!mb.getMemoryType().equals(ram.getType()) || cnt > mb.getMemorySlot())
                return false;
        }

        // cases<->vga 가로 비교
        if(vga != null && cases.getGpuMounting() < vga.getWidth())
            return false;

        // cases<->psu 깊이 비교
        if(cases.getPowerMounting() < psu.getDepth())
            return false;

        // cases<->cooler 높이 비교
        if(cooler != null && cases.getGpuMounting() < cooler.getCoolerHeight())
            return false;

        return true;
    }
}
