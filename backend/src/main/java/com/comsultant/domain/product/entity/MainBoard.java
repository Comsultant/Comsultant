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
@Table(name = "mainboard")
@DiscriminatorValue("8")
public class MainBoard extends Product {

    @Builder
    public MainBoard(int category, String name, int imgCnt,
                     String corp, String registeredAt, String type, String cpuSocket, String detailChipset, int cpuCnt,
                     String formFactor, String powerSupply, String vcore, String embedGraphic, String memoryType,
                     String memorySpeed, int memorySlot, String memoryChannel, int maxMemoryVolume, int embedMemoryVolume,
                     boolean xmp, boolean optane, boolean xmp3, String vga, boolean pcie5, boolean pcie4, boolean pcie3,
                     boolean pcie, int pci, int pciex16, int pciex8, int pciex4, int pciex1, int miniPcie, boolean crossfire,
                     boolean crossfireX, boolean sli, boolean hybridCfX, boolean lucidVirtu, int sata3, String m2,
                     int sas, int u2, int sata2, boolean sata, boolean nvme, boolean size2230, boolean size2242, boolean size2260,
                     boolean size2280, boolean size22110, boolean sataRaid, boolean nvmeRaid, boolean dsub, boolean dvi, boolean hdmi,
                     boolean dp, boolean typeC, String rj45, boolean spdif, boolean audioJack, boolean ps2, boolean typec5g, boolean typec10g,
                     boolean typec20g, boolean typea10g, int usb32, int usb31, int usb3, int usb2, boolean thunderbolt4, boolean thunderbolt3,
                     boolean typecAudio, boolean exSata, boolean serialPort, boolean parallelPort, String wiredLanChipset, double wiredLanSpeed,
                     String wiredlessLanChipset, boolean wiredlessLan, boolean bluetooth, boolean m2KeyE, boolean dualLan,
                     String embedSound, String analogOutput, String rgb4, String argb3, String argb6, int systemFan4, int thunderboltHead4,
                     int thunderboltHead3, int usb2Head, int usb3Head, int usb31Head, int usb3TypecHead, int usb31TypecHead, int usb32TypecHead,
                     boolean uefi, boolean drMos, boolean led, boolean ledHead, boolean m2Heatsink, boolean tpmHead, boolean amdApu, String ledSystem,
                     boolean embedLed, boolean backLed, boolean frontLed, boolean logoLed, boolean ioShieldLed, boolean heatsinkLed, String distCorp) {
        super(category, name, imgCnt);
        this.corp = corp;
        this.registeredAt = registeredAt;
        this.type = type;
        this.cpuSocket = cpuSocket;
        this.detailChipset = detailChipset;
        this.cpuCnt = cpuCnt;
        this.formFactor = formFactor;
        this.powerSupply = powerSupply;
        this.vcore = vcore;
        this.embedGraphic = embedGraphic;
        this.memoryType = memoryType;
        this.memorySpeed = memorySpeed;
        this.memorySlot = memorySlot;
        this.memoryChannel = memoryChannel;
        this.maxMemoryVolume = maxMemoryVolume;
        this.embedMemoryVolume = embedMemoryVolume;
        this.xmp = xmp;
        this.optane = optane;
        this.xmp3 = xmp3;
        this.vga = vga;
        this.pcie5 = pcie5;
        this.pcie4 = pcie4;
        this.pcie3 = pcie3;
        this.pcie = pcie;
        this.pci = pci;
        this.pciex16 = pciex16;
        this.pciex8 = pciex8;
        this.pciex4 = pciex4;
        this.pciex1 = pciex1;
        this.miniPcie = miniPcie;
        this.crossfire = crossfire;
        this.crossfireX = crossfireX;
        this.sli = sli;
        this.hybridCfX = hybridCfX;
        this.lucidVirtu = lucidVirtu;
        this.sata3 = sata3;
        this.m2 = m2;
        this.sas = sas;
        this.u2 = u2;
        this.sata2 = sata2;
        this.sata = sata;
        this.nvme = nvme;
        this.size2230 = size2230;
        this.size2242 = size2242;
        this.size2260 = size2260;
        this.size2280 = size2280;
        this.size22110 = size22110;
        this.sataRaid = sataRaid;
        this.nvmeRaid = nvmeRaid;
        this.dsub = dsub;
        this.dvi = dvi;
        this.hdmi = hdmi;
        this.dp = dp;
        this.typeC = typeC;
        this.rj45 = rj45;
        this.spdif = spdif;
        this.audioJack = audioJack;
        this.ps2 = ps2;
        this.typec5g = typec5g;
        this.typec10g = typec10g;
        this.typec20g = typec20g;
        this.typea10g = typea10g;
        this.usb32 = usb32;
        this.usb31 = usb31;
        this.usb3 = usb3;
        this.usb2 = usb2;
        this.thunderbolt4 = thunderbolt4;
        this.thunderbolt3 = thunderbolt3;
        this.typecAudio = typecAudio;
        this.exSata = exSata;
        this.serialPort = serialPort;
        this.parallelPort = parallelPort;
        this.wiredLanChipset = wiredLanChipset;
        this.wiredLanSpeed = wiredLanSpeed;
        this.wiredlessLanChipset = wiredlessLanChipset;
        this.wiredlessLan = wiredlessLan;
        this.bluetooth = bluetooth;
        this.m2KeyE = m2KeyE;
        this.dualLan = dualLan;
        this.embedSound = embedSound;
        this.analogOutput = analogOutput;
        this.rgb4 = rgb4;
        this.argb3 = argb3;
        this.argb6 = argb6;
        this.systemFan4 = systemFan4;
        this.thunderboltHead4 = thunderboltHead4;
        this.thunderboltHead3 = thunderboltHead3;
        this.usb2Head = usb2Head;
        this.usb3Head = usb3Head;
        this.usb31Head = usb31Head;
        this.usb3TypecHead = usb3TypecHead;
        this.usb31TypecHead = usb31TypecHead;
        this.usb32TypecHead = usb32TypecHead;
        this.uefi = uefi;
        this.drMos = drMos;
        this.led = led;
        this.ledHead = ledHead;
        this.m2Heatsink = m2Heatsink;
        this.tpmHead = tpmHead;
        this.amdApu = amdApu;
        this.ledSystem = ledSystem;
        this.embedLed = embedLed;
        this.backLed = backLed;
        this.frontLed = frontLed;
        this.logoLed = logoLed;
        this.ioShieldLed = ioShieldLed;
        this.heatsinkLed = heatsinkLed;
        this.distCorp = distCorp;
    }

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "cpu_socket", columnDefinition = "VARCHAR(255)")
    private String cpuSocket;

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

    @Column(name = "memory_speed", columnDefinition = "VARCHAR(255)")
    private String memorySpeed;

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

    @Column(name = "pcie4", columnDefinition = "TINYINT(1)")
    private boolean pcie4;

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
