package com.comsultant.domain.product.service.specification;

import com.comsultant.domain.product.entity.Cases;
import com.comsultant.domain.product.entity.Cooler;
import org.springframework.data.jpa.domain.Specification;

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
    public static Specification<Cooler> equalLga3647(boolean lga3647) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga3647"), lga3647);
    }
    public static Specification<Cooler> equalLga2066(boolean lga2066) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga2066"), lga2066);
    }
    public static Specification<Cooler> equalLga2011V3(boolean lga2011V3) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga2011V3"), lga2011V3);
    }
    public static Specification<Cooler> equalLga2011(boolean lga2011) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga2011"), lga2011);
    }
    public static Specification<Cooler> equalLga1700(boolean lga1700) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga1700"), lga1700);
    }
    public static Specification<Cooler> equalLga1366(boolean lga1366) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga1366"), lga1366);
    }
    public static Specification<Cooler> equalLga1200(boolean lga1200) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga1200"), lga1200);
    }
    public static Specification<Cooler> equalLga115x(boolean lga115x) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga115x"), lga115x);
    }
    public static Specification<Cooler> equalLga775(boolean lga775) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga775"), lga775);
    }
    public static Specification<Cooler> equalLga771(boolean lga771) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga771"), lga771);
    }
    public static Specification<Cooler> equalLga4677(boolean lga4677) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga4677"), lga4677);
    }
    public static Specification<Cooler> equalLga4189(boolean lga4189) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lga4189"), lga4189);
    }
    public static Specification<Cooler> equalSocket478(boolean socket478) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socket478"), socket478);
    }
    public static Specification<Cooler> equalSocket370(boolean socket370) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socket370"), socket370);
    }
    public static Specification<Cooler> equalTr4(boolean tr4) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tr4"), tr4);
    }
    public static Specification<Cooler> equalAm5(boolean am5) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("am5"), am5);
    }
    public static Specification<Cooler> equalAm4(boolean am4) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("am4"), am4);
    }
    public static Specification<Cooler> equalAm3(boolean am3) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("am3"), am3);
    }
    public static Specification<Cooler> equalAm1(boolean am1) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("am1"), am1);
    }
    public static Specification<Cooler> equalSp3(boolean sp3) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sp3"), sp3);
    }
    public static Specification<Cooler> equalStrx4(boolean strx4) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("strx4"), strx4);
    }
    public static Specification<Cooler> equalSocket939(boolean socket939) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socket939"), socket939);
    }
    public static Specification<Cooler> equalSocket754(boolean socket754) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socket754"), socket754);
    }
    public static Specification<Cooler> equalSocket940(boolean socket940) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socket940"), socket940);
    }
    public static Specification<Cooler> equalSwrx8(boolean swrx8) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("swrx8"), swrx8);
    }
    public static Specification<Cooler> equalSocketa(boolean socketa) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socketa"), socketa);
    }
    public static Specification<Cooler> equalSocketf(boolean socketf) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("socketf"), socketf);
    }
    public static Specification<Cooler> equalFmxAmx(boolean fmxAmx) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fmxAmx"), fmxAmx);
    }
    public static Specification<Cooler> price(int minPrice, int maxPrice) { //price 추후 수정
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("intelCooler"), minPrice, maxPrice);
    }
}
