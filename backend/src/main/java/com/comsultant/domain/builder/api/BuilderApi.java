package com.comsultant.domain.builder.api;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.BuilderProductDto;
import com.comsultant.domain.builder.dto.MyBuilderDetailDto;
import com.comsultant.domain.builder.dto.MyBuilderDetailListDto;
import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.service.BuilderService;
import com.comsultant.global.common.response.DtoResponse;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import com.comsultant.global.util.CompatibilityUtil;
import com.comsultant.global.util.ParameterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/builder")
@RequiredArgsConstructor
public class BuilderApi {

    private final BuilderService builderService;

    private final ResponseProperties responseProperties;

    private final CompatibilityUtil compatibilityUtil;

    @PostMapping("")
    public ResponseEntity<DtoResponse> createMyBuilder(@AuthenticationPrincipal AccountDetails accountDetails, @RequestBody MyBuilderDto myBuilderDto) {
        Account account = new Account();
        if (accountDetails != null)
            account = accountDetails.getAccount();

        MyBuilderDto result = builderService.createMyBuilder(account, myBuilderDto);

        if (result.isCapture()) {
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), null));
        } else if (result.getIdx() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(), null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
        }
    }

    @PostMapping("/capture")
    public ResponseEntity<MessageResponse> capture(@RequestBody MyBuilderDto myBuilderDto) {

        boolean result = builderService.captureBuilder(myBuilderDto);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<DtoResponse<MyBuilderDetailListDto>> getMyBuilderDetailList(@AuthenticationPrincipal AccountDetails accountDetails) {
        MyBuilderDetailListDto result = builderService.getMyBuilderDetails(accountDetails.getAccount());
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("")
    public ResponseEntity<DtoResponse<MyBuilderDetailListDto>> getMyBuilderPageList(@RequestParam(name = "page", required = false) String pageParam, @AuthenticationPrincipal AccountDetails accountDetails) {
        int page = ParameterUtil.checkPage(pageParam);
        MyBuilderDetailListDto result = builderService.getMyBuilderPageList(accountDetails.getAccount(), page);
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @GetMapping("/{myBuilderIdx}")
    public ResponseEntity<DtoResponse<MyBuilderDetailDto>> getBuilder(@PathVariable("myBuilderIdx") long myBuilderIdx, @AuthenticationPrincipal AccountDetails accountDetails) {
        MyBuilderDetailDto result = builderService.getBuilder(myBuilderIdx, accountDetails.getAccount());
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }

    @PatchMapping("/{myBuilderIdx}")
    public ResponseEntity<MessageResponse> updateMyBuilder(@PathVariable("myBuilderIdx") long myBuilderIdx, @RequestBody MyBuilderDto myBuilderDto, @AuthenticationPrincipal AccountDetails accountDetails) {
        boolean result = builderService.reNameMyBuilder(accountDetails.getAccount(), myBuilderIdx, myBuilderDto);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @DeleteMapping("/{myBuilderIdx}")
    public ResponseEntity<MessageResponse> deleteMyBuilder(@PathVariable("myBuilderIdx") long myBuilderIdx, @AuthenticationPrincipal AccountDetails accountDetails) {
        boolean result = builderService.deleteMyBuilder(accountDetails.getAccount(), myBuilderIdx);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }

    @PostMapping("/check")
    public ResponseEntity<MessageResponse> checkCompatibility(@RequestBody Map<String, List<BuilderProductDto>> builderProducts){
        String result = compatibilityUtil.checkCompatibility(builderProducts.get("products"));
        if("success".equals(result)){
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, result));
        }
    }
}
