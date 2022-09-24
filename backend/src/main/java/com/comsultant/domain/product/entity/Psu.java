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
@Table(name = "psu")
@DiscriminatorValue("5")
public class Psu extends Product {

    @Builder
    public Psu(int category, String name, int imgCnt,
               String corporation, String registeredAt, String type, int ratedPower, String plusCert, String etaCert,
               String lambdaCert, String volChange, String outputMeth12v, int avail12v, String pfcCircuit, int pf,
               int coolingFanSize, int coolingFanCnt, String bearing, double depth, String asPeriod, String output3v,
               double output5v, String output12v, String outputMinus12v, String output5vsb, String cableConn, String mainPower,
               int subPower4, int subPower8, int pcie8, String pcie6, int sata, int sata33, int ide4pin, int fdd, boolean fanlessMode,
               boolean autoFanControl, boolean manualFanControl, boolean standbyPower1w, boolean flatCable, boolean freeBolt,
               boolean ledLight, boolean digitalControl, boolean condenser85, boolean condenser105, boolean dcToDc, boolean llcConvert,
               boolean ovp, boolean uvp, boolean ocp, boolean olp, boolean opp, boolean otpOhp, boolean scp, boolean sip, boolean nlp,
               boolean nlo, boolean auraSync, boolean mysticLight, boolean rgbFusion, boolean polychrome, boolean razerChroma,
               boolean ttRgbPlus, boolean alertSound, boolean led, String outputVoltage, int outputW, int outputVa, String inputVoltage,
               String inputFrequency, String chargeTime, String load100, String load50, double width, double height, double weight) {
        super(category, name, imgCnt);
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

    @Column(name = "corporation", columnDefinition = "VARCHAR(255)")
    private String corporation;

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

}
