package com.comsultant.domain.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishDto {
    private long idx;

    private long accountIdx;

    private long productIdx;

    private String createDate;

    private int category;

    private String productName;

    public void inputCategory(int category) {
        this.category = category;
    }

    public void inputProductName(String productName) {
        this.productName = productName;
    }
}
