package com.comsultant.domain.builder.api;

import com.comsultant.domain.account.entity.Account;
import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.service.BuilderService;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/builder")
@RequiredArgsConstructor
public class BuilderApi {

    private final BuilderService builderService;

    private final ResponseProperties responseProperties;

/*    @PostMapping("")
    public ResponseEntity<MessageResponse> createBuilder(@RequestBody BuilderDto builderDto) {
        boolean result = builderService.createBuilder(builderDto);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }*/

    @PostMapping("")
    public ResponseEntity<MessageResponse> createMyBuilder(@AuthenticationPrincipal AccountDetails accountDetails, @RequestBody MyBuilderDto myBuilderDto) {
        Account account = new Account();
        if (accountDetails != null)
            account = accountDetails.getAccount();

        boolean result = builderService.createMyBuilder(account, myBuilderDto);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
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

}
