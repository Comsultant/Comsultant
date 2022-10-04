package com.comsultant.domain.product.service;

import com.comsultant.domain.product.dto.*;
import com.comsultant.domain.product.dto.filterResponse.*;
import com.comsultant.domain.product.dto.request.*;
import com.comsultant.domain.product.entity.*;
import com.comsultant.domain.product.mapper.*;
import com.comsultant.domain.product.repository.*;
import com.comsultant.domain.product.service.specification.*;
import com.comsultant.global.common.model.ProductCategory;
import com.comsultant.global.error.exception.ProductApiException;
import com.comsultant.global.error.model.ProductErrorCode;
import com.comsultant.global.properties.ConstProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //생성자 주입. final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성. AutoWired 불필요
@Slf4j //로깅 어노테이션
public class ProductServiceImpl implements ProductService {

    private final MongoTemplate mongoTemplate;

    private final ProductRepository productRepository;
    private final CasesRepository casesRepository;
    private final CpuRepository cpuRepository;
    private final CoolerRepository coolerRepository;
    private final HddRepository hddRepository;
    private final MainBoardRepository mainBoardRepository;
    private final PsuRepository psuRepository;
    private final RamRepository ramRepository;
    private final SsdRepository ssdRepository;
    private final VgaRepository vgaRepository;
    private final ConstProperties constProperties;
    private final EntityManager entityManager;

