package com.comsultant.domain.product.api;

import com.comsultant.domain.product.service.ProductService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductApi {
    private final ProductService productService;

    private final ResponseProperties responseProperties;

    @GetMapping("/{productId}")
    public ResponseEntity<DtoResponse<Object>> getProduct(@PathVariable("productId") long idx) {
        long type = productService.getProduct(idx).getType();
        Object response = productService.getObject(type, idx);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), response));
    }
}
