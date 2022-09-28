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
public class SsdRequest {
    private List<String> corp;
    private List<String> formFactor;
    private List<Integer> volume;
    private List<String> memoryType;
    private String name;
    private List<Integer> price;
}
