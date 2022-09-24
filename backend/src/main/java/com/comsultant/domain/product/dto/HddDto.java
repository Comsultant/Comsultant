package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HddDto extends ProductDto {

    @Builder
    public HddDto(long idx, int category, String name, int imgCnt, String corp, String registeredAt,
                  String type, String diskSize, int diskVolume, String interfaces, int rotate,
                  int bufferSize, int transSpeed, String recordMethod, int diskCnt, double thickness,
                  boolean heliumCharge, boolean rvSensor, boolean dsa, boolean lowPower, boolean smart,
                  boolean ise, boolean sed, int loadAmount, int guarantee, String noise, boolean year5, boolean year3,
                  boolean year2, boolean year1, boolean restore3, boolean restore5, boolean restore2, boolean restore1) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.type = type;
        this.diskSize = diskSize;
        this.diskVolume = diskVolume;
        this.interfaces = interfaces;
        this.rotate = rotate;
        this.bufferSize = bufferSize;
        this.transSpeed = transSpeed;
        this.recordMethod = recordMethod;
        this.diskCnt = diskCnt;
        this.thickness = thickness;
        this.heliumCharge = heliumCharge;
        this.rvSensor = rvSensor;
        this.dsa = dsa;
        this.lowPower = lowPower;
        this.smart = smart;
        this.ise = ise;
        this.sed = sed;
        this.loadAmount = loadAmount;
        this.guarantee = guarantee;
        this.noise = noise;
        this.year5 = year5;
        this.year3 = year3;
        this.year2 = year2;
        this.year1 = year1;
        this.restore3 = restore3;
        this.restore5 = restore5;
        this.restore2 = restore2;
        this.restore1 = restore1;
    }

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
