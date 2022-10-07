package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Ssd;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SsdSpecification {
    public static Specification<Ssd> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Ssd> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Ssd> equalFormFactor(List<String> formFactor) {
        return (root, query, criteriaBuilder) -> root.get("formFactor").in(formFactor);
    }
    public static Specification<Ssd> equalVolume(List<Integer> volume) {
        return (root, query, criteriaBuilder) -> root.get("volume").in(volume);
    }
    public static Specification<Ssd> equalMemoryType(List<String> memoryType) {
        return (root, query, criteriaBuilder) -> root.get("memoryType").in(memoryType);
    }
    public static Specification<Ssd> betweenPrice(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}
