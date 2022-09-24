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
@Table(name = "ram")
@DiscriminatorValue("2")
public class Ram extends Product {

    @Builder
    public Ram(int category, String name, int imgCnt,
               String corp, String registeredAt, String useDevice, String type, String memorySize,
               double memoryVolume, int actionClock, String lamTiming, double actionVoltage, int lamCnt,
               boolean led, boolean ondieEcc, boolean xmp, boolean xmp3, boolean tempSensor, String heatsink,
               boolean reg, boolean lpSize, String heatsinkColor, String ledColor, boolean auraSync, boolean mysticLight,
               boolean rgbFusion, boolean polychrome, boolean corsairIcue, boolean razerChroma, boolean gskillLighting,
               boolean ttRgbPlus, boolean teamForceBlitz, boolean xpgRgb, String distCorp) {
        super(category, name, imgCnt);
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

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "use_device", columnDefinition = "VARCHAR(255)")
    private String useDevice;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "memory_size", columnDefinition = "VARCHAR(255)")
    private String memorySize;

    @Column(name = "memory_volume", columnDefinition = "DOUBLE")
    private double memoryVolume;

    @Column(name = "action_clock", columnDefinition = "INT")
    private int actionClock;

    @Column(name = "lam_timing", columnDefinition = "VARCHAR(255)")
    private String lamTiming;

    @Column(name = "action_voltage", columnDefinition = "DOUBLE")
    private double actionVoltage;

    @Column(name = "lam_cnt", columnDefinition = "INT")
    private int lamCnt;

    @Column(name = "led", columnDefinition = "TINYINT(1)")
    private boolean led;

    @Column(name = "ondie_ecc", columnDefinition = "TINYINT(1)")
    private boolean ondieEcc;

    @Column(name = "xmp", columnDefinition = "TINYINT(1)")
    private boolean xmp;

    @Column(name = "xmp3", columnDefinition = "TINYINT(1)")
    private boolean xmp3;

    @Column(name = "temp_sensor", columnDefinition = "TINYINT(1)")
    private boolean tempSensor;

    @Column(name = "heatsink", columnDefinition = "VARCHAR(255)")
    private String heatsink;

    @Column(name = "reg", columnDefinition = "TINYINT(1)")
    private boolean reg;

    @Column(name = "lp_size", columnDefinition = "TINYINT(1)")
    private boolean lpSize;

    @Column(name = "heatsink_color", columnDefinition = "VARCHAR(255)")
    private String heatsinkColor;

    @Column(name = "led_color", columnDefinition = "VARCHAR(255)")
    private String ledColor;

    @Column(name = "aura_sync", columnDefinition = "TINYINT(1)")
    private boolean auraSync;

    @Column(name = "mystic_light", columnDefinition = "TINYINT(1)")
    private boolean mysticLight;

    @Column(name = "rgb_fusion", columnDefinition = "TINYINT(1)")
    private boolean rgbFusion;

    @Column(name = "polychrome", columnDefinition = "TINYINT(1)")
    private boolean polychrome;

    @Column(name = "corsair_icue", columnDefinition = "TINYINT(1)")
    private boolean corsairIcue;

    @Column(name = "razer_chroma", columnDefinition = "TINYINT(1)")
    private boolean razerChroma;

    @Column(name = "gskill_lighting", columnDefinition = "TINYINT(1)")
    private boolean gskillLighting;

    @Column(name = "tt_rgb_plus", columnDefinition = "TINYINT(1)")
    private boolean ttRgbPlus;

    @Column(name = "team_force_blitz", columnDefinition = "TINYINT(1)")
    private boolean teamForceBlitz;

    @Column(name = "xpg_rgb", columnDefinition = "TINYINT(1)")
    private boolean xpgRgb;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

}
