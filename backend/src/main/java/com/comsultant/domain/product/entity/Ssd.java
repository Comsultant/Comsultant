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
@Table(name = "ssd")
@DiscriminatorValue("4")
public class Ssd extends Product {

    @Builder
    public Ssd(int category, String name, int imgCnt,
               String registeredAt, String corp, String type, String detailType, String formFactor, String interfaces,
               String protocol, int volume, String memoryType, String nandStructure, String ram, String ramType,
               String controller, String consoleGame, String process, String sizeChange, String interfaceChange,
               int seqRead, int seqWrite, double readIops, double writeIops, boolean trim, boolean gc, boolean slcCashing,
               boolean smart, boolean ecc, boolean devslp, boolean aes, boolean privateSw, double tbw, boolean macbookUpgrade,
               boolean migration, boolean manageFunction, String mtbf, boolean year5, boolean year3, boolean restore3,
               boolean restore1, boolean limitRestore, String nvmeHeatsink, String width, String height, String thickness,
               String weight, String distCorp, boolean type25, boolean type35, boolean miniSata, String installCnt,
               boolean m22230, boolean m22242, boolean m22260, boolean m22280, boolean m222110, String supportThickness,
               String useClass, String powerSupply, boolean protect, boolean white, boolean yellow, boolean green, boolean purple,
               boolean grey, boolean blue, boolean black, boolean silver, boolean waterproof, String texture, boolean locked, boolean subPower) {
        super(category, name, imgCnt);
        this.registeredAt = registeredAt;
        this.corp = corp;
        this.type = type;
        this.detailType = detailType;
        this.formFactor = formFactor;
        this.interfaces = interfaces;
        this.protocol = protocol;
        this.volume = volume;
        this.memoryType = memoryType;
        this.nandStructure = nandStructure;
        this.ram = ram;
        this.ramType = ramType;
        this.controller = controller;
        this.consoleGame = consoleGame;
        this.process = process;
        this.sizeChange = sizeChange;
        this.interfaceChange = interfaceChange;
        this.seqRead = seqRead;
        this.seqWrite = seqWrite;
        this.readIops = readIops;
        this.writeIops = writeIops;
        this.trim = trim;
        this.gc = gc;
        this.slcCashing = slcCashing;
        this.smart = smart;
        this.ecc = ecc;
        this.devslp = devslp;
        this.aes = aes;
        this.privateSw = privateSw;
        this.tbw = tbw;
        this.macbookUpgrade = macbookUpgrade;
        this.migration = migration;
        this.manageFunction = manageFunction;
        this.mtbf = mtbf;
        this.year5 = year5;
        this.year3 = year3;
        this.restore3 = restore3;
        this.restore1 = restore1;
        this.limitRestore = limitRestore;
        this.nvmeHeatsink = nvmeHeatsink;
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        this.weight = weight;
        this.distCorp = distCorp;
        this.type25 = type25;
        this.type35 = type35;
        this.miniSata = miniSata;
        this.installCnt = installCnt;
        this.m22230 = m22230;
        this.m22242 = m22242;
        this.m22260 = m22260;
        this.m22280 = m22280;
        this.m222110 = m222110;
        this.supportThickness = supportThickness;
        this.useClass = useClass;
        this.powerSupply = powerSupply;
        this.protect = protect;
        this.white = white;
        this.yellow = yellow;
        this.green = green;
        this.purple = purple;
        this.grey = grey;
        this.blue = blue;
        this.black = black;
        this.silver = silver;
        this.waterproof = waterproof;
        this.texture = texture;
        this.locked = locked;
        this.subPower = subPower;
    }

    @Column(name = "registered_at", columnDefinition = "VARCHAR(255)")
    private String registeredAt;

    @Column(name = "corp", columnDefinition = "VARCHAR(255)")
    private String corp;

    @Column(name = "type", columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "detail_type", columnDefinition = "VARCHAR(255)")
    private String detailType;

    @Column(name = "form_factor", columnDefinition = "VARCHAR(255)")
    private String formFactor;

    @Column(name = "interfaces", columnDefinition = "VARCHAR(255)")
    private String interfaces;

    @Column(name = "protocol", columnDefinition = "VARCHAR(255)")
    private String protocol;

    @Column(name = "volume", columnDefinition = "INT")
    private int volume;

    @Column(name = "memory_type", columnDefinition = "VARCHAR(255)")
    private String memoryType;

    @Column(name = "nand_structure", columnDefinition = "VARCHAR(255)")
    private String nandStructure;

    @Column(name = "ram", columnDefinition = "VARCHAR(255)")
    private String ram;

    @Column(name = "ram_type", columnDefinition = "VARCHAR(255)")
    private String ramType;

    @Column(name = "controller", columnDefinition = "VARCHAR(255)")
    private String controller;

    @Column(name = "console_game", columnDefinition = "VARCHAR(255)")
    private String consoleGame;

    @Column(name = "process", columnDefinition = "VARCHAR(255)")
    private String process;

    @Column(name = "size_change", columnDefinition = "VARCHAR(255)")
    private String sizeChange;

    @Column(name = "interface_change", columnDefinition = "VARCHAR(255)")
    private String interfaceChange;

    @Column(name = "seq_read", columnDefinition = "INT")
    private int seqRead;

