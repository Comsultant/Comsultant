package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Cases;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public static Predicate equalExtendedAtx(CriteriaBuilder cb, Root root, boolean extendedAtx) {
        return cb.equal(root.get("extendedAtx"), extendedAtx);
    }
    public static Predicate equalStandardAtx(CriteriaBuilder cb, Root root, boolean standardAtx) {
        return cb.equal(root.get("standardAtx"), standardAtx);
    }
    public static Predicate equalMicroAtx(CriteriaBuilder cb, Root root, boolean microAtx) {
        return cb.equal(root.get("microAtx"), microAtx);
    }
    public static Predicate equalFlexAtx(CriteriaBuilder cb, Root root, boolean flexAtx) {
        return cb.equal(root.get("flexAtx"), flexAtx);
    }
    public static Predicate equalStandardItx(CriteriaBuilder cb, Root root, boolean standardItx) {
        return cb.equal(root.get("standardItx"), standardItx);
    }
    public static Predicate equalMiniItx(CriteriaBuilder cb, Root root, boolean miniItx) {
        return cb.equal(root.get("miniItx"), miniItx);
    }
    public static Predicate equalSsiCeb(CriteriaBuilder cb, Root root, boolean ssiCeb) {
        return cb.equal(root.get("ssiCeb"), ssiCeb);
    }
    public static Predicate equalSsiEeb(CriteriaBuilder cb, Root root, boolean ssiEeb) {
        return cb.equal(root.get("ssiEeb"), ssiEeb);
    }
    public static Predicate equalMiniDtx(CriteriaBuilder cb, Root root, boolean miniDtx) {
        return cb.equal(root.get("flexAtx"), miniDtx);
    }
    public static Specification<Cases> betweenPrice(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
    public static Specification<Cases> result(Predicate p) {
        return (root, query, criteriaBuilder) -> p;
    }
}
