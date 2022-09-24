package com.comsultant.api;

import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.mapper.*;
import com.comsultant.domain.product.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CpuRepository cpuRepository;

    @Autowired
    private RamRepository ramRepository;

    @Autowired
    private HddRepository hddRepository;

    @Autowired
    private SsdRepository ssdRepository;

    @Autowired
    private PsuRepository psuRepository;

    @Autowired
    private CoolerRepository coolerRepository;

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private MainBoardRepository mainBoardRepository;

    @Autowired
    private VgaRepository vgaRepository;



    @Test
    public void getCpuTest() {
//        Cpu cpu = (Cpu) productRepository.findByIdx(472410).orElse(null);
        Cpu cpu = cpuRepository.findByIdx(472410).orElse(null);
        CpuDto dto = CpuMapper.mapper.toDto(cpu);
        System.out.println(cpu.getName());
        System.out.println(cpu.getBusSpeed());
        assertThat(cpu.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getCasesTest() {
//        Cpu cpu = (Cpu) productRepository.findByIdx(472410).orElse(null);
        Cases cases = casesRepository.findByIdx(70053).orElse(null);
        CasesDto dto = CasesMapper.mapper.toDto(cases);
        System.out.println(cases.getName());
        System.out.println(cases.getBack());
        assertThat(cases.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getCoolerTest() {
        Cooler cooler = coolerRepository.findByIdx(125688).orElse(null);
        CoolerDto dto = CoolerMapper.mapper.toDto(cooler);
        System.out.println(cooler.getName());
        System.out.println(cooler.getNoiseMax());
        assertThat(cooler.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getHddTest() {
        Hdd product = hddRepository.findByIdx(66979).orElse(null);
        HddDto dto = HddMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getBufferSize());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getMbTest() {
        MainBoard product = mainBoardRepository.findByIdx(1357994).orElse(null);
        MainBoardDto dto = MainBoardMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getAnalogOutput());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getPsuTest() {
        Psu product = psuRepository.findByIdx(73075).orElse(null);
        PsuDto dto = PsuMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getHeight());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getRamTest() {
        Ram product = ramRepository.findByIdx(636450).orElse(null);
        RamDto dto = RamMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getActionClock());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getSsdTest() {
        Ssd product = ssdRepository.findByIdx(1187403).orElse(null);
        SsdDto dto = SsdMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getCorp());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void getVgaTest() {
        Vga product = vgaRepository.findByIdx(151759).orElse(null);
        VgaDto dto = VgaMapper.mapper.toDto(product);
        System.out.println(product.getName());
        System.out.println(product.getCorp());
        assertThat(product.getName()).isEqualTo(dto.getName());
    }

}
