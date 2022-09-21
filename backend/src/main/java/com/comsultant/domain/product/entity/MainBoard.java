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
@Table(name = "mainboard")
public class MainBoard {

    @Id
    @Column(name = "product_idx", nullable = false)
    private long idx;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_idx", columnDefinition = "BIGINT(20) UNSIGNED")
    private Product product;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "img_cnt", columnDefinition = "INT")
    private int imgCnt;

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "detail_chipset", columnDefinition = "VARCHAR(255)")
    private String detailChipset;

    @Column(name = "cpu_cnt", columnDefinition = "INT")
    private int cpuCnt;

    @Column(name = "form_factor", columnDefinition = "VARCHAR(255)")
    private String formFactor;

    @Column(name = "power_supply", columnDefinition = "VARCHAR(255)")
    private String powerSupply;

    @Column(name = "vcore", columnDefinition = "VARCHAR(255)")
    private String vcore;

    @Column(name = "embed_graphic", columnDefinition = "VARCHAR(255)")
    private String embedGraphic;

    @Column(name = "memory_type", columnDefinition = "VARCHAR(255)")
    private String memoryType;

    @Column(name = "memory_slot", columnDefinition = "INT")
    private int memorySlot;

    @Column(name = "memory_channel", columnDefinition = "VARCHAR(255)")
    private String memoryChannel;

    @Column(name = "max_memory_volume", columnDefinition = "INT")
    private int maxMemoryVolume;

    @Column(name = "embed_memory_volume", columnDefinition = "INT")
    private int embedMemoryVolume;

    @Column(name = "xmp", columnDefinition = "TINYINT(1)")
    private boolean xmp;

    @Column(name = "optane", columnDefinition = "TINYINT(1)")
    private boolean optane;

    @Column(name = "xmp3", columnDefinition = "TINYINT(1)")
    private boolean xmp3;

    @Column(name = "vga", columnDefinition = "VARCHAR(255)")
    private String vga;

    @Column(name = "pcie5", columnDefinition = "TINYINT(1)")
    private boolean pcie5;

    @Column(name = "pcie3", columnDefinition = "TINYINT(1)")
    private boolean pcie3;

    @Column(name = "pcie", columnDefinition = "TINYINT(1)")
    private boolean pcie;

    @Column(name = "pci", columnDefinition = "INT")
    private int pci;

    @Column(name = "pciex16", columnDefinition = "INT")
    private int pciex16;

    @Column(name = "pciex8", columnDefinition = "INT")
    private int pciex8;

    @Column(name = "pciex4", columnDefinition = "INT")
    private int pciex4;

    @Column(name = "pciex1", columnDefinition = "INT")
    private int pciex1;

    @Column(name = "mini_pcie", columnDefinition = "INT")
    private int miniPcie;

    @Column(name = "crossfire", columnDefinition = "TINYINT(1)")
    private boolean crossfire;

    @Column(name = "crossfire_x", columnDefinition = "TINYINT(1)")
    private boolean crossfireX;

    @Column(name = "sli", columnDefinition = "TINYINT(1)")
    private boolean sli;

    @Column(name = "hybrid_cf_x", columnDefinition = "TINYINT(1)")
    private boolean hybridCfX;

    @Column(name = "lucid_virtu", columnDefinition = "TINYINT(1)")
    private boolean lucidVirtu;

    @Column(name = "sata3", columnDefinition = "INT")
    private int sata3;

    @Column(name = "m2", columnDefinition = "VARCHAR(255)")
    private String m2;

    @Column(name = "sas", columnDefinition = "INT")
    private int sas;

    @Column(name = "u2", columnDefinition = "INT")
    private int u2;

    @Column(name = "sata2", columnDefinition = "INT")
    private int sata2;

    @Column(name = "sata", columnDefinition = "TINYINT(1)")
    private boolean sata;

    @Column(name = "nvme", columnDefinition = "TINYINT(1)")
    private boolean nvme;

    @Column(name = "size2230", columnDefinition = "TINYINT(1)")
    private boolean size2230;

    @Column(name = "size2242", columnDefinition = "TINYINT(1)")
    private boolean size2242;

    @Column(name = "size2260", columnDefinition = "TINYINT(1)")
    private boolean size2260;

    @Column(name = "size2280", columnDefinition = "TINYINT(1)")
    private boolean size2280;

    @Column(name = "size22110", columnDefinition = "TINYINT(1)")
    private boolean size22110;

    @Column(name = "sata_raid", columnDefinition = "TINYINT(1)")
    private boolean sataRaid;

    @Column(name = "nvme_raid", columnDefinition = "TINYINT(1)")
    private boolean nvmeRaid;

    @Column(name = "dsub", columnDefinition = "TINYINT(1)")
    private boolean dsub;

    @Column(name = "dvi", columnDefinition = "TINYINT(1)")
    private boolean dvi;

    @Column(name = "hdmi", columnDefinition = "TINYINT(1)")
    private boolean hdmi;

    @Column(name = "dp", columnDefinition = "TINYINT(1)")
    private boolean dp;

    @Column(name = "type_c", columnDefinition = "TINYINT(1)")
    private boolean typeC;

    @Column(name = "rj45", columnDefinition = "VARCHAR(255)")
    private String rj45;

    @Column(name = "spdif", columnDefinition = "TINYINT(1)")
    private boolean spdif;

    @Column(name = "audio_jack", columnDefinition = "TINYINT(1)")
    private boolean audioJack;

    @Column(name = "ps2", columnDefinition = "TINYINT(1)")
    private boolean ps2;

    @Column(name = "typec_5g", columnDefinition = "TINYINT(1)")
    private boolean typec5g;

    @Column(name = "typec_10g", columnDefinition = "TINYINT(1)")
    private boolean typec10g;

    @Column(name = "typec_20g", columnDefinition = "TINYINT(1)")
    private boolean typec20g;

    @Column(name = "typea_10g", columnDefinition = "TINYINT(1)")
    private boolean typea10g;

    @Column(name = "usb32", columnDefinition = "INT")
    private int usb32;

    @Column(name = "usb31", columnDefinition = "INT")
    private int usb31;

    @Column(name = "usb3", columnDefinition = "INT")
    private int usb3;

    @Column(name = "usb2", columnDefinition = "INT")
    private int usb2;

    @Column(name = "thunderbolt4", columnDefinition = "TINYINT(1)")
    private boolean thunderbolt4;

    @Column(name = "thunderbolt3", columnDefinition = "TINYINT(1)")
    private boolean thunderbolt3;

    @Column(name = "typec_audio", columnDefinition = "TINYINT(1)")
    private boolean typecAudio;

    @Column(name = "ex_sata", columnDefinition = "TINYINT(1)")
    private boolean exSata;

    @Column(name = "serial_port", columnDefinition = "TINYINT(1)")
    private boolean serialPort;

    @Column(name = "parallel_port", columnDefinition = "TINYINT(1)")
    private boolean parallelPort;

    @Column(name = "wired_lan_chipset", columnDefinition = "VARCHAR(255)")
    private String wiredLanChipset;

    @Column(name = "wired_lan_speed", columnDefinition = "DOUBLE")
    private double wiredLanSpeed;

    @Column(name = "wiredless_lan_chipset", columnDefinition = "VARCHAR(255)")
    private String wiredlessLanChipset;

    @Column(name = "wiredless_lan", columnDefinition = "TINYINT(1)")
    private boolean wiredlessLan;

    @Column(name = "bluetooth", columnDefinition = "TINYINT(1)")
    private boolean bluetooth;

    @Column(name = "m2_key_e", columnDefinition = "TINYINT(1)")
    private boolean m2KeyE;

    @Column(name = "dual_lan", columnDefinition = "TINYINT(1)")
    private boolean dualLan;

    @Column(name = "embed_sound", columnDefinition = "VARCHAR(255)")
    private String embedSound;

    @Column(name = "analog_output", columnDefinition = "VARCHAR(255)")
    private String analogOutput;

    @Column(name = "rgb_4", columnDefinition = "VARCHAR(255)")
    private String rgb4;

    @Column(name = "argb_3", columnDefinition = "VARCHAR(255)")
    private String argb3;

    @Column(name = "argb_6", columnDefinition = "VARCHAR(255)")
    private String argb6;

    @Column(name = "system_fan_4", columnDefinition = "INT")
    private int systemFan4;

    @Column(name = "thunderbolt_head4", columnDefinition = "INT")
    private int thunderboltHead4;

    @Column(name = "thunderbolt_head3", columnDefinition = "INT")
    private int thunderboltHead3;

    @Column(name = "usb2_head", columnDefinition = "INT")
    private int usb2Head;

    @Column(name = "usb3_head", columnDefinition = "INT")
    private int usb3Head;

    @Column(name = "usb31_head", columnDefinition = "INT")
    private int usb31Head;

    @Column(name = "usb3_typec_head", columnDefinition = "INT")
    private int usb3TypecHead;

    @Column(name = "usb31_typec_head", columnDefinition = "INT")
    private int usb31TypecHead;

    @Column(name = "usb32_typec_head", columnDefinition = "INT")
    private int usb32TypecHead;

    @Column(name = "uefi", columnDefinition = "TINYINT(1)")
    private boolean uefi;

    @Column(name = "dr_mos", columnDefinition = "TINYINT(1)")
    private boolean drMos;

    @Column(name = "led", columnDefinition = "TINYINT(1)")
    private boolean led;

    @Column(name = "led_head", columnDefinition = "TINYINT(1)")
    private boolean ledHead;

    @Column(name = "m2_heatsink", columnDefinition = "TINYINT(1)")
    private boolean m2Heatsink;

    @Column(name = "tpm_head", columnDefinition = "TINYINT(1)")
    private boolean tpmHead;

    @Column(name = "amd_apu", columnDefinition = "TINYINT(1)")
    private boolean amdApu;

    @Column(name = "led_system", columnDefinition = "VARCHAR(255)")
    private String ledSystem;

    @Column(name = "embed_led", columnDefinition = "TINYINT(1)")
    private boolean embedLed;

    @Column(name = "back_led", columnDefinition = "TINYINT(1)")
    private boolean backLed;

    @Column(name = "front_led", columnDefinition = "TINYINT(1)")
    private boolean frontLed;

    @Column(name = "logo_led", columnDefinition = "TINYINT(1)")
    private boolean logoLed;

    @Column(name = "io_shield_led", columnDefinition = "TINYINT(1)")
    private boolean ioShieldLed;

    @Column(name = "heatsink_led", columnDefinition = "TINYINT(1)")
    private boolean heatsinkLed;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

}
