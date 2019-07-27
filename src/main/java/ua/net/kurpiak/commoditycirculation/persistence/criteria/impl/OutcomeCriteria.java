package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import lombok.Getter;
import lombok.Setter;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OutcomeCriteria extends Criteria<OutcomeEntity> {

    private Integer productId;

    public OutcomeCriteria() {
        super(OutcomeEntity.class);
    }

    public OutcomeCriteria(String restrict) throws WrongRestrictionException {
        this();

        OutcomeCriteria parsed = parse(restrict, OutcomeCriteria.class);
        if (parsed != null) {
            this.productId = parsed.productId;
        }
    }

    @Override
    public List<Predicate> query(Root<OutcomeEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (productId != null) {
            Join<IncomeEntity, ProductEntity> productJoin = root.join("product");
            predicates.add(cb.equal(productJoin.get("id"), productId));
        }

        return predicates;
    }

}
