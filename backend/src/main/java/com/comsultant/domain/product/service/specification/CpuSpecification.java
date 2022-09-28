package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Cpu;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CpuSpecification {
    public static Specification<Cpu> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Cpu> equalIntelCpu(List<String> intelCpu) {
        return (root, query, criteriaBuilder) -> root.get("intelCpu").in(intelCpu);
    }
    public static Specification<Cpu> equalAmdCpu(List<String> amdCpu) {
        return (root, query, criteriaBuilder) -> root.get("amdCpu").in(amdCpu);
    }
    public static Specification<Cpu> equalSocket(List<String> socket) {
        return (root, query, criteriaBuilder) -> root.get("socket").in(socket);
    }
    public static Specification<Cpu> equalCore(List<String> core) {
        return (root, query, criteriaBuilder) -> root.get("core").in(core);
    }
    public static Specification<Cpu> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Cpu> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelCpu"), minPrice, maxPrice);
    }
}
