package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CasesDto extends ProductDto {

    @Builder
    public CasesDto(long idx, int category, String name, int imgCnt, String corp, String registeredAt, String classType,
                    String detailClass, String size, String power, String powerSize, boolean extendedAtx, boolean standardAtx,
                    boolean microAtx, boolean flexAtx, boolean standardItx, boolean miniItx, boolean ssiCeb, boolean ssiEeb,
                    boolean miniDtx, String bay13, int bay8, int bay6, int storageDevice, String pciSlot, String verticalPciSlot,
                    int coolingFan, int ledFan, String side, String back, String front, String top, String bottom, String innerTop,
                    String innerSide, String filter, String sound, boolean usb, boolean usb3, boolean typec3, boolean typec31,
                    boolean typea31, boolean ieee, boolean esata, boolean hdmi, boolean cardReader, double width, double deepth,
                    double height, int powerMounting, String powerLocation, int gpuMounting, int cpuCoolerMounting, int waterCoolerSize,
                    String radiatorTop, String radiatorFront, String radiatorBack, String caseColorType, boolean outsideLedControll,
                    boolean rgbControll, boolean outsideLed) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.classType = classType;
        this.detailClass = detailClass;
        this.size = size;
        this.power = power;
        this.powerSize = powerSize;
        this.extendedAtx = extendedAtx;
        this.standardAtx = standardAtx;
        this.microAtx = microAtx;
        this.flexAtx = flexAtx;
        this.standardItx = standardItx;
        this.miniItx = miniItx;
        this.ssiCeb = ssiCeb;
        this.ssiEeb = ssiEeb;
        this.miniDtx = miniDtx;
        this.bay13 = bay13;
        this.bay8 = bay8;
        this.bay6 = bay6;
        this.storageDevice = storageDevice;
        this.pciSlot = pciSlot;
        this.verticalPciSlot = verticalPciSlot;
        this.coolingFan = coolingFan;
        this.ledFan = ledFan;
        this.side = side;
        this.back = back;
        this.front = front;
        this.top = top;
        this.bottom = bottom;
        this.innerTop = innerTop;
        this.innerSide = innerSide;
        this.filter = filter;
        this.sound = sound;
        this.usb = usb;
        this.usb3 = usb3;
        this.typec3 = typec3;
        this.typec31 = typec31;
        this.typea31 = typea31;
        this.ieee = ieee;
        this.esata = esata;
        this.hdmi = hdmi;
        this.cardReader = cardReader;
        this.width = width;
        this.deepth = deepth;
        this.height = height;
        this.powerMounting = powerMounting;
        this.powerLocation = powerLocation;
        this.gpuMounting = gpuMounting;
        this.cpuCoolerMounting = cpuCoolerMounting;
        this.waterCoolerSize = waterCoolerSize;
        this.radiatorTop = radiatorTop;
        this.radiatorFront = radiatorFront;
        this.radiatorBack = radiatorBack;
        this.caseColorType = caseColorType;
        this.outsideLedControll = outsideLedControll;
        this.rgbControll = rgbControll;
        this.outsideLed = outsideLed;
    }

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String classType; //제품 분류

    private String detailClass; //제품 상세 분류

    private String size; //케이스 크기

    private String power; //파워포함여부

    private String powerSize; //지원파워규격

    private boolean extendedAtx; //Extended-ATX

    private boolean standardAtx; //표준-ATX

    private boolean microAtx; //Micro-ATX

    private boolean flexAtx; //Flex-ATX

    private boolean standardItx; //표준-ITX

    private boolean miniItx; //Mini-ITX

    private boolean ssiCeb; //SSI-CEB

    private boolean ssiEeb; //SSI-EEB

    private boolean miniDtx; //Mini-DTX

    private String bay13; //13.3cm 베이

    private int bay8; //8.9cm 베이

    private int bay6; //6.4cm 베이

    private int storageDevice; //저장장치 장착

    private String pciSlot; //PCI 슬롯

    private String verticalPciSlot; //수직 PCI슬롯

    private int coolingFan; //쿨링팬

    private int ledFan; //LED팬

    private String side; //측면

    private String back; //후면

    private String front; //전면

    private String top; //상단

    private String bottom; //하단

    private String innerTop; //내부 상단

    private String innerSide; //내부 측면

    private String filter; //먼지필터

    private String sound; //사운드

    private boolean usb; //USB

    private boolean usb3; //USB 3.0

    private boolean typec3; //USB 3.0 (Type-C)

    private boolean typec31; //USB 3.1 (Type-C)

    private boolean typea31; //USB 3.1 (Type-A)

    private boolean ieee; //IEEE1394

    private boolean esata; //eSATA

    private boolean hdmi; //HDMI

    private boolean cardReader; //카드리더기

    private double width; //너비(W)

    private double deepth; //깊이(D)

    private double height; //높이(H)

    private int powerMounting; //파워장착

    private String powerLocation; //파워 위치

    private int gpuMounting; //GPU 장착

    private int cpuCoolerMounting; //CPU쿨러 장착

    private int waterCoolerSize; //수냉쿨러 규격

    private String radiatorTop; //라디에이터(상단)

    private String radiatorFront; //라디에이터(전면)

    private String radiatorBack; //라디에이터(후면)

    private String caseColorType; //케이스 색상 계열

    private boolean outsideLedControll; //외부 LED 컨트롤

    private boolean rgbControll; //RGB 컨트롤

    private boolean outsideLed; //외부 LED

}
