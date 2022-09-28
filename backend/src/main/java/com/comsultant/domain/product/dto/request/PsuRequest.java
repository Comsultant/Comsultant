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
public class PsuRequest {
    private List<String> corp;
    private List<String> type ;
    private List<Integer> ratedPower;
    private String name;
    private List<Integer> price;
}
