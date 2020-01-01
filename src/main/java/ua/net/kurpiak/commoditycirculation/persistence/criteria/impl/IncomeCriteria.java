package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.Getter;
import lombok.Setter;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity_;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity_;

@Getter
@Setter
public class IncomeCriteria extends Criteria<IncomeEntity> {

    private Integer productId;
    private Boolean hasMore;

    public IncomeCriteria() {
        super(IncomeEntity.class);
    }

    public IncomeCriteria(String restrict) throws WrongRestrictionException {
        this();

        IncomeCriteria parsed = parse(restrict, IncomeCriteria.class);
        if (parsed != null) {
            this.productId = parsed.productId;
            this.hasMore = parsed.hasMore;
        }
    }

    @Override
    public List<Predicate> query(Root<IncomeEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (productId != null) {
            Join<IncomeEntity, ProductEntity> productJoin = root.join(IncomeEntity_.product);
            predicates.add(cb.equal(productJoin.get(ProductEntity_.id), productId));
        }

        if (hasMore != null) {
            predicates.add(cb.equal(root.get("hasMore"), hasMore));
        }

        return predicates;
    }
}
