package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VgaDto extends ProductDto {

    @Builder
    public VgaDto(long idx, int category, String name, int imgCnt, String corp, String registeredAt,
                  String chipsetCorp, String series, String gpuProcess, String nvidia, String amd, int baseClock,
                  int boostClock, String streamProcessor, int cudaProcessor, String interfaces, String memoryType,
                  int memoryClock, double memoryVolume, int memoryBus, int hdmi, int dvi, int dp, int minidp, int monitor,
                  boolean hdmi21, boolean dp14, boolean thunderbolt3, boolean usb3, boolean gbLam, boolean zeroFan,
                  boolean display8k, boolean display4k, boolean hdr, boolean dualBios, boolean hdcp23, boolean multiVga,
                  boolean hdcp, double power, int recomPower, int powerPort4, int powerPort6, int powerPort8, int powerPort12,
                  String powerSupply, boolean heatSink, boolean heatFight, boolean fanCooler, boolean vaporChamber, boolean waterCooling,
                  int fanCnt, double width, double height, boolean backPlate, boolean drMos, boolean led, boolean overclockPysical,
                  boolean pwm, boolean lcd, boolean waterPossible, boolean frontLed, boolean backLed, boolean sideLed,
                  boolean fanLed, String distCorp) {
        super(idx, category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.chipsetCorp = chipsetCorp;
        this.series = series;
        this.gpuProcess = gpuProcess;
        this.nvidia = nvidia;
        this.amd = amd;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.streamProcessor = streamProcessor;
        this.cudaProcessor = cudaProcessor;
        this.interfaces = interfaces;
        this.memoryType = memoryType;
        this.memoryClock = memoryClock;
        this.memoryVolume = memoryVolume;
        this.memoryBus = memoryBus;
        this.hdmi = hdmi;
        this.dvi = dvi;
        this.dp = dp;
        this.minidp = minidp;
        this.monitor = monitor;
        this.hdmi21 = hdmi21;
        this.dp14 = dp14;
        this.thunderbolt3 = thunderbolt3;
        this.usb3 = usb3;
        this.gbLam = gbLam;
        this.zeroFan = zeroFan;
        this.display8k = display8k;
        this.display4k = display4k;
        this.hdr = hdr;
        this.dualBios = dualBios;
        this.hdcp23 = hdcp23;
        this.multiVga = multiVga;
        this.hdcp = hdcp;
        this.power = power;
        this.recomPower = recomPower;
        this.powerPort4 = powerPort4;
        this.powerPort6 = powerPort6;
        this.powerPort8 = powerPort8;
        this.powerPort12 = powerPort12;
        this.powerSupply = powerSupply;
        this.heatSink = heatSink;
        this.heatFight = heatFight;
        this.fanCooler = fanCooler;
        this.vaporChamber = vaporChamber;
        this.waterCooling = waterCooling;
        this.fanCnt = fanCnt;
        this.width = width;
        this.height = height;
        this.backPlate = backPlate;
        this.drMos = drMos;
        this.led = led;
        this.overclockPysical = overclockPysical;
        this.pwm = pwm;
        this.lcd = lcd;
        this.waterPossible = waterPossible;
        this.frontLed = frontLed;
        this.backLed = backLed;
        this.sideLed = sideLed;
        this.fanLed = fanLed;
        this.distCorp = distCorp;
    }

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String chipsetCorp; //칩셋 제조사

    private String series; //제품 시리즈

    private String gpuProcess; //GPU 제조 공정

    private String nvidia; //NVIDIA 칩셋

    private String amd; //AMD 칩셋

    private int baseClock; //베이스클럭

    private int boostClock; //부스트클럭

    private String streamProcessor; //스트림 프로세서

    private int cudaProcessor; //쿠다 프로세서

    private String interfaces; //인터페이스

    private String memoryType; //메모리 종류

    private int memoryClock; //메모리 클럭

    private double memoryVolume; //메모리 용량

    private int memoryBus; //메모리 버스

    private int hdmi; //HDMI

    private int dvi; //DVI

    private int dp; //DP

    private int minidp; //miniDP

    private int monitor; //모니터 지원

    private boolean hdmi21; //HDMI2.1

    private boolean dp14; //DP1.4

    private boolean thunderbolt3; //썬더볼트3

    private boolean usb3; //USB 3.0

    private boolean gbLam; //기가비트 LAM

    private boolean zeroFan; //제로팬(0-dB기술)

    private boolean display8k; //8K 해상도 지원

    private boolean display4k; //4K 해상도 지원

    private boolean hdr; //HDR지원

    private boolean dualBios; //Dual BIOS

    private boolean hdcp23; //HDCP 2.3

    private boolean multiVga; //멀티 VGA

    private boolean hdcp; //HDCP 지원

    private double power; //사용 전력

    private int recomPower; //권장 파워용량

    private int powerPort4; //전원 포트 4핀

    private int powerPort6; //전원 포트 6핀

    private int powerPort8; //전원 포트 8핀

    private int powerPort12; //전원 포트 12핀

    private String powerSupply; //전원부

    private boolean heatSink; //방열판

    private boolean heatFight; //히트파이트

    private boolean fanCooler; //팬쿨러

    private boolean vaporChamber; //베이퍼챔버

    private boolean waterCooling; //수냉 쿨링

    private int fanCnt; //팬 개수

    private double width; //가로(길이)

    private double height; //높이(두께)

    private boolean backPlate; //백플레이트

    private boolean drMos; //Dr MOS 모스펫

    private boolean led; //LED 라이트

    private boolean overclockPysical; //오버클럭 물리키

    private boolean pwm; //PWM 커넥터

    private boolean lcd; //LCD 모니터링

    private boolean waterPossible; //수냉 장착 지원

    private boolean frontLed; //전면 LED

    private boolean backLed; //후면 LED

    private boolean sideLed; //측면 LED

    private boolean fanLed; //팬  LED

    private String distCorp; //유통회사

}
