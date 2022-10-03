package com.comsultant.domain.product.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SsdDto {

    private long idx;

    private int price;

    private String name; //제품명

    private int imgCnt; //img_cnt

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
