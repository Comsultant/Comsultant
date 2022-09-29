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
public class FilterSsdResponse {
    private List<String> corp;
    private List<String> formFactor;
    private List<Integer> volume;
    private List<String> memoryType;
}
