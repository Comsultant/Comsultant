package com.comsultant.domain.product.api;

import com.comsultant.domain.comment.dto.CommentListDto;
import com.comsultant.domain.comment.service.CommentService;
import com.comsultant.domain.product.dto.ProductListDto;
import com.comsultant.domain.product.dto.filterResponse.*;
import com.comsultant.domain.product.dto.request.*;
import com.comsultant.domain.product.service.ProductService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.ParameterUtil;
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
            @RequestParam(name = "desc", required = false) String descParam) {
            int page = ParameterUtil.checkPage(pageParam);
            boolean desc = ParameterUtil.checkDesc(descParam);
            CommentListDto result = commentService.getProductComments(productId, page, desc);
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/cpu")
    public ResponseEntity<DtoResponse<ProductListDto>> getCpuList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody CpuRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getCpuList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/vga")
    public ResponseEntity<DtoResponse<ProductListDto>> getVgaList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody VgaRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getVgaList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/mainboard")
    public ResponseEntity<DtoResponse<ProductListDto>> getMainBoardList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody MainBoardRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getMainBoardList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/psu")
    public ResponseEntity<DtoResponse<ProductListDto>> getPsuList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody PsuRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getPsuList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/ram")
    public ResponseEntity<DtoResponse<ProductListDto>> getRamList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody RamRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getRamList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/hdd")
    public ResponseEntity<DtoResponse<ProductListDto>> getHddList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody HddRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getHddList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/ssd")
    public ResponseEntity<DtoResponse<ProductListDto>> getSsdList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody SsdRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getSsdList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/cases")
    public ResponseEntity<DtoResponse<ProductListDto>> getCasesList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody CasesRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getCasesList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/cooler")
    public ResponseEntity<DtoResponse<ProductListDto>> getCoolerList(
            @RequestParam(name = "page") String pageParam,
            @RequestBody CoolerRequest request) {
        int page = ParameterUtil.checkPage(pageParam);
        ProductListDto result = productService.getCoolerList(request, page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/cpu")
    public ResponseEntity<DtoResponse<FilterCpuResponse>> getCpuFilter() {
        FilterCpuResponse result = productService.getCpufilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/vga")
    public ResponseEntity<DtoResponse<FilterVgaResponse>> getVgaFilter() {
        FilterVgaResponse result = productService.getVgafilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/ram")
    public ResponseEntity<DtoResponse<FilterRamResponse>> getRamFilter() {
        FilterRamResponse result = productService.getRamfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/hdd")
    public ResponseEntity<DtoResponse<FilterHddResponse>> getHddFilter() {
        FilterHddResponse result = productService.getHddfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/ssd")
    public ResponseEntity<DtoResponse<FilterSsdResponse>> getSsdFilter() {
        FilterSsdResponse result = productService.getSsdfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/mainboard")
    public ResponseEntity<DtoResponse<FilterMainBoardResponse>> getMainBoardFilter() {
        FilterMainBoardResponse result = productService.getMainBoardfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/cooler")
    public ResponseEntity<DtoResponse<FilterCoolerResponse>> getCoolerFilter() {
        FilterCoolerResponse result = productService.getCoolerfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/psu")
    public ResponseEntity<DtoResponse<FilterPsuResponse>> getPsuFilter() {
        FilterPsuResponse result = productService.getPsufilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/filter/cases")
    public ResponseEntity<DtoResponse<FilterCasesResponse>> getCasesFilter() {
        FilterCasesResponse result = productService.getCasesfilter();
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }
}