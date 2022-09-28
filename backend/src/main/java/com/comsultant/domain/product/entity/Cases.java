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
@Table(name = "cases")
public class Cases {

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

    @Column(name = "class_type", columnDefinition = "VARCHAR(255)")
    private String classType;

    @Column(name = "detail_class", columnDefinition = "VARCHAR(255)")
    private String detailClass;

    @Column(name = "size", columnDefinition = "VARCHAR(255)")
    private String size;

    @Column(name = "power", columnDefinition = "VARCHAR(255)")
    private String power;

    @Column(name = "power_size", columnDefinition = "VARCHAR(255)")
    private String powerSize;

    @Column(name = "extended_atx", columnDefinition = "TINYINT(1)")
    private boolean extendedAtx;

    @Column(name = "standard_atx", columnDefinition = "TINYINT(1)")
    private boolean standardAtx;

    @Column(name = "micro_atx", columnDefinition = "TINYINT(1)")
    private boolean microAtx;

    @Column(name = "flex_atx", columnDefinition = "TINYINT(1)")
    private boolean flexAtx;

    @Column(name = "standard_itx", columnDefinition = "TINYINT(1)")
    private boolean standardItx;

    @Column(name = "mini_itx", columnDefinition = "TINYINT(1)")
    private boolean miniItx;

    @Column(name = "ssi_ceb", columnDefinition = "TINYINT(1)")
    private boolean ssiCeb;

    @Column(name = "ssi_eeb", columnDefinition = "TINYINT(1)")
    private boolean ssiEeb;

    @Column(name = "mini_dtx", columnDefinition = "TINYINT(1)")
    private boolean miniDtx;

    @Column(name = "bay13", columnDefinition = "VARCHAR(255)")
    private String bay13;

    @Column(name = "bay8", columnDefinition = "INT")
    private int bay8;

    @Column(name = "bay6", columnDefinition = "INT")
    private int bay6;

    @Column(name = "storage_device", columnDefinition = "INT")
    private int storageDevice;

    @Column(name = "pci_slot", columnDefinition = "VARCHAR(255)")
    private String pciSlot;

    @Column(name = "vertical_pci_slot", columnDefinition = "VARCHAR(255)")
    private String verticalPciSlot;

    @Column(name = "cooling_fan", columnDefinition = "INT")
    private int coolingFan;

    @Column(name = "led_fan", columnDefinition = "INT")
    private int ledFan;

    @Column(name = "side", columnDefinition = "VARCHAR(255)")
    private String side;

    @Column(name = "back", columnDefinition = "VARCHAR(255)")
    private String back;

    @Column(name = "front", columnDefinition = "VARCHAR(255)")
    private String front;

    @Column(name = "top", columnDefinition = "VARCHAR(255)")
    private String top;

    @Column(name = "bottom", columnDefinition = "VARCHAR(255)")
    private String bottom;

    @Column(name = "inner_top", columnDefinition = "VARCHAR(255)")
    private String innerTop;

    @Column(name = "inner_side", columnDefinition = "VARCHAR(255)")
    private String innerSide;

    @Column(name = "filter", columnDefinition = "VARCHAR(255)")
    private String filter;

    @Column(name = "sound", columnDefinition = "VARCHAR(255)")
    private String sound;

    @Column(name = "usb", columnDefinition = "TINYINT(1)")
    private boolean usb;

    @Column(name = "usb3", columnDefinition = "TINYINT(1)")
    private boolean usb3;

    @Column(name = "typec3", columnDefinition = "TINYINT(1)")
    private boolean typec3;

    @Column(name = "typec31", columnDefinition = "TINYINT(1)")
    private boolean typec31;

    @Column(name = "typea31", columnDefinition = "TINYINT(1)")
    private boolean typea31;

    @Column(name = "ieee", columnDefinition = "TINYINT(1)")
    private boolean ieee;

    @Column(name = "esata", columnDefinition = "TINYINT(1)")
    private boolean esata;

    @Column(name = "hdmi", columnDefinition = "TINYINT(1)")
    private boolean hdmi;

    @Column(name = "card_reader", columnDefinition = "TINYINT(1)")
    private boolean cardReader;

    @Column(name = "width", columnDefinition = "DOUBLE")
    private double width;

    @Column(name = "deepth", columnDefinition = "DOUBLE")
    private double deepth;

    @Column(name = "height", columnDefinition = "DOUBLE")
    private double height;

    @Column(name = "power_mounting", columnDefinition = "INT")
    private int powerMounting;

    @Column(name = "power_location", columnDefinition = "VARCHAR(255)")
    private String powerLocation;

    @Column(name = "gpu_mounting", columnDefinition = "INT")
    private int gpuMounting;

    @Column(name = "cpu_cooler_mounting", columnDefinition = "INT")
    private int cpuCoolerMounting;

    @Column(name = "water_cooler_size", columnDefinition = "INT")
    private int waterCoolerSize;

    @Column(name = "radiator_top", columnDefinition = "VARCHAR(255)")
    private String radiatorTop;

    @Column(name = "radiator_front", columnDefinition = "VARCHAR(255)")
    private String radiatorFront;

    @Column(name = "radiator_back", columnDefinition = "VARCHAR(255)")
    private String radiatorBack;

    @Column(name = "case_color_type", columnDefinition = "VARCHAR(255)")
    private String caseColorType;

    @Column(name = "outside_led_controll", columnDefinition = "TINYINT(1)")
    private boolean outsideLedControll;

    @Column(name = "rgb_controll", columnDefinition = "TINYINT(1)")
    private boolean rgbControll;

    @Column(name = "outside_led", columnDefinition = "TINYINT(1)")
    private boolean outsideLed;

}
