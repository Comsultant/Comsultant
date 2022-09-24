package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PsuDto extends ProductDto {

    @Builder
    public PsuDto(long idx, int category, String name, int imgCnt, String corporation, String registeredAt, String type,
                  int ratedPower, String plusCert, String etaCert, String lambdaCert, String volChange, String outputMeth12v,
                  int avail12v, String pfcCircuit, int pf, int coolingFanSize, int coolingFanCnt, String bearing,
                  double depth, String asPeriod, String output3v, double output5v, String output12v, String outputMinus12v,
                  String output5vsb, String cableConn, String mainPower, int subPower4, int subPower8, int pcie8,
                  String pcie6, int sata, int sata33, int ide4pin, int fdd, boolean fanlessMode, boolean autoFanControl,
                  boolean manualFanControl, boolean standbyPower1w, boolean flatCable, boolean freeBolt, boolean ledLight,
                  boolean digitalControl, boolean condenser85, boolean condenser105, boolean dcToDc, boolean llcConvert, boolean ovp,
                  boolean uvp, boolean ocp, boolean olp, boolean opp, boolean otpOhp, boolean scp, boolean sip, boolean nlp, boolean nlo,
                  boolean auraSync, boolean mysticLight, boolean rgbFusion, boolean polychrome, boolean razerChroma, boolean ttRgbPlus,
                  boolean alertSound, boolean led, String outputVoltage, int outputW, int outputVa, String inputVoltage, String inputFrequency,
                  String chargeTime, String load100, String load50, double width, double height, double weight) {
        super(idx, category, name, imgCnt);
        this.corporation = corporation;
        this.registeredAt = registeredAt;
        this.type = type;
        this.ratedPower = ratedPower;
        this.plusCert = plusCert;
        this.etaCert = etaCert;
        this.lambdaCert = lambdaCert;
        this.volChange = volChange;
        this.outputMeth12v = outputMeth12v;
        this.avail12v = avail12v;
        this.pfcCircuit = pfcCircuit;
        this.pf = pf;
        this.coolingFanSize = coolingFanSize;
        this.coolingFanCnt = coolingFanCnt;
        this.bearing = bearing;
        this.depth = depth;
        this.asPeriod = asPeriod;
        this.output3v = output3v;
        this.output5v = output5v;
        this.output12v = output12v;
        this.outputMinus12v = outputMinus12v;
        this.output5vsb = output5vsb;
        this.cableConn = cableConn;
        this.mainPower = mainPower;
        this.subPower4 = subPower4;
        this.subPower8 = subPower8;
        this.pcie8 = pcie8;
        this.pcie6 = pcie6;
        this.sata = sata;
        this.sata33 = sata33;
        this.ide4pin = ide4pin;
        this.fdd = fdd;
        this.fanlessMode = fanlessMode;
        this.autoFanControl = autoFanControl;
        this.manualFanControl = manualFanControl;
        this.standbyPower1w = standbyPower1w;
        this.flatCable = flatCable;
        this.freeBolt = freeBolt;
        this.ledLight = ledLight;
        this.digitalControl = digitalControl;
        this.condenser85 = condenser85;
        this.condenser105 = condenser105;
        this.dcToDc = dcToDc;
        this.llcConvert = llcConvert;
        this.ovp = ovp;
        this.uvp = uvp;
        this.ocp = ocp;
        this.olp = olp;
        this.opp = opp;
        this.otpOhp = otpOhp;
        this.scp = scp;
        this.sip = sip;
        this.nlp = nlp;
        this.nlo = nlo;
        this.auraSync = auraSync;
        this.mysticLight = mysticLight;
        this.rgbFusion = rgbFusion;
        this.polychrome = polychrome;
        this.razerChroma = razerChroma;
        this.ttRgbPlus = ttRgbPlus;
        this.alertSound = alertSound;
        this.led = led;
        this.outputVoltage = outputVoltage;
        this.outputW = outputW;
        this.outputVa = outputVa;
        this.inputVoltage = inputVoltage;
        this.inputFrequency = inputFrequency;
        this.chargeTime = chargeTime;
        this.load100 = load100;
        this.load50 = load50;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    private String corporation; //제조회사

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
