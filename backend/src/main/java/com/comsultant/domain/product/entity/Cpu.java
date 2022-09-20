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
@Table(name = "cpu")
public class Cpu {

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

    @Column(name = "intel_cpu", columnDefinition = "VARCHAR(255)")
    private String intelCpu;

    @Column(name = "amd_cpu", columnDefinition = "VARCHAR(255)")
    private String amdCpu;

    @Column(name = "generation", columnDefinition = "VARCHAR(255)")
    private String generation;

    @Column(name = "socket", columnDefinition = "VARCHAR(255)")
    private String socket;

    @Column(name = "created_at", columnDefinition = "VARCHAR(255)")
    private String createdAt;

    @Column(name = "nano", columnDefinition = "VARCHAR(255)")
    private String nano;

    @Column(name = "core", columnDefinition = "VARCHAR(255)")
    private String core;

    @Column(name = "thread_cnt", columnDefinition = "VARCHAR(255)")
    private String threadCnt;

    @Column(name = "clock_original", columnDefinition = "DOUBLE")
    private double clockOriginal;

    @Column(name = "clock_max", columnDefinition = "DOUBLE")
    private double clockMax;

    @Column(name = "l2cash", columnDefinition = "VARCHAR(255)")
    private String l2cash;

    @Column(name = "l3cash", columnDefinition = "VARCHAR(255)")
    private String l3cash;

    @Column(name = "arithmetic_system", columnDefinition = "VARCHAR(255)")
    private String arithmeticSystem;

    @Column(name = "bus_speed", columnDefinition = "VARCHAR(255)")
    private String busSpeed;

    @Column(name = "tdp", columnDefinition = "INT")
    private int tdp;

    @Column(name = "tdp_max", columnDefinition = "INT")
    private int tdpMax;

    @Column(name = "pcie_version", columnDefinition = "VARCHAR(255)")
    private String pcieVersion;

    @Column(name = "pcie_lane", columnDefinition = "VARCHAR(255)")
    private String pcieLane;

    @Column(name = "memory_max_size", columnDefinition = "INT")
    private int memoryMaxSize;

    @Column(name = "memory_frame", columnDefinition = "VARCHAR(255)")
    private String memoryFrame;

    @Column(name = "memory_clock", columnDefinition = "VARCHAR(255)")
    private String memoryClock;

    @Column(name = "memory_channel", columnDefinition = "TINYINT(1)")
    private boolean memoryChannel;

    @Column(name = "include_vga", columnDefinition = "TINYINT(1)")
    private boolean includeVga;

    @Column(name = "include_vga_name", columnDefinition = "VARCHAR(255)")
    private String includeVgaName;

    @Column(name = "include_vga_core_speed", columnDefinition = "INT")
    private int includeVgaCoreSpeed;

    @Column(name = "hyper_threading", columnDefinition = "TINYINT(1)")
    private boolean hyperThreading;

    @Column(name = "optane", columnDefinition = "TINYINT(1)")
    private boolean optane;

    @Column(name = "storemi", columnDefinition = "TINYINT(1)")
    private boolean storemi;

    @Column(name = "sensemi", columnDefinition = "TINYINT(1)")
    private boolean sensemi;

    @Column(name = "ryzen_master", columnDefinition = "TINYINT(1)")
    private boolean ryzenMaster;

    @Column(name = "vr_ready", columnDefinition = "TINYINT(1)")
    private boolean vrReady;

    @Column(name = "vcash_3d", columnDefinition = "TINYINT(1)")
    private boolean vcash3d;

    @Column(name = "package_type", columnDefinition = "VARCHAR(255)")
    private String packageType;

    @Column(name = "include_cooler", columnDefinition = "VARCHAR(255)")
    private String includeCooler;

}
