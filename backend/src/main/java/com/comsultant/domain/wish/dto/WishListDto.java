package com.comsultant.domain.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListDto {
    private List<WishDto> wishDtoList;
    private int totalPage;
}
