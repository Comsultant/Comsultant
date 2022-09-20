package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HddDto {

    private long idx;

    private String name; //제품명

    private int imgCnt; //img_cnt

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String type; //제품 분류

    private String diskSize; //디스크 크기

    private int diskVolume; //디스크 용량

    private String interfaces; //인터페이스

    private int rotate; //회전수

    private int bufferSize; //버퍼 용량

    private int transSpeed; //전송 속도

    private String recordMethod; //기록 방식

    private int diskCnt; //디스크 수

    private double thickness; //두께

    private boolean heliumCharge; //헬륨충전

    private boolean rvSensor; //RV 센서

    private boolean dsa; //DSA

    private boolean lowPower; //저전력

    private boolean smart; //S.M.A.R.T 지원

    private boolean ise; //ISE

    private boolean sed; //SED

    private int loadAmount; //작업부하량

    private int guarantee; //사용보증

    private String noise; //소음(유휴/탐색)

    private boolean year5; //5년

    private boolean year3; //3년

    private boolean year2; //2년

    private boolean year1; //1년

    private boolean restore3; //데이터 복구 3년

    private boolean restore5; //데이터 복구 5년

    private boolean restore2; //데이터 복구 2년

    private boolean restore1; //데이터 복구 1년

}
