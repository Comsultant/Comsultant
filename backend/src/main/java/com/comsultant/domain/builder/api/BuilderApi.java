package com.comsultant.domain.builder.api;

import com.comsultant.domain.builder.dto.MyBuilderDto;
import com.comsultant.domain.builder.service.BuilderService;
import com.comsultant.global.common.response.MessageResponse;
import com.comsultant.global.config.security.AccountDetails;
import com.comsultant.global.properties.ResponseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/name")
    public ResponseEntity<MessageResponse> createMyBuilder(@AuthenticationPrincipal AccountDetails accountDetails, @RequestBody MyBuilderDto myBuilderDto) {
        boolean result = builderService.createMyBuilder(accountDetails.getAccount(), myBuilderDto);

        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getFail()));
        }
    }
}
