package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class IncomeOrderCriteria extends Criteria<IncomeOrderEntity> {

    public IncomeOrderCriteria() {
        super(IncomeOrderEntity.class);
    }

    public IncomeOrderCriteria(String restrict) {
        this();

        parse(restrict, IncomeOrderCriteria.class);
    }

    @Override
    public List<Predicate> query(Root<IncomeOrderEntity> root, CriteriaBuilder cb) {
        return new ArrayList<>();
    }
}
