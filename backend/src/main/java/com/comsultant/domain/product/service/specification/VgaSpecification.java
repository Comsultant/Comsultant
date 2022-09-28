package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Vga;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class VgaSpecification {
    public static Specification<Vga> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Vga> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Vga> equalChipsetCorp(List<String> chipsetCorp) {
        return (root, query, criteriaBuilder) -> root.get("chipsetCorp").in(chipsetCorp);
    }
    public static Specification<Vga> equalNvidia(List<String> nvidia) {
        return (root, query, criteriaBuilder) -> root.get("nvidia").in(nvidia);
    }
    public static Specification<Vga> equalAmd(List<String> amd) {
        return (root, query, criteriaBuilder) -> root.get("amd").in(amd);
    }
    public static Specification<Vga> equalMemoryVolume(List<Double> memoryVolume) {
        return (root, query, criteriaBuilder) -> root.get("memoryVolume").in(memoryVolume);
    }
    public static Specification<Vga> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelVga"), minPrice, maxPrice);
    }
}
