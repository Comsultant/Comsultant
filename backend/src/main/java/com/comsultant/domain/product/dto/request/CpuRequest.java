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
public class CpuRequest {
    private List<String> corp;
    private List<String> intelCpu;
    private List<String> amdCpu;
    private List<String> core;
    private List<String> socket;
    private String name;
    private List<Integer> price;
}
