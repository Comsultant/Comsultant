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
public class CasesRequest {
    private List<String> corp;
    private List<String> classType;
    private List<String> size;
    private List<String> powerSize;
    private boolean extendedAtx;
    private boolean standardAtx;
    private boolean microAtx;
    private boolean flexAtx;
    private boolean standardItx;
    private boolean miniItx;
    private boolean ssiCeb;
    private boolean ssiEeb;
    private boolean miniDtx;
    private String name;
    private List<Integer> price;
    //지원보드규격 : 프론트에서 관리 (Extended_ATX, 표준-ATX, Micro-ATX, Flex-ATX, 표준-ITX, Mini-ITX, SSI-CEB, SSI-EEB, Mini-DTX)
}
