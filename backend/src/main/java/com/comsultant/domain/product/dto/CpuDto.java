package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CpuDto extends ProductDto {

    @Builder
    public CpuDto(long idx, int category, String name,
                  int imgCnt, String corp, String registeredAt, String intelCpu, String amdCpu, String generation,
                  String socket, String createdAt, String nano, String core, String threadCnt, double clockOriginal,
                  double clockMax, String l2cash, String l3cash, String arithmeticSystem, String busSpeed, int tdp, int tdpMax,
                  String pcieVersion, String pcieLane, int memoryMaxSize, String memoryFrame, String memoryClock, boolean memoryChannel,
                  String includeVga, String includeVgaName, int includeVgaCoreSpeed, boolean hyperThreading, boolean optane,
                  boolean storemi, boolean sensemi, boolean ryzenMaster, boolean vrReady, boolean vcash3d, String packageType, String includeCooler) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.intelCpu = intelCpu;
        this.amdCpu = amdCpu;
        this.generation = generation;
        this.socket = socket;
        this.createdAt = createdAt;
        this.nano = nano;
        this.core = core;
        this.threadCnt = threadCnt;
        this.clockOriginal = clockOriginal;
        this.clockMax = clockMax;
        this.l2cash = l2cash;
        this.l3cash = l3cash;
        this.arithmeticSystem = arithmeticSystem;
        this.busSpeed = busSpeed;
        this.tdp = tdp;
        this.tdpMax = tdpMax;
        this.pcieVersion = pcieVersion;
        this.pcieLane = pcieLane;
        this.memoryMaxSize = memoryMaxSize;
        this.memoryFrame = memoryFrame;
        this.memoryClock = memoryClock;
        this.memoryChannel = memoryChannel;
        this.includeVga = includeVga;
        this.includeVgaName = includeVgaName;
        this.includeVgaCoreSpeed = includeVgaCoreSpeed;
        this.hyperThreading = hyperThreading;
        this.optane = optane;
        this.storemi = storemi;
        this.sensemi = sensemi;
        this.ryzenMaster = ryzenMaster;
        this.vrReady = vrReady;
        this.vcash3d = vcash3d;
        this.packageType = packageType;
        this.includeCooler = includeCooler;
    }

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String intelCpu; //인텔 CPU종류

    private String amdCpu; //AMD CPU종류

    private String generation; //세대 구분

    private String socket; //소켓 구분

    private String createdAt; //출시일

    private String nano; //제조 공정

    private String core; //코어 수

    private String threadCnt; //쓰레드 수

    private double clockOriginal; //기본 클럭

    private double clockMax; //최대 클럭

    private String l2cash; //L2 캐시

    private String l3cash; //L3 캐시

    private String arithmeticSystem; //연산 체계

    private String busSpeed; //버스 속도

    private int tdp; //TDP

    private int tdpMax; //최대 TDP

    private String pcieVersion; //PCIe 버전

    private String pcieLane; //최대 PCIe 레인수

    private int memoryMaxSize; //최대 메모리 크기

    private String memoryFrame; //메모리 규격

    private String memoryClock; //메모리 클럭

    private boolean memoryChannel; //메모리 채널

    private String includeVga; //내장그래픽

    private String includeVgaName; //GPU 모델명

    private int includeVgaCoreSpeed; //GPU 코어 속도

    private boolean hyperThreading; //하이퍼스레딩

    private boolean optane; //옵테인

    private boolean storemi; //StoreMI

    private boolean sensemi; //SenseMI

    private boolean ryzenMaster; //Ryzen Master

    private boolean vrReady; //VR Ready 프리미엄

    private boolean vcash3d; //3D V캐시

    private String packageType; //패키지 형태

    private String includeCooler; //쿨러

}
