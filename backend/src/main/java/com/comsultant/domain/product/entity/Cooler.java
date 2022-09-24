package com.comsultant.domain.product.entity;

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
@Table(name = "cooler")
@DiscriminatorValue("6")
public class Cooler extends Product{

    @Builder
    public Cooler(int category, String name, int imgCnt, String corp, String registeredAt,
                  String coolingSystem, String classType, String type, String thermal,
                  double heatConductivity, String volume, String electricityConductivity,
                  String viscosity, String density, boolean lga3647, boolean lga2066, boolean lga2011V3,
                  boolean lga2011, boolean lga1700, boolean lga1366, boolean lga1200, boolean lga115x, boolean lga775,
                  boolean lga771, boolean lga4677, boolean lga4189, boolean socket478, boolean socket370, boolean tr4,
                  boolean am5, boolean am4, boolean am3, boolean am1, boolean sp3, boolean strx4, boolean socket939,
                  boolean socket754, boolean socket940, boolean swrx8, boolean socketa, boolean socketf, boolean fmxAmx,
                  int tdp, String texture, String radiator, String heatpipe, double coolerHeight, String weight,
                  double fanSize, String fanThickness, String connector, String bearing, int fanSpeedMax, String windVolumeMax,
                  String windPressureMax, double noiseMax, String fanCnt, String fanLife, boolean ledLight, String ledColor,
                  boolean pwm, boolean rgbController, boolean daisychain, boolean zerofan, boolean fanController,
                  boolean waterblock, boolean auraSync, boolean mysticLight, boolean rgbFusion, boolean rgbLed, boolean polychrome,
                  boolean chroma, boolean vivid, boolean biostarSync, boolean software, String asPeriod, boolean custom, boolean packages,
                  boolean remote, boolean indicator, boolean pumpSpeed, String distCorp, boolean lcd) {
        super(category, name, imgCnt);
        this.name = name;
        this.imgCnt = imgCnt;
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.coolingSystem = coolingSystem;
        this.classType = classType;
        this.type = type;
        this.thermal = thermal;
        this.heatConductivity = heatConductivity;
        this.volume = volume;
        this.electricityConductivity = electricityConductivity;
        this.viscosity = viscosity;
        this.density = density;
        this.lga3647 = lga3647;
        this.lga2066 = lga2066;
        this.lga2011V3 = lga2011V3;
        this.lga2011 = lga2011;
        this.lga1700 = lga1700;
        this.lga1366 = lga1366;
        this.lga1200 = lga1200;
        this.lga115x = lga115x;
        this.lga775 = lga775;
        this.lga771 = lga771;
        this.lga4677 = lga4677;
        this.lga4189 = lga4189;
        this.socket478 = socket478;
        this.socket370 = socket370;
        this.tr4 = tr4;
        this.am5 = am5;
        this.am4 = am4;
        this.am3 = am3;
        this.am1 = am1;
        this.sp3 = sp3;
        this.strx4 = strx4;
        this.socket939 = socket939;
        this.socket754 = socket754;
        this.socket940 = socket940;
        this.swrx8 = swrx8;
        this.socketa = socketa;
        this.socketf = socketf;
        this.fmxAmx = fmxAmx;
        this.tdp = tdp;
        this.texture = texture;
        this.radiator = radiator;
        this.heatpipe = heatpipe;
        this.coolerHeight = coolerHeight;
        this.weight = weight;
        this.fanSize = fanSize;
        this.fanThickness = fanThickness;
        this.connector = connector;
        this.bearing = bearing;
        this.fanSpeedMax = fanSpeedMax;
        this.windVolumeMax = windVolumeMax;
        this.windPressureMax = windPressureMax;
        this.noiseMax = noiseMax;
        this.fanCnt = fanCnt;
        this.fanLife = fanLife;
        this.ledLight = ledLight;
        this.ledColor = ledColor;
        this.pwm = pwm;
        this.rgbController = rgbController;
        this.daisychain = daisychain;
        this.zerofan = zerofan;
        this.fanController = fanController;
        this.waterblock = waterblock;
        this.auraSync = auraSync;
        this.mysticLight = mysticLight;
        this.rgbFusion = rgbFusion;
        this.rgbLed = rgbLed;
        this.polychrome = polychrome;
        this.chroma = chroma;
        this.vivid = vivid;
        this.biostarSync = biostarSync;
        this.software = software;
        this.asPeriod = asPeriod;
        this.custom = custom;
        this.packages = packages;
        this.remote = remote;
        this.indicator = indicator;
        this.pumpSpeed = pumpSpeed;
        this.distCorp = distCorp;
        this.lcd = lcd;
    }

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

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

}
