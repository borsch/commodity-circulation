package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<>();
    }
}
