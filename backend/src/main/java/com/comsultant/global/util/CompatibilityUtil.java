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

                    // Cpu Cooler만
                    if("CPU쿨러".equals(tmp.getType()))
                        cooler = tmp;
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
            case "ATX":
                if(!cases.isStandardAtx())
                    return false;
                break;
            case "M-ATX":
                if(!cases.isMicroAtx())
                    return false;
                break;
            case "E-ATX":
                if(!cases.isExtendedAtx())
                    return false;
                break;
            case "CEB":
                if(!cases.isSsiCeb())
                    return false;
                break;
            case "EEB":
                if(!cases.isSsiEeb())
                    return false;
                break;
            case "M-ITX":
                if(!cases.isMiniItx())
                    return false;
                break;
            case "M-DTX":
                if(!cases.isMiniDtx())
                    return false;
                break;
            case "XL-ATX":
                if(!cases.isExtendedAtx())
                    return false;
                String pciSlot = cases.getPciSlot();
                boolean flag = (pciSlot.contains("8개") || pciSlot.contains("9개") || pciSlot.contains("10개") || pciSlot.contains("12개"));
                if(!flag)
                    return false;
                break;
            default:
                return false;
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

        // cases<->psu 깊이 크기 비교
        if(cases.getPowerMounting() < psu.getDepth())
            return false;
        int pSize = sep(cases.getPowerSize());
        switch (psu.getType()){
            case "ATX 파워":
                if(pSize < 3)
                    return false;
                break;
            case "M-ATX(SFX) 파워":
                if(pSize < 2)
                    return false;
                break;
            case "Flex-ATX 파워":
            case "TFX 파워":
                if(pSize != 100)
                    return false;
                break;
            case "DC to DC":
                if(pSize < 1)
                    return false;
                break;
            case "서버용 파워":
                if(!"랙마운트".equals(cases.getClassType()))
                    return false;
                break;
            case "리던던트":
                if(pSize != 200)
                    return false;
            default:
                break;
        }

        // cases<->cooler 높이 비교
        if(cooler != null && cases.getGpuMounting() < cooler.getCoolerHeight())
            return false;

        return true;
    }

    private int sep(String powerSize) {
        switch (powerSize){
            case "ATX 파워" :
            case "표준-ATX (M-ATX 지원)":
            case "표준-ATX, SFX-L":
            case "표준-ATX, Mini-redundant":
                return 3;
            case "M-ATX(SFX) 파워":
                return 2;
            case "DC to DC":
                return 1;
            case "Flex-ATX 파워":
            case "FLEX, 1U SMPS":
            case "TFX 파워":
                return 100;
            case "리던던트":
                return 200;
            default:
                return 0;
        }
    }
}
