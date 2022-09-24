package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RamDto extends ProductDto {

    @Builder
    public RamDto(long idx, int category, String name, int imgCnt, String corp, String registeredAt, String useDevice,
                  String type, String memorySize, double memoryVolume, int actionClock, String lamTiming, double actionVoltage,
                  int lamCnt, boolean led, boolean ondieEcc, boolean xmp, boolean xmp3, boolean tempSensor, String heatsink,
                  boolean reg, boolean lpSize, String heatsinkColor, String ledColor, boolean auraSync, boolean mysticLight,
                  boolean rgbFusion, boolean polychrome, boolean corsairIcue, boolean razerChroma, boolean gskillLighting,
                  boolean ttRgbPlus, boolean teamForceBlitz, boolean xpgRgb, String distCorp) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.useDevice = useDevice;
        this.type = type;
        this.memorySize = memorySize;
        this.memoryVolume = memoryVolume;
        this.actionClock = actionClock;
        this.lamTiming = lamTiming;
        this.actionVoltage = actionVoltage;
        this.lamCnt = lamCnt;
        this.led = led;
        this.ondieEcc = ondieEcc;
        this.xmp = xmp;
        this.xmp3 = xmp3;
        this.tempSensor = tempSensor;
        this.heatsink = heatsink;
        this.reg = reg;
        this.lpSize = lpSize;
        this.heatsinkColor = heatsinkColor;
        this.ledColor = ledColor;
        this.auraSync = auraSync;
        this.mysticLight = mysticLight;
        this.rgbFusion = rgbFusion;
        this.polychrome = polychrome;
        this.corsairIcue = corsairIcue;
        this.razerChroma = razerChroma;
        this.gskillLighting = gskillLighting;
        this.ttRgbPlus = ttRgbPlus;
        this.teamForceBlitz = teamForceBlitz;
        this.xpgRgb = xpgRgb;
        this.distCorp = distCorp;
    }

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
