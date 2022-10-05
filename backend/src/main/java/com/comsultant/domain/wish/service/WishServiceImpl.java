package com.comsultant.domain.wish.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.ProductRepository;
import com.comsultant.domain.wish.dto.WishDto;
import com.comsultant.domain.wish.dto.WishListDto;
import com.comsultant.domain.wish.entity.Wish;
import com.comsultant.domain.wish.mapper.WishMapper;
import com.comsultant.domain.wish.repository.WishRepository;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.ProductErrorCode;
import com.comsultant.global.properties.ConstProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor //생성자 주입. final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성. AutoWired 불필요
@Slf4j //로깅 어노테이션
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final ConstProperties constProperties;

    @Override
    public boolean createLike(Account account, long productIdx) {
        if(account == null || account.getIdx() == 0)
            return false;
        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND));
        if(wishRepository.findByAccountAndProduct(account, product) != null)
            return false;
        Wish savedWish = wishRepository.save(Wish.builder().account(account).product(product).build());
        if (savedWish.getIdx() == 0)
            return false;
        return true;
    }

    @Override
    public boolean deleteLike(Account account, long productIdx) {
        if(account == null || account.getIdx() == 0)
            return false;
        Product product = productRepository.findById(productIdx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND));
        Wish wish = wishRepository.findByAccountAndProduct(account, product);
        if(wish == null)
            return false;
        wishRepository.deleteById(wish.getIdx());
        return true;
    }

    @Override
    public WishListDto getLikes(Account account, int page, boolean desc) {
        if(account == null || account.getIdx() == 0) {
            return null;
        }
        Pageable pageable;

        if (desc)
            pageable = PageRequest.of(page, constProperties.getWishListSize(), Sort.by("idx").descending()); //시작 위치, 몇 개씩, sort
        else
            pageable = PageRequest.of(page, constProperties.getWishListSize());

        Page<Wish> pageLikes = wishRepository.findAllByAccount(account, pageable);
        List<Wish> wishes = pageLikes.getContent();
        int totalPages = pageLikes.getTotalPages();

        List<WishDto> list = new ArrayList<>();

        for (Wish wish : wishes)
            list.add(WishMapper.mapper.toDto(wish));

        return WishListDto.builder()
                .wishDtoList(list)
                .totalPage(totalPages)
                .build();
    }
}
