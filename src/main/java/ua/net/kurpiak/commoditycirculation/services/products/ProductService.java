package ua.net.kurpiak.commoditycirculation.services.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.net.kurpiak.commoditycirculation.convertors.ProductConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.mapper.ProductMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.ProductCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.ProductView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Service
@Transactional(rollbackFor = BaseException.class)
public class ProductService extends BaseService<ProductEntity, ProductView, Integer> {

    public ProductService(final ProductRepository repository, final ProductConverter converter, final ProductMapper productMapper,
        final CriteriaRepository criteriaRepository, final ProductValidator validationService) {
        super(repository, converter, productMapper, criteriaRepository, validationService);
    }

    @Override
    public Criteria<ProductEntity> parse(String restrict) throws WrongRestrictionException {
        return new ProductCriteria(restrict);
    }
}
