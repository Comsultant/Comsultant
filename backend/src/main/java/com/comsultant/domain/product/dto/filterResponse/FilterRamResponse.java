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
public class FilterRamResponse {
    private List<String> corp;
    private List<String> useDevice;
    private List<String> type;
    private List<Double> memoryVolume;
}
