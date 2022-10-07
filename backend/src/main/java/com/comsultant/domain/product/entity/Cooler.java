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
@Table(name = "cooler")
public class Cooler {

    @Id
    @Column(name = "product_idx", nullable = false)
    private long idx;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_idx",  columnDefinition = "BIGINT(20) UNSIGNED")
    private Product product;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "price", columnDefinition = "INT")
    private int price;

    @Column(name = "img_cnt", columnDefinition = "INT")
    private int imgCnt;

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "cooling_system", columnDefinition = "VARCHAR(255)")
    private String coolingSystem;

    @Column(name = "class_type", columnDefinition = "VARCHAR(255)")
    private String classType;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "thermal", columnDefinition = "VARCHAR(255)")
    private String thermal;

    @Column(name = "heat_conductivity", columnDefinition = "DOUBLE")
    private double heatConductivity;

    @Column(name = "volume", columnDefinition = "VARCHAR(255)")
    private String volume;

    @Column(name = "electricity_conductivity", columnDefinition = "VARCHAR(255)")
    private String electricityConductivity;

    @Column(name = "viscosity", columnDefinition = "VARCHAR(255)")
    private String viscosity;

    @Column(name = "density", columnDefinition = "VARCHAR(255)")
    private String density;

    @Column(name = "lga3647", columnDefinition = "TINYINT(1)")
    private boolean lga3647;

    @Column(name = "lga2066", columnDefinition = "TINYINT(1)")
    private boolean lga2066;

    @Column(name = "lga2011_v3", columnDefinition = "TINYINT(1)")
    private boolean lga2011V3;

    @Column(name = "lga2011", columnDefinition = "TINYINT(1)")
    private boolean lga2011;

    @Column(name = "lga1700", columnDefinition = "TINYINT(1)")
    private boolean lga1700;

    @Column(name = "lga1366", columnDefinition = "TINYINT(1)")
    private boolean lga1366;

    @Column(name = "lga1200", columnDefinition = "TINYINT(1)")
    private boolean lga1200;

    @Column(name = "lga115x", columnDefinition = "TINYINT(1)")
    private boolean lga115x;

    @Column(name = "lga775", columnDefinition = "TINYINT(1)")
    private boolean lga775;

    @Column(name = "lga771", columnDefinition = "TINYINT(1)")
    private boolean lga771;

    @Column(name = "lga4677", columnDefinition = "TINYINT(1)")
    private boolean lga4677;

    @Column(name = "lga4189", columnDefinition = "TINYINT(1)")
    private boolean lga4189;

    @Column(name = "socket478", columnDefinition = "TINYINT(1)")
    private boolean socket478;

    @Column(name = "socket370", columnDefinition = "TINYINT(1)")
    private boolean socket370;

    @Column(name = "tr4", columnDefinition = "TINYINT(1)")
    private boolean tr4;

    @Column(name = "am5", columnDefinition = "TINYINT(1)")
    private boolean am5;

    @Column(name = "am4", columnDefinition = "TINYINT(1)")
    private boolean am4;

    @Column(name = "am3", columnDefinition = "TINYINT(1)")
    private boolean am3;

    @Column(name = "am1", columnDefinition = "TINYINT(1)")
    private boolean am1;

    @Column(name = "sp3", columnDefinition = "TINYINT(1)")
    private boolean sp3;

    @Column(name = "strx4", columnDefinition = "TINYINT(1)")
    private boolean strx4;

    @Column(name = "socket939", columnDefinition = "TINYINT(1)")
    private boolean socket939;

    @Column(name = "socket754", columnDefinition = "TINYINT(1)")
    private boolean socket754;

    @Column(name = "socket940", columnDefinition = "TINYINT(1)")
    private boolean socket940;

    @Column(name = "swrx8", columnDefinition = "TINYINT(1)")
    private boolean swrx8;

    @Column(name = "socketa", columnDefinition = "TINYINT(1)")
    private boolean socketa;

    @Column(name = "socketf", columnDefinition = "TINYINT(1)")
    private boolean socketf;

    @Column(name = "fmx_amx", columnDefinition = "TINYINT(1)")
    private boolean fmxAmx;

    @Column(name = "tdp", columnDefinition = "INT")
    private int tdp;

    @Column(name = "texture", columnDefinition = "VARCHAR(255)")
    private String texture;

    @Column(name = "radiator", columnDefinition = "VARCHAR(255)")
    private String radiator;

    @Column(name = "heatpipe", columnDefinition = "VARCHAR(255)")
    private String heatpipe;

    @Column(name = "cooler_height", columnDefinition = "DOUBLE")
    private double coolerHeight;

    @Column(name = "weight", columnDefinition = "VARCHAR(255)")
    private String weight;

    @Column(name = "fan_size", columnDefinition = "DOUBLE")
    private double fanSize;

    @Column(name = "fan_thickness", columnDefinition = "VARCHAR(255)")
    private String fanThickness;

    @Column(name = "connector", columnDefinition = "VARCHAR(255)")
    private String connector;

    @Column(name = "bearing", columnDefinition = "VARCHAR(255)")
    private String bearing;

    @Column(name = "fan_speed_max", columnDefinition = "INT")
    private int fanSpeedMax;

    @Column(name = "wind_volume_max", columnDefinition = "VARCHAR(255)")
    private String windVolumeMax;

    @Column(name = "wind_pressure_max", columnDefinition = "VARCHAR(255)")
    private String windPressureMax;

    @Column(name = "noise_max", columnDefinition = "DOUBLE")
    private double noiseMax;

    @Column(name = "fan_cnt", columnDefinition = "VARCHAR(255)")
    private String fanCnt;

    @Column(name = "fan_life", columnDefinition = "VARCHAR(255)")
    private String fanLife;

    @Column(name = "led_light", columnDefinition = "TINYINT(1)")
    private boolean ledLight;

    @Column(name = "led_color", columnDefinition = "VARCHAR(255)")
    private String ledColor;

    @Column(name = "pwm", columnDefinition = "TINYINT(1)")
    private boolean pwm;

    @Column(name = "rgb_controller", columnDefinition = "TINYINT(1)")
    private boolean rgbController;

    @Column(name = "daisychain", columnDefinition = "TINYINT(1)")
    private boolean daisychain;

    @Column(name = "zerofan", columnDefinition = "TINYINT(1)")
    private boolean zerofan;

    @Column(name = "fan_controller", columnDefinition = "TINYINT(1)")
    private boolean fanController;

    @Column(name = "waterblock", columnDefinition = "TINYINT(1)")
    private boolean waterblock;

    @Column(name = "aura_sync", columnDefinition = "TINYINT(1)")
    private boolean auraSync;

    @Column(name = "mystic_light", columnDefinition = "TINYINT(1)")
    private boolean mysticLight;

    @Column(name = "rgb_fusion", columnDefinition = "TINYINT(1)")
    private boolean rgbFusion;

    @Column(name = "rgb_led", columnDefinition = "TINYINT(1)")
    private boolean rgbLed;

    @Column(name = "polychrome", columnDefinition = "TINYINT(1)")
    private boolean polychrome;

    @Column(name = "chroma", columnDefinition = "TINYINT(1)")
    private boolean chroma;

    @Column(name = "vivid", columnDefinition = "TINYINT(1)")
    private boolean vivid;

    @Column(name = "biostar_sync", columnDefinition = "TINYINT(1)")
    private boolean biostarSync;

    @Column(name = "software", columnDefinition = "TINYINT(1)")
    private boolean software;

    @Column(name = "as_period", columnDefinition = "VARCHAR(255)")
    private String asPeriod;

    @Column(name = "custom", columnDefinition = "TINYINT(1)")
    private boolean custom;

    @Column(name = "packages", columnDefinition = "TINYINT(1)")
    private boolean packages;

    @Column(name = "remote", columnDefinition = "TINYINT(1)")
    private boolean remote;

    @Column(name = "indicator", columnDefinition = "TINYINT(1)")
    private boolean indicator;

    @Column(name = "pump_speed", columnDefinition = "TINYINT(1)")
    private boolean pumpSpeed;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

    @Column(name = "lcd", columnDefinition = "TINYINT(1)")
    private boolean lcd;

    public void modifyPrice(int price) {
        this.price = price;
    }
}
