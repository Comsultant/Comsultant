package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PsuDto {

    private long idx;

    private String name; //제품명

    private int imgCnt; //img_cnt

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String type; //제품 분류

    private int ratedPower; //정격출력

    private String plusCert; //80PLUS인증

    private String etaCert; //ETA인증

    private String lambdaCert; //LAMBDA인증

    private String volChange; //전압변동

    private String outputMeth12v; //+12V 출력방식

    private int avail12v; //+12V 가용률

    private String pfcCircuit; //PFC회로

    private int pf; //PF(역률)

    private int coolingFanSize; //쿨링팬 크기

    private int coolingFanCnt; //쿨링팬 개수

    private String bearing; //베어링

    private double depth; //깊이

    private String asPeriod; //A/S 보증기간

    private String output3v; //+3.3V 출력

    private double output5v; //+5V 출력

    private String output12v; //+12V 출력

    private String outputMinus12v; //-12V 출력

    private String output5vsb; //+5Vsb 출력

    private String cableConn; //케이블연결

    private String mainPower; //메인전원

    private int subPower4; //보조전원 (4핀)

    private int subPower8; //보조전원(8핀)

    private int pcie8; //PCle 8핀(6+2)

    private String pcie6; //PCIe 6핀

    private int sata; //SATA

    private int sata33; //SATA3.3

    private int ide4pin; //IDE 4핀

    private int fdd; //FDD

    private boolean fanlessMode; //팬리스모드

    private boolean autoFanControl; //자동 팬 조절

    private boolean manualFanControl; //수동 팬 조절

    private boolean standbyPower1w; //대기전력 1W 미만

    private boolean flatCable; //플랫케이블

    private boolean freeBolt; //프리볼트

    private boolean ledLight; //LED 라이트

    private boolean digitalControl; //디지털제어

    private boolean condenser85; //85도 콘덴서

    private boolean condenser105; //105도 콘덴서

    private boolean dcToDc; //DC to DC 설계

    private boolean llcConvert; //LLC공진형컨버터

    private boolean ovp; //과전압(OVP)

    private boolean uvp; //저전압(UVP)

    private boolean ocp; //과전류(OCP)

    private boolean olp; //과부하(OLP)

    private boolean opp; //과전력(OPP)

    private boolean otpOhp; //과열(OTP/OHP)

    private boolean scp; //단락(SCP)

    private boolean sip; //서지 + 인러쉬(SIP)

    private boolean nlp; //무부하(NLP)

    private boolean nlo; //공회전(NLO)

    private boolean auraSync; //AURA SYNC

    private boolean mysticLight; //MYSTIC LIGHT

    private boolean rgbFusion; //RGB FUSION

    private boolean polychrome; //POLYCHROME

    private boolean razerChroma; //RAZER CHROMA

    private boolean ttRgbPlus; //TT RGB PLUS

    private boolean alertSound; //경보음

    private boolean led; //LED

    private String outputVoltage; //출력 전압

    private int outputW; //출력 용량 (W)

    private int outputVa; //출력 용량(VA)

    private String inputVoltage; //허용 입력 전압

    private String inputFrequency; //입력 주파수

    private String chargeTime; //일반적 충전시간

    private String load100; //100%부하

    private String load50; //50% 부하

    private double width; //폭

    private double height; //높이

    private double weight; //무게

}
