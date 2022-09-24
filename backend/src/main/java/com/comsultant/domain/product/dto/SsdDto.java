package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SsdDto extends ProductDto {

    @Builder
    public SsdDto(long idx, int category, String name, int imgCnt, String registeredAt, String corp, String type,
                  String detailType, String formFactor, String interfaces, String protocol, int volume, String memoryType,
                  String nandStructure, String ram, String ramType, String controller, String consoleGame, String process,
                  String sizeChange, String interfaceChange, int seqRead, int seqWrite, double readIops, double writeIops,
                  boolean trim, boolean gc, boolean slcCashing, boolean smart, boolean ecc, boolean devslp, boolean aes,
                  boolean privateSw, double tbw, boolean macbookUpgrade, boolean migration, boolean manageFunction,
                  String mtbf, boolean year5, boolean year3, boolean restore3, boolean restore1, boolean limitRestore,
                  String nvmeHeatsink, String width, String height, String thickness, String weight, String distCorp,
                  boolean type25, boolean type35, boolean miniSata, String installCnt, boolean m22230, boolean m22242,
                  boolean m22260, boolean m22280, boolean m222110, String supportThickness, String useClass, String powerSupply,
                  boolean protect, boolean white, boolean yellow, boolean green, boolean purple, boolean grey, boolean blue,
                  boolean black, boolean silver, boolean waterproof, String texture, boolean locked, boolean subPower) {
        super(idx, category, name, imgCnt);
        this.registeredAt = registeredAt;
        this.corp = corp;
        this.type = type;
        this.detailType = detailType;
        this.formFactor = formFactor;
        this.interfaces = interfaces;
        this.protocol = protocol;
        this.volume = volume;
        this.memoryType = memoryType;
        this.nandStructure = nandStructure;
        this.ram = ram;
        this.ramType = ramType;
        this.controller = controller;
        this.consoleGame = consoleGame;
        this.process = process;
        this.sizeChange = sizeChange;
        this.interfaceChange = interfaceChange;
        this.seqRead = seqRead;
        this.seqWrite = seqWrite;
        this.readIops = readIops;
        this.writeIops = writeIops;
        this.trim = trim;
        this.gc = gc;
        this.slcCashing = slcCashing;
        this.smart = smart;
        this.ecc = ecc;
        this.devslp = devslp;
        this.aes = aes;
        this.privateSw = privateSw;
        this.tbw = tbw;
        this.macbookUpgrade = macbookUpgrade;
        this.migration = migration;
        this.manageFunction = manageFunction;
        this.mtbf = mtbf;
        this.year5 = year5;
        this.year3 = year3;
        this.restore3 = restore3;
        this.restore1 = restore1;
        this.limitRestore = limitRestore;
        this.nvmeHeatsink = nvmeHeatsink;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.weight = weight;
        this.distCorp = distCorp;
        this.type25 = type25;
        this.type35 = type35;
        this.miniSata = miniSata;
        this.installCnt = installCnt;
        this.m22230 = m22230;
        this.m22242 = m22242;
        this.m22260 = m22260;
        this.m22280 = m22280;
        this.m222110 = m222110;
        this.supportThickness = supportThickness;
        this.useClass = useClass;
        this.powerSupply = powerSupply;
        this.protect = protect;
        this.white = white;
        this.yellow = yellow;
        this.green = green;
        this.purple = purple;
        this.grey = grey;
        this.blue = blue;
        this.black = black;
        this.silver = silver;
        this.waterproof = waterproof;
        this.texture = texture;
        this.locked = locked;
        this.subPower = subPower;
    }

    private String registeredAt; //등록년월

    private String corp; //제조회사

    private String type; //제품분류

    private String detailType; //상세분류

    private String formFactor; //폼팩터

    private String interfaces; //인터페이스

    private String protocol; //프로토콜

    private int volume; //용량

    private String memoryType; //메모리 타입

    private String nandStructure; //낸드 구조

    private String ram; //RAM 유무

    private String ramType; //RAM 타입

    private String controller; //컨트롤러

    private String consoleGame; //콘솔게임기 호환

    private String process; //공정

    private String sizeChange; //크기 변환

    private String interfaceChange; //인터페이스 변환

    private int seqRead; //순차읽기

    private int seqWrite; //순차쓰기

    private double readIops; //읽기IOPS

    private double writeIops; //쓰기IOPS

    private boolean trim; //TRIM

    private boolean gc; //GC

    private boolean slcCashing; //SLC캐싱

    private boolean smart; //S.M.A.R.T

    private boolean ecc; //ECC

    private boolean devslp; //DEVSLP

    private boolean aes; //AES 암호화

    private boolean privateSw; //전용 S/W

    private double tbw; //TBW

    private boolean macbookUpgrade; //맥북 업그레이드용

    private boolean migration; //마이그레이션

    private boolean manageFunction; //관리기능

    private String mtbf; //MTBF

    private boolean year5; //5년

    private boolean year3; //3년

    private boolean restore3; //데이터 복구 3년

    private boolean restore1; //데이터 복구 1년

    private boolean limitRestore; //제한보증

    private String nvmeHeatsink; //NVMe 방열판

    private String width; //가로

    private String height; //세로

    private String thickness; //두께

    private String weight; //무게

    private String distCorp; //유통회사

    private boolean type25; //2.5형(6.4cm)

    private boolean type35; //3.5형(8.9cm)

    private boolean miniSata; //Mini SATA(mSATA)

    private String installCnt; //보관(장착) 개수

    private boolean m22230; //M.2 (2230)

    private boolean m22242; //M.2 (2242)

    private boolean m22260; //M.2 (2260)

    private boolean m22280; //M.2 (2280)

    private boolean m222110; //M.2 (22110)

    private String supportThickness; //지원 두께

    private String useClass; //용도 구분

    private String powerSupply; //전원공급

    private boolean protect; //충격보호

    private boolean white; //화이트

    private boolean yellow; //옐로우

    private boolean green; //그린

    private boolean purple; //퍼플

    private boolean grey; //그레이

    private boolean blue; //블루

    private boolean black; //블랙

    private boolean silver; //실버

    private boolean waterproof; //방수

    private String texture; //제품재질

    private boolean locked; //잠금장치

    private boolean subPower; //보조전원

}
