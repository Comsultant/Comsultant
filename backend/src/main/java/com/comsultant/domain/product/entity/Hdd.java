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
@Table(name = "hdd")
@DiscriminatorValue("3")
public class Hdd extends Product {

    @Builder
    public Hdd(int category, String name, int imgCnt,
               String corp, String registeredAt, String type, String diskSize, int diskVolume, String interfaces,
               int rotate, int bufferSize, int transSpeed, String recordMethod, int diskCnt, double thickness,
               boolean heliumCharge, boolean rvSensor, boolean dsa, boolean lowPower, boolean smart, boolean ise,
               boolean sed, int loadAmount, int guarantee, String noise, boolean year5, boolean year3, boolean year2,
               boolean year1, boolean restore3, boolean restore5, boolean restore2, boolean restore1) {
        super(category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.type = type;
        this.diskSize = diskSize;
        this.diskVolume = diskVolume;
        this.interfaces = interfaces;
        this.rotate = rotate;
        this.bufferSize = bufferSize;
        this.transSpeed = transSpeed;
        this.recordMethod = recordMethod;
        this.diskCnt = diskCnt;
        this.thickness = thickness;
        this.heliumCharge = heliumCharge;
        this.rvSensor = rvSensor;
        this.dsa = dsa;
        this.lowPower = lowPower;
        this.smart = smart;
        this.ise = ise;
        this.sed = sed;
        this.loadAmount = loadAmount;
        this.guarantee = guarantee;
        this.noise = noise;
        this.year5 = year5;
        this.year3 = year3;
        this.year2 = year2;
        this.year1 = year1;
        this.restore3 = restore3;
        this.restore5 = restore5;
        this.restore2 = restore2;
        this.restore1 = restore1;
    }

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "disk_size", columnDefinition = "VARCHAR(255)")
    private String diskSize;

    @Column(name = "disk_volume", columnDefinition = "INT")
    private int diskVolume;

    @Column(name = "interfaces", columnDefinition = "VARCHAR(255)")
    private String interfaces;

    @Column(name = "rotate", columnDefinition = "INT")
    private int rotate;

    @Column(name = "buffer_size", columnDefinition = "INT")
    private int bufferSize;

    @Column(name = "trans_speed", columnDefinition = "INT")
    private int transSpeed;

    @Column(name = "record_method", columnDefinition = "VARCHAR(255)")
    private String recordMethod;

    @Column(name = "disk_cnt", columnDefinition = "INT")
    private int diskCnt;

    @Column(name = "thickness", columnDefinition = "DOUBLE")
    private double thickness;

    @Column(name = "helium_charge", columnDefinition = "TINYINT(1)")
    private boolean heliumCharge;

    @Column(name = "rv_sensor", columnDefinition = "TINYINT(1)")
    private boolean rvSensor;

    @Column(name = "dsa", columnDefinition = "TINYINT(1)")
    private boolean dsa;

    @Column(name = "low_power", columnDefinition = "TINYINT(1)")
    private boolean lowPower;

    @Column(name = "smart", columnDefinition = "TINYINT(1)")
    private boolean smart;

    @Column(name = "ise", columnDefinition = "TINYINT(1)")
    private boolean ise;

    @Column(name = "sed", columnDefinition = "TINYINT(1)")
    private boolean sed;

    @Column(name = "load_amount", columnDefinition = "INT")
    private int loadAmount;

    @Column(name = "guarantee", columnDefinition = "INT")
    private int guarantee;

    @Column(name = "noise", columnDefinition = "VARCHAR(255)")
    private String noise;

    @Column(name = "year5", columnDefinition = "TINYINT(1)")
    private boolean year5;

    @Column(name = "year3", columnDefinition = "TINYINT(1)")
    private boolean year3;

    @Column(name = "year2", columnDefinition = "TINYINT(1)")
    private boolean year2;

    @Column(name = "year1", columnDefinition = "TINYINT(1)")
    private boolean year1;

    @Column(name = "restore3", columnDefinition = "TINYINT(1)")
    private boolean restore3;

    @Column(name = "restore5", columnDefinition = "TINYINT(1)")
    private boolean restore5;

    @Column(name = "restore2", columnDefinition = "TINYINT(1)")
    private boolean restore2;

    @Column(name = "restore1", columnDefinition = "TINYINT(1)")
    private boolean restore1;

}
