package com.comsultant.domain.product.dto.filterResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterCpuResponse {
    private List<String> corp;
    private List<String> intelCpu;
    private List<String> amdCpu;
    private List<String> core;
    private List<String> socket;
}
