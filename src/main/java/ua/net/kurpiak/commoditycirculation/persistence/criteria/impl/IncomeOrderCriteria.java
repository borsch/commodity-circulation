package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity_;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity_;

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
        root.fetch(IncomeOrderEntity_.incomes)
            .fetch(IncomeEntity_.product);
        return new ArrayList<>();
    }
}
