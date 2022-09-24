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
@Table(name = "cpu")
@DiscriminatorValue("1")
public class Cpu extends Product {

    @Builder
    public Cpu(int category, String name, int imgCnt,
               String corp, String registeredAt, String intelCpu, String amdCpu,
               String generation, String socket, String createdAt, String nano,
               String core, String threadCnt, double clockOriginal, double clockMax,
               String l2cash, String l3cash, String arithmeticSystem, String busSpeed, int tdp,
               int tdpMax, String pcieVersion, String pcieLane, int memoryMaxSize, String memoryFrame,
               String memoryClock, boolean memoryChannel, String includeVga, String includeVgaName,
               int includeVgaCoreSpeed, boolean hyperThreading, boolean optane, boolean storemi, boolean sensemi,
               boolean ryzenMaster, boolean vrReady, boolean vcash3d, String packageType, String includeCooler) {
        super(category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.intelCpu = intelCpu;
        this.amdCpu = amdCpu;
        this.generation = generation;
        this.socket = socket;
        this.createdAt = createdAt;
        this.nano = nano;
        this.core = core;
        this.threadCnt = threadCnt;
        this.clockOriginal = clockOriginal;
        this.clockMax = clockMax;
        this.l2cash = l2cash;
        this.l3cash = l3cash;
        this.arithmeticSystem = arithmeticSystem;
        this.busSpeed = busSpeed;
        this.tdp = tdp;
        this.tdpMax = tdpMax;
        this.pcieVersion = pcieVersion;
        this.pcieLane = pcieLane;
        this.memoryMaxSize = memoryMaxSize;
        this.memoryFrame = memoryFrame;
        this.memoryClock = memoryClock;
        this.memoryChannel = memoryChannel;
        this.includeVga = includeVga;
        this.includeVgaName = includeVgaName;
        this.includeVgaCoreSpeed = includeVgaCoreSpeed;
        this.hyperThreading = hyperThreading;
        this.optane = optane;
        this.storemi = storemi;
        this.sensemi = sensemi;
        this.ryzenMaster = ryzenMaster;
        this.vrReady = vrReady;
        this.vcash3d = vcash3d;
        this.packageType = packageType;
        this.includeCooler = includeCooler;
    }

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

    @Column(name = "include_vga", columnDefinition = "VARCHAR(255)")
    private String includeVga;

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
