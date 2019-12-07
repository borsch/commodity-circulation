package ua.net.kurpiak.commoditycirculation.persistence.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import lombok.Setter;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;

@Setter
public class ProductCriteria extends Criteria<ProductEntity> {

    private String query;

    public ProductCriteria() {
        super(ProductEntity.class);
    }

    public ProductCriteria(String restrict) throws WrongRestrictionException {
        this();

        ProductCriteria parsed = parse(restrict, ProductCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
        }
    }

    @Override
    public List<Predicate> query(Root<ProductEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(query)) {
            String queryLike = '%' + query + '%';

            predicates.add(cb.or(cb.like(root.get("code"), queryLike), cb.like(root.get("name"), queryLike)));
        }

        return predicates;
    }
}
