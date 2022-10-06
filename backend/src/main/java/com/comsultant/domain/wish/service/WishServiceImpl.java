package com.comsultant.domain.wish.service;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.product.entity.Product;
import com.comsultant.domain.product.repository.*;
import com.comsultant.domain.product.service.ProductServiceImpl;
import com.comsultant.domain.wish.dto.WishDto;
import com.comsultant.domain.wish.dto.WishListDto;
import com.comsultant.domain.wish.entity.Wish;
import com.comsultant.domain.wish.mapper.WishMapper;
import com.comsultant.domain.wish.repository.WishRepository;
import com.comsultant.global.config.security.AccountDetails;
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
    private final CpuRepository cpuRepository;
    private final RamRepository ramRepository;
    private final SsdRepository ssdRepository;
    private final HddRepository hddRepository;
    private final MainBoardRepository mainBoardRepository;
    private final CoolerRepository coolerRepository;
    private final CasesRepository casesRepository;
    private final VgaRepository vgaRepository;
    private final PsuRepository psuRepository;
    private final ProductServiceImpl productServiceImpl;

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
    public WishListDto getLikes(Account account, int page, boolean desc, AccountDetails accountDetails) {
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

        for (Wish wish : wishes) {
            WishDto wishDto = WishMapper.mapper.toDto(wish);
            Product product = null;
            product = wish.getProduct();
            if(product != null) {
                int category = product.getCategory();
                wishDto.inputCategory(category);
                String name = findName(wish.getProduct().getIdx(), category, accountDetails);
                wishDto.inputProductName(name);
            }
            list.add(wishDto);
        }

        return WishListDto.builder()
                .wishDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public String findName(long idx, int type, AccountDetails accountDetails) {
        if (type == 1) { // 1.cpu, 2.ram, 3.hdd, 4.ssd, 5.psu, 6.cooler, 7.cases, 8.mainboard, 9.vga
            return productServiceImpl.getCpu(idx, accountDetails).getName();
        } else if (type == 2) {
            return productServiceImpl.getRam(idx, accountDetails).getName();
        } else if (type == 3) {
            return productServiceImpl.getHdd(idx, accountDetails).getName();
        } else if (type == 4) {
            return productServiceImpl.getSsd(idx, accountDetails).getName();
        } else if (type == 5) {
            return productServiceImpl.getPsu(idx, accountDetails).getName();
        } else if (type == 6) {
            return productServiceImpl.getCooler(idx, accountDetails).getName();
        } else if (type == 7) {
            return productServiceImpl.getCases(idx, accountDetails).getName();
        } else if (type == 8) {
            return productServiceImpl.getMainBoard(idx, accountDetails).getName();
        } else if (type == 9) {
            return productServiceImpl.getVga(idx, accountDetails).getName();
        }
        return null;
    }
}
