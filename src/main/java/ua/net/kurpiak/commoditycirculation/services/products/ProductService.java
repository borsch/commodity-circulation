package ua.net.kurpiak.commoditycirculation.services.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ua.net.kurpiak.commoditycirculation.convertors.ProductConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
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

    public ProductService(
        final ProductRepository repository,
        final ProductConverter converter, final MapperFacade mapperFacade,
        final CriteriaRepository criteriaRepository,
        final ProductValidator validationService) {
        super(repository, converter, mapperFacade, criteriaRepository, validationService);
    }

    @Override
    public Criteria<ProductEntity> parse(String restrict) throws WrongRestrictionException {
        return new ProductCriteria(restrict);
    }

    @Override
    public ProductEntity newInstance() {
        return new ProductEntity();
    }

    public static void initMapperFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(ProductEntity.class, ProductView.class)
                     .field("code", "code").mapNulls(false).mapNullsInReverse(false)
                     .field("name", "name").mapNulls(false).mapNullsInReverse(false)
                     .field("unit", "unit").mapNulls(false).mapNullsInReverse(false)
                     .field("residual", "residual").mapNulls(false).mapNullsInReverse(false)
                     .field("defaultPurchasePrice", "defaultPurchasePrice").mapNulls(false).mapNullsInReverse(false)
                     .field("defaultPurchasePriceUsd", "defaultPurchasePriceUsd").mapNulls(false).mapNullsInReverse(false)
                     .field("defaultSalePrice", "defaultSalePrice").mapNulls(false).mapNullsInReverse(false)
                     .register();
    }
}
