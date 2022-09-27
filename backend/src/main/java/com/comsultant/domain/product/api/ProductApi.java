package com.comsultant.domain.product.api;

import com.comsultant.domain.comment.dto.CommentListDto;
import com.comsultant.domain.comment.service.CommentService;
import com.comsultant.domain.product.service.ProductService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.ParameterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductApi {
    private final ProductService productService;
    private final ResponseProperties responseProperties;
    private final CommentService commentService;

    @GetMapping("/{productId}")
    public ResponseEntity<DtoResponse<Object>> getProduct(@PathVariable("productId") long idx) {
        long type = productService.getProduct(idx).getCategory();
        Object response = productService.getObject(type, idx);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), response));
    }

    @GetMapping("/comment/{productId}")
    public ResponseEntity<DtoResponse<CommentListDto>> getProductComments(
            @PathVariable("productId") long productId,
            @RequestParam(name = "page", required = false) String pageParam,
            @RequestParam(name = "desc", required = false) String descParam,
            @AuthenticationPrincipal AccountDetails accountDetails) {
            int page = ParameterUtil.checkPage(pageParam);
            boolean desc = ParameterUtil.checkDesc(descParam);
            CommentListDto result = commentService.getProductComments(productId, page, desc);
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }
}
