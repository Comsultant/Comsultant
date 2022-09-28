package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Ram;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class RamSpecification {
    public static Specification<Ram> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Ram> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Ram> equalUseDevice(List<String> useDevice) {
        return (root, query, criteriaBuilder) -> root.get("useDevice").in(useDevice);
    }
    public static Specification<Ram> equalType(List<String> type) {
        return (root, query, criteriaBuilder) -> root.get("type").in(type);
    }
    public static Specification<Ram> equalMemoryVolume(List<Double> memoryVolume) {
        return (root, query, criteriaBuilder) -> root.get("memoryVolume").in(memoryVolume);
    }
    public static Specification<Ram> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelRam"), minPrice, maxPrice);
    }
}
