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
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity_;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity_;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity_;

@Getter
@Setter
public class OutcomeCriteria extends Criteria<OutcomeEntity> {

    private Integer productId;
    private Integer orderId;

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
            Join<OutcomeEntity, ProductEntity> productJoin = root.join(OutcomeEntity_.product);
            predicates.add(cb.equal(productJoin.get(ProductEntity_.id), productId));
        }

        if (orderId != null) {
            Join<OutcomeEntity, OutcomeOrderEntity> orderJoin = root.join(OutcomeEntity_.order);
            predicates.add(cb.equal(orderJoin.get(OutcomeOrderEntity_.id), orderId));
        }

        root.fetch(OutcomeEntity_.product);

        return predicates;
    }

}
