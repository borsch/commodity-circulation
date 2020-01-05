package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity_;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity_;

public class OutcomeOrderCriteria extends Criteria<OutcomeOrderEntity> {

    public OutcomeOrderCriteria() {
        super(OutcomeOrderEntity.class);
    }

    public OutcomeOrderCriteria(String restrict) {
        this();

        parse(restrict, OutcomeOrderCriteria.class);
    }

    @Override
    public List<Predicate> query(Root<OutcomeOrderEntity> root, CriteriaBuilder cb) {
        root.fetch(OutcomeOrderEntity_.outcomes)
            .fetch(OutcomeEntity_.product);
        return new ArrayList<>();
    }
}
