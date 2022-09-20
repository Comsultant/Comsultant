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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "ram")
public class Ram {

    @Id
    @Column(name = "product_idx", nullable = false)
    private long idx;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_idx",  columnDefinition = "BIGINT(20) UNSIGNED")
    private Product product;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "img_cnt", columnDefinition = "INT")
    private int imgCnt;

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

    @Column(name = "t_force_blitz", columnDefinition = "TINYINT(1)")
    private boolean tForceBlitz;

    @Column(name = "xpg_rgb", columnDefinition = "TINYINT(1)")
    private boolean xpgRgb;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

}