    @Column(name = "seq_write", columnDefinition = "INT")
    private int seqWrite;

    @Column(name = "read_iops", columnDefinition = "DOUBLE")
    private double readIops;

    @Column(name = "write_iops", columnDefinition = "DOUBLE")
    private double writeIops;

    @Column(name = "trim", columnDefinition = "TINYINT(1)")
    private boolean trim;

    @Column(name = "gc", columnDefinition = "TINYINT(1)")
    private boolean gc;

    @Column(name = "slc_cashing", columnDefinition = "TINYINT(1)")
    private boolean slcCashing;

    @Column(name = "smart", columnDefinition = "TINYINT(1)")
    private boolean smart;

    @Column(name = "ecc", columnDefinition = "TINYINT(1)")
    private boolean ecc;

    @Column(name = "devslp", columnDefinition = "TINYINT(1)")
    private boolean devslp;

    @Column(name = "aes", columnDefinition = "TINYINT(1)")
    private boolean aes;

    @Column(name = "private_sw", columnDefinition = "TINYINT(1)")
    private boolean privateSw;

    @Column(name = "tbw", columnDefinition = "DOUBLE")
    private double tbw;

    @Column(name = "macbook_upgrade", columnDefinition = "TINYINT(1)")
    private boolean macbookUpgrade;

    @Column(name = "migration", columnDefinition = "TINYINT(1)")
    private boolean migration;

    @Column(name = "manage_function", columnDefinition = "TINYINT(1)")
    private boolean manageFunction;

    @Column(name = "mtbf", columnDefinition = "VARCHAR(255)")
    private String mtbf;

    @Column(name = "year5", columnDefinition = "TINYINT(1)")
    private boolean year5;

    @Column(name = "year3", columnDefinition = "TINYINT(1)")
    private boolean year3;

    @Column(name = "restore3", columnDefinition = "TINYINT(1)")
    private boolean restore3;

    @Column(name = "restore1", columnDefinition = "TINYINT(1)")
    private boolean restore1;

    @Column(name = "limit_restore", columnDefinition = "TINYINT(1)")
    private boolean limitRestore;

    @Column(name = "nvme_heatsink", columnDefinition = "VARCHAR(255)")
    private String nvmeHeatsink;

    @Column(name = "width", columnDefinition = "VARCHAR(255)")
    private String width;

    @Column(name = "height", columnDefinition = "VARCHAR(255)")
    private String height;

    @Column(name = "thickness", columnDefinition = "VARCHAR(255)")
    private String thickness;

    @Column(name = "weight", columnDefinition = "VARCHAR(255)")
    private String weight;

    @Column(name = "dist_corp", columnDefinition = "VARCHAR(255)")
    private String distCorp;

    @Column(name = "type25", columnDefinition = "TINYINT(1)")
    private boolean type25;

    @Column(name = "type35", columnDefinition = "TINYINT(1)")
    private boolean type35;

    @Column(name = "mini_sata", columnDefinition = "TINYINT(1)")
    private boolean miniSata;

    @Column(name = "install_cnt", columnDefinition = "VARCHAR(255)")
    private String installCnt;

    @Column(name = "m2_2230", columnDefinition = "TINYINT(1)")
    private boolean m22230;

    @Column(name = "m2_2242", columnDefinition = "TINYINT(1)")
    private boolean m22242;

    @Column(name = "m2_2260", columnDefinition = "TINYINT(1)")
    private boolean m22260;

    @Column(name = "m2_2280", columnDefinition = "TINYINT(1)")
    private boolean m22280;

    @Column(name = "m2_22110", columnDefinition = "TINYINT(1)")
    private boolean m222110;

    @Column(name = "support_thickness", columnDefinition = "VARCHAR(255)")
    private String supportThickness;

    @Column(name = "use_class", columnDefinition = "VARCHAR(255)")
    private String useClass;

    @Column(name = "power_supply", columnDefinition = "VARCHAR(255)")
    private String powerSupply;

    @Column(name = "protect", columnDefinition = "TINYINT(1)")
    private boolean protect;

    @Column(name = "white", columnDefinition = "TINYINT(1)")
    private boolean white;

    @Column(name = "yellow", columnDefinition = "TINYINT(1)")
    private boolean yellow;

    @Column(name = "green", columnDefinition = "TINYINT(1)")
    private boolean green;

    @Column(name = "purple", columnDefinition = "TINYINT(1)")
    private boolean purple;

    @Column(name = "grey", columnDefinition = "TINYINT(1)")
    private boolean grey;

    @Column(name = "blue", columnDefinition = "TINYINT(1)")
    private boolean blue;

    @Column(name = "black", columnDefinition = "TINYINT(1)")
    private boolean black;

    @Column(name = "silver", columnDefinition = "TINYINT(1)")
    private boolean silver;

    @Column(name = "waterproof", columnDefinition = "TINYINT(1)")
    private boolean waterproof;

    @Column(name = "texture", columnDefinition = "VARCHAR(255)")
    private String texture;

    @Column(name = "locked", columnDefinition = "TINYINT(1)")
    private boolean locked;

    @Column(name = "sub_power", columnDefinition = "TINYINT(1)")
    private boolean subPower;

}
