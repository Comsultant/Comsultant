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
public class FilterMainBoardResponse {
    private List<String> corp;
    private List<String> cpuSocket ;
    private List<String> type;
    private List<String> detailChipset;
}
