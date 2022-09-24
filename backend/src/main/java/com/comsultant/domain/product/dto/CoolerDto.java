package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoolerDto extends ProductDto {

    @Builder
    public CoolerDto(long idx, int category, String name, int imgCnt, String corp, String registeredAt,
                     String coolingSystem, String classType, String type, String thermal, double heatConductivity,
                     String volume, String electricityConductivity, String viscosity, String density, boolean lga3647,
                     boolean lga2066, boolean lga2011V3, boolean lga2011, boolean lga1700, boolean lga1366, boolean lga1200,
                     boolean lga115x, boolean lga775, boolean lga771, boolean lga4677, boolean lga4189, boolean socket478,
                     boolean socket370, boolean tr4, boolean am5, boolean am4, boolean am3, boolean am1, boolean sp3,
                     boolean strx4, boolean socket939, boolean socket754, boolean socket940, boolean swrx8, boolean socketa,
                     boolean socketf, boolean fmxAmx, int tdp, String texture, String radiator, String heatpipe, double coolerHeight,
                     String weight, double fanSize, String fanThickness, String connector, String bearing, int fanSpeedMax,
                     String windVolumeMax, String windPressureMax, double noiseMax, String fanCnt, String fanLife, boolean ledLight,
                     String ledColor, boolean pwm, boolean rgbController, boolean daisychain, boolean zerofan, boolean fanController,
                     boolean waterblock, boolean auraSync, boolean mysticLight, boolean rgbFusion, boolean rgbLed, boolean polychrome,
                     boolean chroma, boolean vivid, boolean biostarSync, boolean software, String asPeriod, boolean custom, boolean packages,
                     boolean remote, boolean indicator, boolean pumpSpeed, String distCorp, boolean lcd) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.coolingSystem = coolingSystem;
        this.classType = classType;
        this.type = type;
        this.thermal = thermal;
        this.heatConductivity = heatConductivity;
        this.volume = volume;
        this.electricityConductivity = electricityConductivity;
        this.viscosity = viscosity;
        this.density = density;
        this.lga3647 = lga3647;
        this.lga2066 = lga2066;
        this.lga2011V3 = lga2011V3;
        this.lga2011 = lga2011;
        this.lga1700 = lga1700;
        this.lga1366 = lga1366;
        this.lga1200 = lga1200;
        this.lga115x = lga115x;
        this.lga775 = lga775;
        this.lga771 = lga771;
        this.lga4677 = lga4677;
        this.lga4189 = lga4189;
        this.socket478 = socket478;
        this.socket370 = socket370;
        this.tr4 = tr4;
        this.am5 = am5;
        this.am4 = am4;
        this.am3 = am3;
        this.am1 = am1;
        this.sp3 = sp3;
        this.strx4 = strx4;
        this.socket939 = socket939;
        this.socket754 = socket754;
        this.socket940 = socket940;
        this.swrx8 = swrx8;
        this.socketa = socketa;
        this.socketf = socketf;
        this.fmxAmx = fmxAmx;
        this.tdp = tdp;
        this.texture = texture;
        this.radiator = radiator;
        this.heatpipe = heatpipe;
        this.coolerHeight = coolerHeight;
        this.weight = weight;
        this.fanSize = fanSize;
        this.fanThickness = fanThickness;
        this.connector = connector;
        this.bearing = bearing;
        this.fanSpeedMax = fanSpeedMax;
        this.windVolumeMax = windVolumeMax;
        this.windPressureMax = windPressureMax;
        this.noiseMax = noiseMax;
        this.fanCnt = fanCnt;
        this.fanLife = fanLife;
        this.ledLight = ledLight;
        this.ledColor = ledColor;
        this.pwm = pwm;
        this.rgbController = rgbController;
        this.daisychain = daisychain;
        this.zerofan = zerofan;
        this.fanController = fanController;
        this.waterblock = waterblock;
        this.auraSync = auraSync;
        this.mysticLight = mysticLight;
        this.rgbFusion = rgbFusion;
        this.rgbLed = rgbLed;
        this.polychrome = polychrome;
        this.chroma = chroma;
        this.vivid = vivid;
        this.biostarSync = biostarSync;
        this.software = software;
        this.asPeriod = asPeriod;
        this.custom = custom;
        this.packages = packages;
        this.remote = remote;
        this.indicator = indicator;
        this.pumpSpeed = pumpSpeed;
        this.distCorp = distCorp;
        this.lcd = lcd;
    }

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String coolingSystem; //냉각방식

    private String classType; //분류

    private String type; //제품 종류

    private String thermal; //써멀 유형

    private double heatConductivity; //열전도율

    private String volume; //용량

    private String electricityConductivity; //전기전도성

    private String viscosity; //점성

    private String density; //밀도

    private boolean lga3647; //LGA3647

    private boolean lga2066; //LGA2066

    private boolean lga2011V3; //LGA2011-V3

    private boolean lga2011; //LGA2011

    private boolean lga1700; //LGA1700

    private boolean lga1366; //LGA1366

    private boolean lga1200; //LGA1200

    private boolean lga115x; //LGA115x

    private boolean lga775; //LGA775

    private boolean lga771; //LGA771

    private boolean lga4677; //LGA4677

    private boolean lga4189; //LGA4189-4/5(소켓P4/P5)

    private boolean socket478; //소켓478

    private boolean socket370; //소켓370

    private boolean tr4; //TR4

    private boolean am5; //AM5

    private boolean am4; //AM4

    private boolean am3; //AM3

    private boolean am1; //AM1

    private boolean sp3; //SP3

    private boolean strx4; //sTRX4

    private boolean socket939; //소켓939

    private boolean socket754; //소켓754

    private boolean socket940; //소켓940

    private boolean swrx8; //sWRX8

    private boolean socketa; //소켓A

    private boolean socketf; //소켓F

    private boolean fmxAmx; //FMx/AMx(AM1/4외)

    private int tdp; //TDP

    private String texture; //재질

    private String radiator; //라디에이터

    private String heatpipe; //히트파이프

    private double coolerHeight; //쿨러 높이

    private String weight; //무게

    private double fanSize; //팬 크기

    private String fanThickness; //팬 두께

    private String connector; //컨넥터

    private String bearing; //베어링

    private int fanSpeedMax; //최대 팬속도

    private String windVolumeMax; //최대 풍량

    private String windPressureMax; //최대 풍압

    private double noiseMax; //최대 소음

    private String fanCnt; //팬 개수

    private String fanLife; //팬수명

    private boolean ledLight; //LED 라이트

    private String ledColor; //LED색상

    private boolean pwm; //PWM 지원

    private boolean rgbController; //RGB 컨트롤러

    private boolean daisychain; //데이지체인

    private boolean zerofan; //제로팬(0-dB기술)

    private boolean fanController; //팬 컨트롤러

    private boolean waterblock; //워터블록/로고 회전

    private boolean auraSync; //AURA SYNC

    private boolean mysticLight; //MYSTIC LIGHT

    private boolean rgbFusion; //RGB FUSION

    private boolean rgbLed; //RGB LED

    private boolean polychrome; //POLYCHROME

    private boolean chroma; //CHROMA

    private boolean vivid; //VIVID

    private boolean biostarSync; //BIOSTAR SYNC

    private boolean software; //제조사 소프트웨어

    private String asPeriod; //A/S기간

    private boolean custom; //수랭 커스텀

    private boolean packages; //패키지 상품

    private boolean remote; //리모콘 지원

    private boolean indicator; //인디게이터

    private boolean pumpSpeed; //펌프속도조절

    private String distCorp; //유통회사

    private boolean lcd; //LCD

}
