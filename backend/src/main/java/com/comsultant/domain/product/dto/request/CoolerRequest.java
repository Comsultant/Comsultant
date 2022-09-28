package com.comsultant.domain.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoolerRequest {
    private List<String> corp;
    private List<String> type ;
    private List<String> coolingSystem ;
    private List<Double> coolerHeight ;
    private boolean lga3647; //LGA3647
    private boolean lga2066; //LGA2066
    private boolean lga2011V3; //LGA2011-V3
    private boolean lga2011; //LGA2011
    private boolean lga1700; //LGA1700
    private boolean lga1366; //LGA1366
    private boolean lga1200; //LGA1200
    private boolean lga115x; //LGA115x
    private boolean lga775; //LGA775
    private boolean lga771; //LGA771
    private boolean lga4677; //LGA4677
    private boolean lga4189; //LGA4189-4/5(소켓P4/P5)
    private boolean socket478; //소켓478
    private boolean socket370; //소켓370
    private boolean tr4; //TR4
    private boolean am5; //AM5
    private boolean am4; //AM4
    private boolean am3; //AM3
    private boolean am1; //AM1
    private boolean sp3; //SP3
    private boolean strx4; //sTRX4
    private boolean socket939; //소켓939
    private boolean socket754; //소켓754
    private boolean socket940; //소켓940
    private boolean swrx8; //sWRX8
    private boolean socketa; //소켓A
    private boolean socketf; //소켓F
    private boolean fmxAmx; //FMx/AMx(AM1/4외)
    private String name;
    private List<Integer> price;
    //인텔소켓 (프론트에서 관리)/ AMD 소켓 (프론트에서 관리)
}
