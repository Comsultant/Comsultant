package com.comsultant.domain.builder.api;

import com.comsultant.domain.builder.dto.RecommendBuilderDto;
import com.comsultant.domain.builder.dto.RecommendBuilderResponseDto;
import com.comsultant.domain.builder.service.RecommendService;
import com.comsultant.global.common.response.ListResponse;
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

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendApi {
    private final RecommendService recommendService;
    private final ResponseProperties responseProperties;

    @PostMapping("")
    public ResponseEntity<ListResponse<RecommendBuilderResponseDto>> getRecommendBuilder(
            @AuthenticationPrincipal AccountDetails accountDetails,
            @RequestBody RecommendBuilderDto recommendBuilderDto) {

        List<RecommendBuilderResponseDto> result = recommendService.getRecommendBuilder(accountDetails, recommendBuilderDto);
        return ResponseEntity.status(HttpStatus.OK).body(ListResponse.of(HttpStatus.OK, responseProperties.getSuccess(), result));
    }
}
