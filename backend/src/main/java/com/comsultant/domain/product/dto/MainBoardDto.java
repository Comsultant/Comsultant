package com.comsultant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainBoardDto extends ProductDto {

    @Builder
    public MainBoardDto(long idx, int category, String name, int imgCnt,
                        String corp, String registeredAt, String type, String cpuSocket, String detailChipset,
                        int cpuCnt, String formFactor, String powerSupply, String vcore, String embedGraphic,
                        String memoryType, String memorySpeed, int memorySlot, String memoryChannel, int maxMemoryVolume,
                        int embedMemoryVolume, boolean xmp, boolean optane, boolean xmp3, String vga, boolean pcie5, boolean pcie4,
                        boolean pcie3, boolean pcie, int pci, int pciex16, int pciex8, int pciex4, int pciex1, int miniPcie,
                        boolean crossfire, boolean crossfireX, boolean sli, boolean hybridCfX, boolean lucidVirtu, int sata3,
                        String m2, int sas, int u2, int sata2, boolean sata, boolean nvme, boolean size2230, boolean size2242,
                        boolean size2260, boolean size2280, boolean size22110, boolean sataRaid, boolean nvmeRaid, boolean dsub,
                        boolean dvi, boolean hdmi, boolean dp, boolean typeC, String rj45, boolean spdif, boolean audioJack,
                        boolean ps2, boolean typec5g, boolean typec10g, boolean typec20g, boolean typea10g, int usb32, int usb31,
                        int usb3, int usb2, boolean thunderbolt4, boolean thunderbolt3, boolean typecAudio, boolean exSata,
                        boolean serialPort, boolean parallelPort, String wiredLanChipset, double wiredLanSpeed, String wiredlessLanChipset,
                        boolean wiredlessLan, boolean bluetooth, boolean m2KeyE, boolean dualLan, String embedSound, String analogOutput,
                        String rgb4, String argb3, String argb6, int systemFan4, int thunderboltHead4, int thunderboltHead3, int usb2Head,
                        int usb3Head, int usb31Head, int usb3TypecHead, int usb31TypecHead, int usb32TypecHead, boolean uefi, boolean drMos,
                        boolean led, boolean ledHead, boolean m2Heatsink, boolean tpmHead, boolean amdApu, String ledSystem, boolean embedLed,
                        boolean backLed, boolean frontLed, boolean logoLed, boolean ioShieldLed, boolean heatsinkLed, String distCorp) {
        super(idx, category, name, imgCnt);
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

    private String corp; //제조회사

    private String registeredAt; //등록년월

    private String type; //제품 분류

    private String cpuSocket; //CPU 소켓

    private String detailChipset; //세부 칩셋

    private int cpuCnt; //CPU 장착수

    private String formFactor; //폼팩터

    private String powerSupply; //전원부

    private String vcore; //Vcore 출력

    private String embedGraphic; //내장그래픽

    private String memoryType; //메모리 종류

    private String memorySpeed; //메모리 속도

    private int memorySlot; //메모리 슬롯

    private String memoryChannel; //메모리 채널

    private int maxMemoryVolume; //최대 메모리 용량

    private int embedMemoryVolume; //임베디드 메모리 용량

    private boolean xmp; //XMP

    private boolean optane; //옵테인

    private boolean xmp3; //XMP3.0

    private String vga; //VGA 연결

    private boolean pcie5; //PCIe5.0

    private boolean pcie4; //PCIe4.0

    private boolean pcie3; //PCIe3.0

    private boolean pcie; //PCIe

    private int pci; //PCI

    private int pciex16; //PCIex16

    private int pciex8; //PCIex8

    private int pciex4; //PCIex4

    private int pciex1; //PCIex1

    private int miniPcie; //mini-PCIe

    private boolean crossfire; //CrossFire

    private boolean crossfireX; //CrossFire X

    private boolean sli; //SLI

    private boolean hybridCfX; //Hybrid CF X

    private boolean lucidVirtu; //Lucid Virtu

    private int sata3; //SATA3

    private String m2; //M.2

    private int sas; //SAS

    private int u2; //U.2

    private int sata2; //SATA2

    private boolean sata; //SATA

    private boolean nvme; //NVMe

    private boolean size2230; //2230

    private boolean size2242; //2242

    private boolean size2260; //2260

    private boolean size2280; //2280

    private boolean size22110; //22110

    private boolean sataRaid; //SATA RAID

    private boolean nvmeRaid; //NVMe RAID

    private boolean dsub; //D-SUB

    private boolean dvi; //DVI

    private boolean hdmi; //HDMI

    private boolean dp; //DP

    private boolean typeC; //Type-C

    private String rj45; //RJ-45

    private boolean spdif; //S/PDIF

    private boolean audioJack; //오디오잭

    private boolean ps2; //PS/2

    private boolean typec5g; //Type-C(5Gbps)

    private boolean typec10g; //Type-C (10Gbps)

    private boolean typec20g; //Type-C (20Gbps)

    private boolean typea10g; //Type-A (10Gbps)

    private int usb32; //USB 3.2

    private int usb31; //USB 3.1

    private int usb3; //USB 3.0

    private int usb2; //USB 2.0

    private boolean thunderbolt4; //썬더볼트4

    private boolean thunderbolt3; //썬더볼트3

    private boolean typecAudio; //Type-C(오디오)

    private boolean exSata; //e-SATA

    private boolean serialPort; //시리얼포트

    private boolean parallelPort; //패러럴포트

    private String wiredLanChipset; //유선랜 칩셋

    private double wiredLanSpeed; //유선랜 속도

    private String wiredlessLanChipset; //무선랜 칩셋

    private boolean wiredlessLan; //무선 LAN

    private boolean bluetooth; //블루투스

    private boolean m2KeyE; //M.2 Key-E(모듈별매)

    private boolean dualLan; //듀얼 LAN

    private String embedSound; //내장 사운드

    private String analogOutput; //아날로그 출력

    private String rgb4; //RGB 헤더(4핀)

    private String argb3; //ARGB 헤더(3핀)

    private String argb6; //ARGB 헤더(6핀)

    private int systemFan4; //시스템팬 헤더(4핀)

    private int thunderboltHead4; //썬더볼트4 헤더

    private int thunderboltHead3; //썬더볼트3 헤더

    private int usb2Head; //USB2.0 헤더

    private int usb3Head; //USB3.0 헤더

    private int usb31Head; //USB3.1 헤더

    private int usb3TypecHead; //USB3.0 Type C 헤더

    private int usb31TypecHead; //USB3.1 Type C 헤더

    private int usb32TypecHead; //USB3.2 Type C 헤더

    private boolean uefi; //UEFI

    private boolean drMos; //Dr MOS

    private boolean led; //LED 라이트

    private boolean ledHead; //LED 헤더

    private boolean m2Heatsink; //M.2 히트싱크

    private boolean tpmHead; //TPM 헤더

    private boolean amdApu; //AMD APU 지원

    private String ledSystem; //LED 시스템

    private boolean embedLed; //내장형(온보드) LED

    private boolean backLed; //후면 LED

    private boolean frontLed; //전면 LED

    private boolean logoLed; //로고 LED

    private boolean ioShieldLed; //I/O 쉴드(아머) LED

    private boolean heatsinkLed; //히트 싱크 LED

    private String distCorp; //유통회사

}
