package com.comsultant.domain.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "vga")
@DiscriminatorValue("9")
public class Vga extends Product {

    @Builder
    public Vga(int category, String name, int imgCnt,
               String corp, String registeredAt, String chipsetCorp, String series, String gpuProcess,
               String nvidia, String amd, int baseClock, int boostClock, String streamProcessor,
               int cudaProcessor, String interfaces, String memoryType, int memoryClock, double memoryVolume,
               int memoryBus, int hdmi, int dvi, int dp, int minidp, int monitor, boolean hdmi21, boolean dp14,
               boolean thunderbolt3, boolean usb3, boolean gbLam, boolean zeroFan, boolean display8k, boolean display4k,
               boolean hdr, boolean dualBios, boolean hdcp23, boolean multiVga, boolean hdcp, double power, int recomPower,
               int powerPort4, int powerPort6, int powerPort8, int powerPort12, String powerSupply, boolean heatSink,
               boolean heatFight, boolean fanCooler, boolean vaporChamber, boolean waterCooling, int fanCnt, double width,
               double height, boolean backPlate, boolean drMos, boolean led, boolean overclockPysical, boolean pwm, boolean lcd,
               boolean waterPossible, boolean frontLed, boolean backLed, boolean sideLed, boolean fanLed, String distCorp) {
        super(category, name, imgCnt);
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

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "chipset_corp", columnDefinition = "VARCHAR(255)")
    private String chipsetCorp;

    @Column(name = "series", columnDefinition = "VARCHAR(255)")
    private String series;

    @Column(name = "gpu_process", columnDefinition = "VARCHAR(255)")
    private String gpuProcess;

    @Column(name = "nvidia", columnDefinition = "VARCHAR(255)")
    private String nvidia;

    @Column(name = "amd", columnDefinition = "VARCHAR(255)")
    private String amd;

    @Column(name = "base_clock", columnDefinition = "INT")
    private int baseClock;

    @Column(name = "boost_clock", columnDefinition = "INT")
    private int boostClock;

    @Column(name = "stream_processor", columnDefinition = "VARCHAR(255)")
    private String streamProcessor;

    @Column(name = "cuda_processor", columnDefinition = "INT")
    private int cudaProcessor;

    @Column(name = "interfaces", columnDefinition = "VARCHAR(255)")
    private String interfaces;

    @Column(name = "memory_type", columnDefinition = "VARCHAR(255)")
    private String memoryType;

    @Column(name = "memory_clock", columnDefinition = "INT")
    private int memoryClock;

    @Column(name = "memory_volume", columnDefinition = "DOUBLE")
    private double memoryVolume;

    @Column(name = "memory_bus", columnDefinition = "INT")
    private int memoryBus;

    @Column(name = "hdmi", columnDefinition = "INT")
    private int hdmi;

    @Column(name = "dvi", columnDefinition = "INT")
    private int dvi;

    @Column(name = "dp", columnDefinition = "INT")
    private int dp;

    @Column(name = "minidp", columnDefinition = "INT")
    private int minidp;

    @Column(name = "monitor", columnDefinition = "INT")
    private int monitor;

    @Column(name = "hdmi21", columnDefinition = "TINYINT(1)")
    private boolean hdmi21;

    @Column(name = "dp14", columnDefinition = "TINYINT(1)")
    private boolean dp14;

    @Column(name = "thunderbolt3", columnDefinition = "TINYINT(1)")
    private boolean thunderbolt3;

    @Column(name = "usb3", columnDefinition = "TINYINT(1)")
    private boolean usb3;

    @Column(name = "gb_lam", columnDefinition = "TINYINT(1)")
    private boolean gbLam;

    @Column(name = "zero_fan", columnDefinition = "TINYINT(1)")
    private boolean zeroFan;

    @Column(name = "display8k", columnDefinition = "TINYINT(1)")
    private boolean display8k;

    @Column(name = "display4k", columnDefinition = "TINYINT(1)")
    private boolean display4k;

    @Column(name = "hdr", columnDefinition = "TINYINT(1)")
    private boolean hdr;

    @Column(name = "dual_bios", columnDefinition = "TINYINT(1)")
    private boolean dualBios;

    @Column(name = "hdcp23", columnDefinition = "TINYINT(1)")
    private boolean hdcp23;

    @Column(name = "multi_vga", columnDefinition = "TINYINT(1)")
    private boolean multiVga;

    @Column(name = "hdcp", columnDefinition = "TINYINT(1)")
    private boolean hdcp;

    @Column(name = "power", columnDefinition = "DOUBLE")
    private double power;

    @Column(name = "recom_power", columnDefinition = "INT")
    private int recomPower;

    @Column(name = "power_port4", columnDefinition = "INT")
    private int powerPort4;

    @Column(name = "power_port6", columnDefinition = "INT")
    private int powerPort6;

    @Column(name = "power_port8", columnDefinition = "INT")
    private int powerPort8;

    @Column(name = "power_port12", columnDefinition = "INT")
    private int powerPort12;

    @Column(name = "power_supply", columnDefinition = "VARCHAR(255)")
    private String powerSupply;

    @Column(name = "heat_sink", columnDefinition = "TINYINT(1)")
    private boolean heatSink;

    @Column(name = "heat_fight", columnDefinition = "TINYINT(1)")
    private boolean heatFight;

    @Column(name = "fan_cooler", columnDefinition = "TINYINT(1)")
    private boolean fanCooler;

    @Column(name = "vapor_chamber", columnDefinition = "TINYINT(1)")
    private boolean vaporChamber;

    @Column(name = "water_cooling", columnDefinition = "TINYINT(1)")
    private boolean waterCooling;

    @Column(name = "fan_cnt", columnDefinition = "INT")
    private int fanCnt;

    @Column(name = "width", columnDefinition = "DOUBLE")
    private double width;

    @Column(name = "height", columnDefinition = "DOUBLE")
    private double height;

    @Column(name = "back_plate", columnDefinition = "TINYINT(1)")
    private boolean backPlate;

    @Column(name = "dr_mos", columnDefinition = "TINYINT(1)")
    private boolean drMos;

    @Column(name = "led", columnDefinition = "TINYINT(1)")
    private boolean led;

    @Column(name = "overclock_pysical", columnDefinition = "TINYINT(1)")
    private boolean overclockPysical;

    @Column(name = "pwm", columnDefinition = "TINYINT(1)")
    private boolean pwm;

    @Column(name = "lcd", columnDefinition = "TINYINT(1)")
    private boolean lcd;

    @Column(name = "water_possible", columnDefinition = "TINYINT(1)")
    private boolean waterPossible;

    @Column(name = "front_led", columnDefinition = "TINYINT(1)")
    private boolean frontLed;

    @Column(name = "back_led", columnDefinition = "TINYINT(1)")
    private boolean backLed;

    @Column(name = "side_led", columnDefinition = "TINYINT(1)")
    private boolean sideLed;

    @Column(name = "fan_led", columnDefinition = "TINYINT(1)")
    private boolean fanLed;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

}
