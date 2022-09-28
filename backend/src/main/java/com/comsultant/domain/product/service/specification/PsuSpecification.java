package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Psu;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PsuSpecification {
    public static Specification<Psu> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Psu> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Psu> equalType(List<String> type) {
        return (root, query, criteriaBuilder) -> root.get("type").in(type);
    }
    public static Specification<Psu> equalRatedPower(List<Integer> ratedPower) {
        return (root, query, criteriaBuilder) -> root.get("ratedPower").in(ratedPower);
    }
    public static Specification<Psu> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelPsu"), minPrice, maxPrice);
    }
}
