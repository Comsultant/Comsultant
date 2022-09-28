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
public class VgaRequest {
    private List<String> corp;
    private List<String> chipsetCorp ;
    private List<String> nvidia;
    private List<String> amd;
    private List<Double> memoryVolume;
    private String name;
    private List<Integer> price;
}
