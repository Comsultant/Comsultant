package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.MainBoard;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MainBoardSpecification {
    public static Specification<MainBoard> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<MainBoard> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<MainBoard> equalCpuSocket(List<String> cpuSocket) {
        return (root, query, criteriaBuilder) -> root.get("cpuSocket").in(cpuSocket);
    }
    public static Specification<MainBoard> equalType(List<String> type) {
        return (root, query, criteriaBuilder) -> root.get("type").in(type);
    }
    public static Specification<MainBoard> equalDetailChipset(List<String> detailChipset) {
        return (root, query, criteriaBuilder) -> root.get("detailChipset").in(detailChipset);
    }
    public static Specification<MainBoard> betweenPrice(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}
