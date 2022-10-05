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
import java.util.HashSet;
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

    public String checkCompatibility(List<BuilderProductDto> builderProducts){
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
        if(!cpu.getSocket().equals(mb.getCpuSocket()))
            return "Error : cpu and mainboard";

        // mainboard<->cases 크기(ATX) 비교
        switch (mb.getFormFactor()){
            case "ATX":
                if(!cases.isStandardAtx())
                    return "Error : ATX Mainboard";
                break;
            case "M-ATX":
                if(!cases.isMicroAtx())
                    return "Error : M-ATX Mainboard";
                break;
            case "E-ATX":
                if(!cases.isExtendedAtx())
                    return "Error : E-ATX Mainboard";
                break;
            case "CEB":
                if(!cases.isSsiCeb())
                    return "Error : CEB Mainboard";
                break;
            case "EEB":
                if(!cases.isSsiEeb())
                    return "Error : EEB Mainboard";
                break;
            case "M-ITX":
                if(!cases.isMiniItx())
                    return "Error : M-ITX Mainboard";
                break;
            case "M-DTX":
                if(!cases.isMiniDtx())
                    return "Error : M-DTX Mainboard";
                break;
            case "XL-ATX":
                if(!cases.isExtendedAtx())
                    return "Error : XL-ATX Mainboard";
                String pciSlot = cases.getPciSlot();
                boolean flag = (pciSlot.contains("8개") || pciSlot.contains("9개") || pciSlot.contains("10개") || pciSlot.contains("12개"));
                if(!flag)
                    return "Error : XL-ATX Mainboard Not Enough Slot";
                break;
            default:
                return "Error : Unknown mainboard size";
        }

        // mainboard<->ram 규격 개수 비교
        int cnt = 0;
        for(Ram ram : ramList){
            cnt += ram.getLamCnt();
            if(!mb.getMemoryType().equals(ram.getType()) || cnt > mb.getMemorySlot())
                return "Error : mainboard and ram";
        }

        // cases<->vga 가로 비교
        if(vga != null && cases.getGpuMounting() < vga.getWidth())
            return "Error : case and vga";

        // cases<->psu 깊이 크기 비교
        if(cases.getPowerMounting() != 0 && cases.getPowerMounting() < psu.getDepth())
            return "Error : case and psu";
        int pSize = sep(cases.getPowerSize());
        switch (psu.getType()){
            case "ATX 파워":
                if(pSize < 3)
                    return "Error : ATX Power";
                break;
            case "M-ATX(SFX) 파워":
                if(pSize < 2)
                    return "Error : SFX Power";
                break;
            case "Flex-ATX 파워":
            case "TFX 파워":
                if(pSize != 100)
                    return "Error : TFX, FATX Power";
                break;
            case "DC to DC":
                if(pSize < 1)
                    return "Error : DC Power";
                break;
            case "서버용 파워":
                if(!"랙마운트".equals(cases.getClassType()))
                    return "Error : Server Power";
                break;
            case "리던던트":
                if(pSize != 200)
                    return "Error : Redundant Power";
            default:
                break;
        }

        // cases<->cooler 높이 비교
        if(cooler != null && "공랭".equals(cooler.getCoolingSystem()) && cases.getGpuMounting() < cooler.getCoolerHeight())
            return "Error : case and cooler";
        else if(cooler != null && "수랭".equals(cooler.getCoolingSystem()))
            return "수랭 쿨러는 미완..."
        return "success";
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
