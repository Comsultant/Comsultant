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
@Table(name = "psu")
public class Psu {

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

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "rated_power", columnDefinition = "INT")
    private int ratedPower;

    @Column(name = "plus_cert", columnDefinition = "VARCHAR(255)")
    private String plusCert;

    @Column(name = "eta_cert", columnDefinition = "VARCHAR(255)")
    private String etaCert;

    @Column(name = "lambda_cert", columnDefinition = "VARCHAR(255)")
    private String lambdaCert;

    @Column(name = "vol_change", columnDefinition = "VARCHAR(255)")
    private String volChange;

    @Column(name = "output_meth_12v", columnDefinition = "VARCHAR(255)")
    private String outputMeth12v;

    @Column(name = "avail_12v", columnDefinition = "INT")
    private int avail12v;

    @Column(name = "pfc_circuit", columnDefinition = "VARCHAR(255)")
    private String pfcCircuit;

    @Column(name = "pf", columnDefinition = "INT")
    private int pf;

    @Column(name = "cooling_fan_size", columnDefinition = "INT")
    private int coolingFanSize;

    @Column(name = "cooling_fan_cnt", columnDefinition = "INT")
    private int coolingFanCnt;

    @Column(name = "bearing", columnDefinition = "VARCHAR(255)")
    private String bearing;

    @Column(name = "depth", columnDefinition = "DOUBLE")
    private double depth;

    @Column(name = "as_period", columnDefinition = "VARCHAR(255)")
    private String asPeriod;

    @Column(name = "output_3v", columnDefinition = "VARCHAR(255)")
    private String output3v;

    @Column(name = "output_5v", columnDefinition = "DOUBLE")
    private double output5v;

    @Column(name = "output_12v", columnDefinition = "VARCHAR(255)")
    private String output12v;

    @Column(name = "output_minus_12v", columnDefinition = "VARCHAR(255)")
    private String outputMinus12v;

    @Column(name = "output_5vsb", columnDefinition = "VARCHAR(255)")
    private String output5vsb;

    @Column(name = "cable_conn", columnDefinition = "VARCHAR(255)")
    private String cableConn;

    @Column(name = "main_power", columnDefinition = "VARCHAR(255)")
    private String mainPower;

    @Column(name = "sub_power4", columnDefinition = "INT")
    private int subPower4;

    @Column(name = "sub_power8", columnDefinition = "INT")
    private int subPower8;

    @Column(name = "pcie8", columnDefinition = "INT")
    private int pcie8;

    @Column(name = "pcie6", columnDefinition = "VARCHAR(255)")
    private String pcie6;

    @Column(name = "sata", columnDefinition = "INT")
    private int sata;

    @Column(name = "sata_33", columnDefinition = "INT")
    private int sata33;

    @Column(name = "ide_4pin", columnDefinition = "INT")
    private int ide4pin;

    @Column(name = "fdd", columnDefinition = "INT")
    private int fdd;

    @Column(name = "fanless_mode", columnDefinition = "TINYINT(1)")
    private boolean fanlessMode;

    @Column(name = "auto_fan_control", columnDefinition = "TINYINT(1)")
    private boolean autoFanControl;

    @Column(name = "manual_fan_control", columnDefinition = "TINYINT(1)")
    private boolean manualFanControl;

    @Column(name = "standby_power_1w", columnDefinition = "TINYINT(1)")
    private boolean standbyPower1w;

    @Column(name = "flat_cable", columnDefinition = "TINYINT(1)")
    private boolean flatCable;

    @Column(name = "free_bolt", columnDefinition = "TINYINT(1)")
    private boolean freeBolt;

    @Column(name = "led_light", columnDefinition = "TINYINT(1)")
    private boolean ledLight;

    @Column(name = "digital_control", columnDefinition = "TINYINT(1)")
    private boolean digitalControl;

    @Column(name = "condenser85", columnDefinition = "TINYINT(1)")
    private boolean condenser85;

    @Column(name = "condenser105", columnDefinition = "TINYINT(1)")
    private boolean condenser105;

    @Column(name = "dc_to_dc", columnDefinition = "TINYINT(1)")
    private boolean dcToDc;

    @Column(name = "llc_convert", columnDefinition = "TINYINT(1)")
    private boolean llcConvert;

    @Column(name = "ovp", columnDefinition = "TINYINT(1)")
    private boolean ovp;

    @Column(name = "uvp", columnDefinition = "TINYINT(1)")
    private boolean uvp;

    @Column(name = "ocp", columnDefinition = "TINYINT(1)")
    private boolean ocp;

    @Column(name = "olp", columnDefinition = "TINYINT(1)")
    private boolean olp;

    @Column(name = "opp", columnDefinition = "TINYINT(1)")
    private boolean opp;

    @Column(name = "otp_ohp", columnDefinition = "TINYINT(1)")
    private boolean otpOhp;

    @Column(name = "scp", columnDefinition = "TINYINT(1)")
    private boolean scp;

    @Column(name = "sip", columnDefinition = "TINYINT(1)")
    private boolean sip;

    @Column(name = "nlp", columnDefinition = "TINYINT(1)")
    private boolean nlp;

    @Column(name = "nlo", columnDefinition = "TINYINT(1)")
    private boolean nlo;

    @Column(name = "aura_sync", columnDefinition = "TINYINT(1)")
    private boolean auraSync;

    @Column(name = "mystic_light", columnDefinition = "TINYINT(1)")
    private boolean mysticLight;

    @Column(name = "rgb_fusion", columnDefinition = "TINYINT(1)")
    private boolean rgbFusion;

    @Column(name = "polychrome", columnDefinition = "TINYINT(1)")
    private boolean polychrome;

    @Column(name = "razer_chroma", columnDefinition = "TINYINT(1)")
    private boolean razerChroma;

    @Column(name = "tt_rgb_plus", columnDefinition = "TINYINT(1)")
    private boolean ttRgbPlus;

    @Column(name = "alert_sound", columnDefinition = "TINYINT(1)")
    private boolean alertSound;

    @Column(name = "led", columnDefinition = "TINYINT(1)")
    private boolean led;

    @Column(name = "output_voltage", columnDefinition = "VARCHAR(255)")
    private String outputVoltage;

    @Column(name = "output_w", columnDefinition = "INT")
    private int outputW;

    @Column(name = "output_va", columnDefinition = "INT")
    private int outputVa;

    @Column(name = "input_voltage", columnDefinition = "VARCHAR(255)")
    private String inputVoltage;

    @Column(name = "input_frequency", columnDefinition = "VARCHAR(255)")
    private String inputFrequency;

    @Column(name = "charge_time", columnDefinition = "VARCHAR(255)")
    private String chargeTime;

    @Column(name = "load100", columnDefinition = "VARCHAR(255)")
    private String load100;

    @Column(name = "load50", columnDefinition = "VARCHAR(255)")
    private String load50;

    @Column(name = "width", columnDefinition = "DOUBLE")
    private double width;

    @Column(name = "height", columnDefinition = "DOUBLE")
    private double height;

    @Column(name = "weight", columnDefinition = "DOUBLE")
    private double weight;

    public void modifyPrice(int price) {
        this.price = price;
    }
}
