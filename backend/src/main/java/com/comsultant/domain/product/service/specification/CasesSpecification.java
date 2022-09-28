package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Cases;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CasesSpecification {
    public static Specification<Cases> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Cases> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Cases> equalClassType(List<String> classType) {
        return (root, query, criteriaBuilder) -> root.get("classType").in(classType);
    }
    public static Specification<Cases> equalSize(List<String> size) {
        return (root, query, criteriaBuilder) -> root.get("size").in(size);
    }
    public static Specification<Cases> equalPowerSize(List<String> powerSize) {
        return (root, query, criteriaBuilder) -> root.get("powerSize").in(powerSize);
    }
    public static Specification<Cases> equalExtendedAtx(boolean extendedAtx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("extendedAtx"), extendedAtx);
    }
    public static Specification<Cases> equalStandardAtx(boolean standardAtx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("standardAtx"), standardAtx);
    }
    public static Specification<Cases> equalMicroAtx(boolean microAtx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("microAtx"), microAtx);
    }
    public static Specification<Cases> equalFlexAtx(boolean flexAtx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("flexAtx"), flexAtx);
    }
    public static Specification<Cases> equalStandardItx(boolean standardItx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("standardItx"), standardItx);
    }
    public static Specification<Cases> equalMiniItx(boolean miniItx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("miniItx"), miniItx);
    }
    public static Specification<Cases> equalSsiCeb(boolean ssiCeb) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ssiCeb"), ssiCeb);
    }
    public static Specification<Cases> equalSsiEeb(boolean ssiEeb) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ssiEeb"), ssiEeb);
    }
    public static Specification<Cases> equalMiniDtx(boolean miniDtx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("flexAtx"), miniDtx);
    }
    public static Specification<Cases> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelCases"), minPrice, maxPrice);
    }
}
