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
public class FilterCasesResponse {
    private List<String> corp;
    private List<String> classType;
    private List<String> size;
    private List<String> powerSize;
}
