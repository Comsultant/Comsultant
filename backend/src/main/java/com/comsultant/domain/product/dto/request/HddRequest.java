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
public class HddRequest {
    private List<String> corp;
    private List<Integer> diskVolume;
    private String name;
    private List<Integer> price;
}
