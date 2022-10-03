package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Cooler;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CoolerSpecification {
    public static Specification<Cooler> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Cooler> equalCorp(List<String> corp) {
        return (root, query, criteriaBuilder) -> root.get("corp").in(corp);
    }
    public static Specification<Cooler> equalType(List<String> type) {
        return (root, query, criteriaBuilder) -> root.get("type").in(type);
    }
    public static Specification<Cooler> equalCoolingSystem(List<String> coolingSystem) {
        return (root, query, criteriaBuilder) -> root.get("coolingSystem").in(coolingSystem);
    }
    public static Specification<Cooler> equalCoolerHeight(List<Double> coolerHeight) {
        return (root, query, criteriaBuilder) -> root.get("coolerHeight").in(coolerHeight);
    }
    public static Specification<Cooler> equalMemoryVolume(List<Double> memoryVolume) {
        return (root, query, criteriaBuilder) -> root.get("memoryVolume").in(memoryVolume);
    }
    public static Predicate equalLga3647(CriteriaBuilder cb, Root root, boolean lga3647) {
        return cb.equal(root.get("lga3647"), lga3647);
    }
    public static Predicate equalLga2066(CriteriaBuilder cb, Root root, boolean lga2066) {
        return cb.equal(root.get("lga2066"), lga2066);
    }
    public static Predicate equalLga2011V3(CriteriaBuilder cb, Root root, boolean lga2011V3) {
        return cb.equal(root.get("lga2011V3"), lga2011V3);
    }
    public static Predicate equalLga2011(CriteriaBuilder cb, Root root, boolean lga2011) {
        return cb.equal(root.get("lga2011"), lga2011);
    }
    public static Predicate equalLga1700(CriteriaBuilder cb, Root root, boolean lga1700) {
        return cb.equal(root.get("lga1700"), lga1700);
    }
    public static Predicate equalLga1366(CriteriaBuilder cb, Root root, boolean lga1366) {
        return cb.equal(root.get("lga1366"), lga1366);
    }
    public static Predicate equalLga1200(CriteriaBuilder cb, Root root, boolean lga1200) {
        return cb.equal(root.get("lga1200"), lga1200);
    }
    public static Predicate equalLga115x(CriteriaBuilder cb, Root root, boolean lga115x) {
        return cb.equal(root.get("lga115x"), lga115x);
    }
    public static Predicate equalLga775(CriteriaBuilder cb, Root root, boolean lga775) {
        return cb.equal(root.get("lga775"), lga775);
    }
    public static Predicate equalLga771(CriteriaBuilder cb, Root root, boolean lga771) {
        return cb.equal(root.get("lga771"), lga771);
    }
    public static Predicate equalLga4677(CriteriaBuilder cb, Root root, boolean lga4677) {
        return cb.equal(root.get("lga4677"), lga4677);
    }
    public static Predicate equalLga4189(CriteriaBuilder cb, Root root, boolean lga4189) {
        return cb.equal(root.get("lga4189"), lga4189);
    }
    public static Predicate equalSocket478(CriteriaBuilder cb, Root root, boolean socket478) {
        return cb.equal(root.get("socket478"), socket478);
    }
    public static Predicate equalSocket370(CriteriaBuilder cb, Root root, boolean socket370) {
        return cb.equal(root.get("socket370"), socket370);
    }
    public static Predicate equalTr4(CriteriaBuilder cb, Root root, boolean tr4) {
        return cb.equal(root.get("tr4"), tr4);
    }
    public static Predicate equalAm5(CriteriaBuilder cb, Root root, boolean am5) {
        return cb.equal(root.get("am5"), am5);
    }
    public static Predicate equalAm4(CriteriaBuilder cb, Root root, boolean am4) {
        return cb.equal(root.get("am4"), am4);
    }
    public static Predicate equalAm3(CriteriaBuilder cb, Root root, boolean am3) {
        return cb.equal(root.get("am3"), am3);
    }
    public static Predicate equalAm1(CriteriaBuilder cb, Root root, boolean am1) {
        return cb.equal(root.get("am1"), am1);
    }
    public static Predicate equalSp3(CriteriaBuilder cb, Root root, boolean sp3) {
        return cb.equal(root.get("sp3"), sp3);
    }
    public static Predicate equalStrx4(CriteriaBuilder cb, Root root, boolean strx4) {
        return cb.equal(root.get("strx4"), strx4);
    }
    public static Predicate equalSocket939(CriteriaBuilder cb, Root root, boolean socket939) {
        return cb.equal(root.get("socket939"), socket939);
    }
    public static Predicate equalSocket754(CriteriaBuilder cb, Root root, boolean socket754) {
        return cb.equal(root.get("socket754"), socket754);
    }
    public static Predicate equalSocket940(CriteriaBuilder cb, Root root, boolean socket940) {
        return cb.equal(root.get("socket940"), socket940);
    }
    public static Predicate equalSwrx8(CriteriaBuilder cb, Root root, boolean swrx8) {
        return cb.equal(root.get("swrx8"), swrx8);
    }
    public static Predicate equalSocketa(CriteriaBuilder cb, Root root, boolean socketa) {
        return cb.equal(root.get("socketa"), socketa);
    }
    public static Predicate equalSocketf(CriteriaBuilder cb, Root root, boolean socketf) {
        return cb.equal(root.get("socketf"), socketf);
    }
    public static Predicate equalFmxAmx(CriteriaBuilder cb, Root root, boolean fmxAmx) {
        return cb.equal(root.get("fmxAmx"), fmxAmx);
    }
    public static Specification<Cooler> betweenPrice(int minPrice, int maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
    public static Specification<Cooler> result(Predicate p) {
        return (root, query, criteriaBuilder) -> p;
    }
}