    @Override
    public ProductDto getProduct(long idx) {
        return ProductMapper.mapper.toDto(productRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CpuDto getCpu(long idx) {
        return CpuMapper.mapper.toDto(cpuRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public RamDto getRam(long idx) {
        return RamMapper.mapper.toDto(ramRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CasesDto getCases(long idx) {
        return CasesMapper.mapper.toDto(casesRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public HddDto getHdd(long idx) {
        return HddMapper.mapper.toDto(hddRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public MainBoardDto getMainBoard(long idx) {
        return MainBoardMapper.mapper.toDto(mainBoardRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public CoolerDto getCooler(long idx) {
        return CoolerMapper.mapper.toDto(coolerRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public PsuDto getPsu(long idx) {
        return PsuMapper.mapper.toDto(psuRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public SsdDto getSsd(long idx) {
        return SsdMapper.mapper.toDto(ssdRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public VgaDto getVga(long idx) {
        return VgaMapper.mapper.toDto(vgaRepository.findById(idx)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public Object getObject(long type, long idx) {
        Object obj = null;

        if (type == 1) { // 1.cpu, 2.ram, 3.hdd, 4.ssd, 5.psu, 6.cooler, 7.cases, 8.mainboard, 9.vga
            obj = getCpu(idx);
        } else if (type == 2) {
            obj = getRam(idx);
        } else if (type == 3) {
            obj = getHdd(idx);
        } else if (type == 4) {
            obj = getSsd(idx);
        } else if (type == 5) {
            obj = getPsu(idx);
        } else if (type == 6) {
            obj = getCooler(idx);
        } else if (type == 7) {
            obj = getCases(idx);
        } else if (type == 8) {
            obj = getMainBoard(idx);
        } else if (type == 9) {
            obj = getVga(idx);
        }
        return obj;
    }

    @Override
    public ProductListDto getCpuList(CpuRequest request, int page, int desc) {
        //필터링 조건
        Specification<Cpu> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(CpuSpecification.containsName(request.getName()));
        }
        if (request.getIntelCpu() != null && request.getIntelCpu().size() > 0) {
            spec = spec.and(CpuSpecification.equalIntelCpu(request.getIntelCpu()));
        }
        if (request.getAmdCpu() != null && request.getAmdCpu().size() > 0) {
            spec = spec.and(CpuSpecification.equalAmdCpu(request.getAmdCpu()));
        }
        if (request.getCore() != null && request.getCore().size() > 0) {
            spec = spec.and(CpuSpecification.equalCore(request.getCore()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(CpuSpecification.equalCorp(request.getCorp()));
        }
        if (request.getSocket() != null && request.getSocket().size() > 0) {
            spec = spec.and(CpuSpecification.equalSocket(request.getSocket()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(CpuSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Cpu> pageCpus = cpuRepository.findAll(spec, pageable);
        List<Cpu> cpus = pageCpus.getContent();
        int totalPages = pageCpus.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Cpu cpu : cpus) {
            list.add(CpuMapper.mapper.toDto(cpu));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getRamList(RamRequest request, int page, int desc) {
        Specification<Ram> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(RamSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(RamSpecification.equalCorp(request.getCorp()));
        }
        if (request.getUseDevice() != null && request.getUseDevice().size() > 0) {
            spec = spec.and(RamSpecification.equalUseDevice(request.getUseDevice()));
        }
        if (request.getType() != null && request.getType().size() > 0) {
            spec = spec.and(RamSpecification.equalType(request.getType()));
        }
        if (request.getMemoryVolume() != null && request.getMemoryVolume().size() > 0) {
            spec = spec.and(RamSpecification.equalMemoryVolume(request.getMemoryVolume()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(RamSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Ram> pageRams = ramRepository.findAll(spec, pageable);
        List<Ram> rams = pageRams.getContent();
        int totalPages = pageRams.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Ram ram : rams) {
            list.add(RamMapper.mapper.toDto(ram));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getVgaList(VgaRequest request, int page, int desc) {
        Specification<Vga> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(VgaSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(VgaSpecification.equalCorp(request.getCorp()));
        }
        if (request.getChipsetCorp() != null && request.getChipsetCorp().size() > 0) {
            spec = spec.and(VgaSpecification.equalChipsetCorp(request.getChipsetCorp()));
        }
        if (request.getNvidia() != null && request.getNvidia().size() > 0) {
            spec = spec.and(VgaSpecification.equalNvidia(request.getNvidia()));
        }
        if (request.getAmd() != null && request.getAmd().size() > 0) {
            spec = spec.and(VgaSpecification.equalAmd(request.getAmd()));
        }
        if (request.getMemoryVolume() != null && request.getMemoryVolume().size() > 0) {
            spec = spec.and(VgaSpecification.equalMemoryVolume(request.getMemoryVolume()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(VgaSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Vga> pageVgas = vgaRepository.findAll(spec, pageable);
        List<Vga> vgas = pageVgas.getContent();
        int totalPages = pageVgas.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Vga vga : vgas) {
            list.add(VgaMapper.mapper.toDto(vga));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getPsuList(PsuRequest request, int page, int desc) {
        Specification<Psu> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(PsuSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(PsuSpecification.equalCorp(request.getCorp()));
        }
        if (request.getType() != null && request.getType().size() > 0) {
            spec = spec.and(PsuSpecification.equalType(request.getType()));
        }
        if (request.getRatedPower() != null && request.getRatedPower().size() > 0) {
            spec = spec.and(PsuSpecification.equalRatedPower(request.getRatedPower()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(PsuSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Psu> pagePsus = psuRepository.findAll(spec, pageable);
        List<Psu> psus = pagePsus.getContent();
        int totalPages = pagePsus.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Psu psu : psus) {
            list.add(PsuMapper.mapper.toDto(psu));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getMainBoardList(MainBoardRequest request, int page, int desc) {
        Specification<MainBoard> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(MainBoardSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(MainBoardSpecification.equalCorp(request.getCorp()));
        }
        if (request.getCpuSocket() != null && request.getCpuSocket().size() > 0) {
            spec = spec.and(MainBoardSpecification.equalCpuSocket(request.getCpuSocket()));
        }
        if (request.getType() != null && request.getType().size() > 0) {
            spec = spec.and(MainBoardSpecification.equalType(request.getType()));
        }
        if (request.getDetailChipset() != null && request.getDetailChipset().size() > 0) {
            spec = spec.and(MainBoardSpecification.equalDetailChipset(request.getDetailChipset()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(MainBoardSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<MainBoard> pageMainBoards = mainBoardRepository.findAll(spec, pageable);
        List<MainBoard> mainBoards = pageMainBoards.getContent();
        int totalPages = pageMainBoards.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (MainBoard mainBoard : mainBoards) {
            list.add(MainBoardMapper.mapper.toDto(mainBoard));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getCoolerList(CoolerRequest request, int page, int desc) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cooler> criteriaQuery = cb.createQuery(Cooler.class);
        Root<Cooler> itemRoot = (Root<Cooler>) criteriaQuery.from(Cooler.class).alias("generatedAlias0");
        Predicate p = null;

        Specification<Cooler> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(CoolerSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(CoolerSpecification.equalCorp(request.getCorp()));
        }
        if (request.getCoolingSystem() != null && request.getCoolingSystem().size() > 0) {
            spec = spec.and(CoolerSpecification.equalCoolingSystem(request.getCoolingSystem()));
        }
        if (request.getCoolerHeight() != null && request.getCoolerHeight().size() > 0) {
            spec = spec.and(CoolerSpecification.equalCoolerHeight(request.getCoolerHeight()));
        }
        if (request.getType() != null && request.getType().size() > 0) {
            spec = spec.and(CoolerSpecification.equalType(request.getType()));
        }
        if (request.isLga3647()) {
            if (p == null)
                p = CoolerSpecification.equalLga3647(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga3647(cb, itemRoot, true));
        }
        if (request.isLga2066()) {
            if (p == null)
                p = CoolerSpecification.equalLga2066(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga2066(cb, itemRoot, true));
        }
        if (request.isLga2011V3()) {
            if (p == null)
                p = CoolerSpecification.equalLga2011V3(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga2011V3(cb, itemRoot, true));
        }
        if (request.isLga2011()) {
            if (p == null)
                p = CoolerSpecification.equalLga2011(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga2011(cb, itemRoot, true));
        }
        if (request.isLga1700()) {
            if (p == null)
                p = CoolerSpecification.equalLga1700(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga1700(cb, itemRoot, true));
        }
        if (request.isLga1366()) {
            if (p == null)
                p = CoolerSpecification.equalLga1366(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga1366(cb, itemRoot, true));
        }
        if (request.isLga1200()) {
            if (p == null)
                p = CoolerSpecification.equalLga1200(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga1200(cb, itemRoot, true));
        }
        if (request.isLga115x()) {
            if (p == null)
                p = CoolerSpecification.equalLga115x(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga115x(cb, itemRoot, true));
        }
        if (request.isLga775()) {
            if (p == null)
                p = CoolerSpecification.equalLga775(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga775(cb, itemRoot, true));
        }
        if (request.isLga771()) {
            if (p == null)
                p = CoolerSpecification.equalLga771(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga771(cb, itemRoot, true));
        }
        if (request.isLga4677()) {
            if (p == null)
                p = CoolerSpecification.equalLga4677(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga4677(cb, itemRoot, true));
        }
        if (request.isLga4189()) {
            if (p == null)
                p = CoolerSpecification.equalLga4189(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalLga4189(cb, itemRoot, true));
        }
        if (request.isSocket478()) {
            if (p == null)
                p = CoolerSpecification.equalSocket478(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocket478(cb, itemRoot, true));
        }
        if (request.isSocket370()) {
            if (p == null)
                p = CoolerSpecification.equalSocket370(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocket370(cb, itemRoot, true));
        }
        if (request.isTr4()) {
            if (p == null)
                p = CoolerSpecification.equalTr4(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalTr4(cb, itemRoot, true));
        }
        if (request.isAm5()) {
            if (p == null)
                p = CoolerSpecification.equalAm5(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalAm5(cb, itemRoot, true));
        }
        if (request.isAm4()) {
            if (p == null)
                p = CoolerSpecification.equalAm4(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalAm4(cb, itemRoot, true));
        }
        if (request.isAm3()) {
            if (p == null)
                p = CoolerSpecification.equalAm3(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalAm3(cb, itemRoot, true));
        }
        if (request.isAm1()) {
            if (p == null)
                p = CoolerSpecification.equalAm1(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalAm1(cb, itemRoot, true));
        }
        if (request.isSp3()) {
            if (p == null)
                p = CoolerSpecification.equalSp3(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSp3(cb, itemRoot, true));
        }
        if (request.isStrx4()) {
            if (p == null)
                p = CoolerSpecification.equalStrx4(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalStrx4(cb, itemRoot, true));
        }
        if (request.isSocket939()) {
            if (p == null)
                p = CoolerSpecification.equalSocket939(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocket939(cb, itemRoot, true));
        }
        if (request.isSocket754()) {
            if (p == null)
                p = CoolerSpecification.equalSocket754(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocket754(cb, itemRoot, true));
        }
        if (request.isSocket940()) {
            if (p == null)
                p = CoolerSpecification.equalSocket940(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocket940(cb, itemRoot, true));
        }
        if (request.isSwrx8()) {
            if (p == null)
                p = CoolerSpecification.equalSwrx8(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSwrx8(cb, itemRoot, true));
        }
        if (request.isSocketa()) {
            if (p == null)
                p = CoolerSpecification.equalSocketa(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocketa(cb, itemRoot, true));
        }
        if (request.isSocketf()) {
            if (p == null)
                p = CoolerSpecification.equalSocketf(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalSocketf(cb, itemRoot, true));
        }
        if (request.isFmxAmx()) {
            if (p == null)
                p = CoolerSpecification.equalFmxAmx(cb, itemRoot, true);
            else
                p = cb.or(p, CoolerSpecification.equalFmxAmx(cb, itemRoot, true));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(CoolerSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }
        if (p != null)
            spec = spec.and(CoolerSpecification.result(p));

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Cooler> pageCoolers = coolerRepository.findAll(spec, pageable);
        List<Cooler> coolers = pageCoolers.getContent();
        int totalPages = pageCoolers.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Cooler cooler : coolers) {
            list.add(CoolerMapper.mapper.toDto(cooler));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getCasesList(CasesRequest request, int page, int desc) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cases> criteriaQuery = cb.createQuery(Cases.class);
        Root<Cases> itemRoot = (Root<Cases>) criteriaQuery.from(Cases.class).alias("generatedAlias0");
        Predicate p = null;

        Specification<Cases> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(CasesSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(CasesSpecification.equalCorp(request.getCorp()));
        }
        if (request.getClassType() != null && request.getClassType().size() > 0) {
            spec = spec.and(CasesSpecification.equalClassType(request.getClassType()));
        }
        if (request.getSize() != null && request.getSize().size() > 0) {
            spec = spec.and(CasesSpecification.equalSize(request.getSize()));
        }
        if (request.getPowerSize() != null && request.getPowerSize().size() > 0) {
            spec = spec.and(CasesSpecification.equalPowerSize(request.getPowerSize()));
        }
        if (request.isExtendedAtx()) {
            if (p == null)
                p = CasesSpecification.equalExtendedAtx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalExtendedAtx(cb, itemRoot, true));
        }
        if (request.isStandardAtx()) {
            if (p == null)
                p = CasesSpecification.equalStandardAtx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalStandardAtx(cb, itemRoot, true));
        }
        if (request.isMicroAtx()) {
            if (p == null)
                p = CasesSpecification.equalMicroAtx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalMicroAtx(cb, itemRoot, true));
        }
        if (request.isFlexAtx()) {
            if (p == null)
                p = CasesSpecification.equalFlexAtx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalFlexAtx(cb, itemRoot, true));
        }
        if (request.isStandardItx()) {
            if (p == null)
                p = CasesSpecification.equalStandardItx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalStandardItx(cb, itemRoot, true));
        }
        if (request.isMiniItx()) {
            if (p == null)
                p = CasesSpecification.equalMiniItx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalMiniItx(cb, itemRoot, true));
        }
        if (request.isSsiCeb()) {
            if (p == null)
                p = CasesSpecification.equalSsiCeb(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalSsiCeb(cb, itemRoot, true));
        }
        if (request.isSsiEeb()) {
            if (p == null)
                p = CasesSpecification.equalSsiEeb(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalSsiEeb(cb, itemRoot, true));
        }
        if (request.isMiniDtx()) {
            if (p == null)
                p = CasesSpecification.equalMiniDtx(cb, itemRoot, true);
            else
                p = cb.or(p, CasesSpecification.equalMiniDtx(cb, itemRoot, true));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(CasesSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }
        if (p != null)
            spec = spec.and(CasesSpecification.result(p));

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Cases> pageCasess = casesRepository.findAll(spec, pageable);
        List<Cases> casess = pageCasess.getContent();
        int totalPages = pageCasess.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Cases cases : casess) {
            list.add(CasesMapper.mapper.toDto(cases));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getHddList(HddRequest request, int page, int desc) {
        Specification<Hdd> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(HddSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(HddSpecification.equalCorp(request.getCorp()));
        }
        if (request.getDiskVolume() != null && request.getDiskVolume().size() > 0) {
            spec = spec.and(HddSpecification.equalDiskVolume(request.getDiskVolume()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(HddSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Hdd> pageHdds = hddRepository.findAll(spec, pageable);
        List<Hdd> hdds = pageHdds.getContent();
        int totalPages = pageHdds.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Hdd hdd : hdds) {
            list.add(HddMapper.mapper.toDto(hdd));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public ProductListDto getSsdList(SsdRequest request, int page, int desc) {
        Specification<Ssd> spec = (root, query, criteriaBuilder) -> null;
        if (request.getName() != null) {
            spec = spec.and(SsdSpecification.containsName(request.getName()));
        }
        if (request.getCorp() != null && request.getCorp().size() > 0) {
            spec = spec.and(SsdSpecification.equalCorp(request.getCorp()));
        }
        if (request.getFormFactor() != null && request.getFormFactor().size() > 0) {
            spec = spec.and(SsdSpecification.equalFormFactor(request.getFormFactor()));
        }
        if (request.getVolume() != null && request.getVolume().size() > 0) {
            spec = spec.and(SsdSpecification.equalVolume(request.getVolume()));
        }
        if (request.getMemoryType() != null && request.getMemoryType().size() > 0) {
            spec = spec.and(SsdSpecification.equalMemoryType(request.getMemoryType()));
        }
        if (request.getPrice() != null && request.getPrice().size() > 1) {
            spec = spec.and(SsdSpecification.betweenPrice(request.getPrice().get(0), request.getPrice().get(1)));
        }

        //페이지네이션
        Pageable pageable = getPageable(page, desc);
        Page<Ssd> pageSsds = ssdRepository.findAll(spec, pageable);
        List<Ssd> ssds = pageSsds.getContent();
        int totalPages = pageSsds.getTotalPages();

        List<Object> list = new ArrayList<>();
        for (Ssd ssd : ssds) {
            list.add(SsdMapper.mapper.toDto(ssd));
        }

        return ProductListDto.builder()
                .productDtoList(list)
                .totalPage(totalPages)
                .build();
    }

    @Override
    public FilterCpuResponse getCpufilter() {
        FilterCpuResponse result = new FilterCpuResponse();
        return result.builder()
                .corp(cpuRepository.findDistinctCorp())
                .core(cpuRepository.findDistinctCore())
                .amdCpu(cpuRepository.findDistinctAmdCpu())
                .socket(cpuRepository.findDistinctSocket())
                .intelCpu(cpuRepository.findDistinctIntelCpu())
                .build();
    }

    @Override
    public FilterVgaResponse getVgafilter() {
        FilterVgaResponse result = new FilterVgaResponse();
        return result.builder()
                .corp(vgaRepository.findDistinctCorp())
                .chipsetCorp(vgaRepository.findDistinctChipsetCorp())
                .nvidia(vgaRepository.findDistinctNvidia())
                .amd(vgaRepository.findDistinctAmd())
                .memoryVolume(vgaRepository.findDistinctMemoryVolume())
                .build();
    }

    @Override
    public FilterRamResponse getRamfilter() {
        FilterRamResponse result = new FilterRamResponse();
        return result.builder()
                .corp(ramRepository.findDistinctCorp())
                .useDevice(ramRepository.findDistinctUseDevice())
                .type(ramRepository.findDistinctType())
                .memoryVolume(ramRepository.findDistinctMemoryVolume())
                .build();
    }

    @Override
    public FilterHddResponse getHddfilter() {
        FilterHddResponse result = new FilterHddResponse();
        return result.builder()
                .corp(hddRepository.findDistinctCorp())
                .diskVolume(hddRepository.findDistinctDiskVolume())
                .build();
    }

    @Override
    public FilterSsdResponse getSsdfilter() {
        FilterSsdResponse result = new FilterSsdResponse();
        return result.builder()
                .corp(ssdRepository.findDistinctCorp())
                .formFactor(ssdRepository.findDistinctFormFactor())
                .volume(ssdRepository.findDistinctVolume())
                .memoryType(ssdRepository.findDistinctMemoryType())
                .build();
    }

    @Override
    public FilterPsuResponse getPsufilter() {
        FilterPsuResponse result = new FilterPsuResponse();
        return result.builder()
                .corp(psuRepository.findDistinctCorp())
                .type(psuRepository.findDistinctType())
                .ratedPower(psuRepository.findDistinctRatedPower())
                .build();
    }

    @Override
    public FilterCasesResponse getCasesfilter() {
        FilterCasesResponse result = new FilterCasesResponse();
        return result.builder()
                .corp(casesRepository.findDistinctCorp())
                .classType(casesRepository.findDistinctClassType())
                .size(casesRepository.findDistinctSize())
                .powerSize(casesRepository.findDistinctPowerSize())
                .build();
    }

    @Override
    public FilterCoolerResponse getCoolerfilter() {
        FilterCoolerResponse result = new FilterCoolerResponse();
        return result.builder()
                .corp(coolerRepository.findDistinctCorp())
                .type(coolerRepository.findDistinctType())
                .coolingSystem(coolerRepository.findDistinctCoolingSystem())
                .coolerHeight(coolerRepository.findDistinctCoolerHeight())
                .build();
    }

    @Override
    public FilterMainBoardResponse getMainBoardfilter() {
        FilterMainBoardResponse result = new FilterMainBoardResponse();
        return result.builder()
                .corp(mainBoardRepository.findDistinctCorp())
                .cpuSocket(mainBoardRepository.findDistinctCpuSocket())
                .type(mainBoardRepository.findDistinctType())
                .detailChipset(mainBoardRepository.findDistinctDetailChipset())
                .build();
    }

    @Override
    //category: "cpu", "ram", "vga", "mainboard", "psu", "hdd", "ssd", "cases", "cooler"
    public PriceDto getProductPriceDto(String category, long productId) {
        Criteria defaultCriteria = Criteria.where("_id").is(Long.toString(productId));
        Query defaultQuery = Query.query(defaultCriteria);
        PriceDto result = mongoTemplate.findOne(
                defaultQuery,
                PriceDto.class,
                category
        );

        return result;
    }

    @Override
    public List<Integer> getProductPriceOne(String category, long productId) {
        PriceDto data = getProductPriceDto(category, productId);
        if (data == null) return null;
        List<List<Integer>> dateData = data.getDate();
        int dateSize = dateData.size();
        if (dateSize == 0) return null;
        List<Integer> dateAndPrice = dateData.get(dateSize - 1);

        return dateAndPrice;
    }

    @Override
    public Pageable getPageable(int page, int desc) {
        int pageSize = constProperties.getProductListSize();
        if (desc == 1) {
            return PageRequest.of(page, pageSize, Sort.by("price").ascending());
        } else if (desc == 2)
            return PageRequest.of(page, pageSize, Sort.by("price").descending());
        else
            return PageRequest.of(page, pageSize, Sort.by("registeredAt").descending());
    }

    @Override
    public PriceDto getProductPeriodPriceDto(long productId, int period) {
        if (period == 0)
            return null;
        ProductDto productDto = ProductMapper.mapper.toDto((productRepository.findById(productId)
                .orElseThrow(() -> new ProductApiException(ProductErrorCode.PRODUCT_NOT_FOUND))));
        int categoryNum = productDto.getCategory();
        ProductCategory[] categoryArr = ProductCategory.values();
        String categoryStr = categoryArr[categoryNum - 1].getCategory();
        PriceDto priceDto = getProductPriceDto(categoryStr, productId);
        //시세가 없는 경우
        if (priceDto == null)
            return null;

        List<List<Integer>> dates = priceDto.getDate();
        //시세가 없는 경우2
        if (dates == null)
            return null;

        //현재 시간 기준. 언제부터 가져올 지 정하기 ex.20221004
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        if (month <= period) {
            year--;
            month += 12;
        }
        int standard = year * 10000 + (month - period) * 100 + day;
        int startIdx = 0;
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            if (dates.get(i).get(0) >= standard) {
                startIdx = i;
                break;
            }
        }
        int subSize = size - startIdx;
        if (subSize == 0)
            return null;
        List<List<Integer>> result = new ArrayList<>();
        if (subSize <= 10)
            result = dates.subList(startIdx, size);
        else {
            int num = subSize / 10;
            for (int i = startIdx; i < size; i += num) {
                result.add(dates.get(i));
            }
        }
        return PriceDto.builder()
                .id(priceDto.getId())
                .name(priceDto.getName())
                .date(result)
                .build();
    }
}