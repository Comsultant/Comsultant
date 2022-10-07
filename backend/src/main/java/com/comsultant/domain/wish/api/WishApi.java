package com.comsultant.domain.wish.api;

import com.comsultant.domain.wish.dto.WishListDto;
import com.comsultant.domain.wish.service.WishService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.ParameterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishApi {
    private final WishService wishService;

    private final ResponseProperties responseProperties;

    @PostMapping("/{productId}")
    public ResponseEntity<MessageResponse> createLike(
            @PathVariable("productId") long productIdx,
            @AuthenticationPrincipal AccountDetails accountDetails) {
        boolean result = wishService.createLike(accountDetails.getAccount(), productIdx);
        String str = result ? responseProperties.getSuccess() : responseProperties.getFail();

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, str));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<MessageResponse> deleteLike(
            @PathVariable("productId") long productIdx,
            @AuthenticationPrincipal AccountDetails accountDetails) {
        boolean result = wishService.deleteLike(accountDetails.getAccount(), productIdx);
        String str = result ? responseProperties.getSuccess() : responseProperties.getFail();

        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, str));
    }

    @GetMapping("")
    public ResponseEntity<DtoResponse<WishListDto>> getLikes(
            @RequestParam(name = "page", required = true) String pageParam,
            @RequestParam(name = "desc", required = true) String descParam,
            @AuthenticationPrincipal AccountDetails accountDetails) {

        int page = ParameterUtil.checkPage(pageParam);
        boolean desc = ParameterUtil.checkDesc(descParam);

        WishListDto result = wishService.getLikes(accountDetails.getAccount(), page,desc, accountDetails);

        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }
}
