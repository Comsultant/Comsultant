package com.comsultant.domain.product.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RamDto {

    private long idx;

    private int price;

    private String name; //제품명

    private int imgCnt; //img_cnt

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String useDevice; //사용 장치

    private String type; //제품 분류

    private String memorySize; //메모리 규격

    private double memoryVolume; //메모리 용량

    private int actionClock; //동작클럭(대역폭)

    private String lamTiming; //램타이밍

    private double actionVoltage; //동작전압

    private int lamCnt; //램개수

    private boolean led; //LED 라이트

    private boolean ondieEcc; //온다이ECC

    private boolean xmp; //XMP

    private boolean xmp3; //XMP3.0

    private boolean tempSensor; //온도센서

    private String heatsink; //히트싱크

    private boolean reg; //REG

    private boolean lpSize; //LP 사이즈

    private String heatsinkColor; //방열판 색상

    private String ledColor; //LED 색상

    private boolean auraSync; //AURA SYNC

    private boolean mysticLight; //MYSTIC LIGHT

    private boolean rgbFusion; //RGB FUSION

    private boolean polychrome; //POLYCHROME

    private boolean corsairIcue; //CORSAIR iCUE

    private boolean razerChroma; //RAZER CHROMA

    private boolean gskillLighting; //G.SKILL Lighting

    private boolean ttRgbPlus; //TT RGB PLUS

    private boolean teamForceBlitz; //T-FORCE-BLITZ

    private boolean xpgRgb; //XPG RGB

    private String distCorp; //유통회사

}
