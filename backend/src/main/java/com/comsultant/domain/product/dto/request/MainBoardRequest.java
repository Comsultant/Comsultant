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
public class MainBoardRequest {
    private List<String> corp;
    private List<String> cpuSocket ;
    private List<String> type;
    private List<String> detailChipset;
    private String name;
    private List<Integer> price;
}
