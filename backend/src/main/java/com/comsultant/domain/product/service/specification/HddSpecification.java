package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Hdd;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class HddSpecification {
    public static Specification<Hdd> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Hdd> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Hdd> equalDiskVolume(List<Integer> diskVolume) {
        return (root, query, criteriaBuilder) -> root.get("diskVolume").in(diskVolume);
    }
    public static Specification<Hdd> betweenPrice(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}
