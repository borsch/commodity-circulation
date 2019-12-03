package ua.net.kurpiak.commoditycirculation.services.outcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeService;

@Slf4j
@Component
public class OutcomeService extends BaseService<OutcomeEntity, OutcomeView, Integer> {

    @Autowired
    private IncomeService incomeService;

    @Override
    public Criteria<OutcomeEntity> parse(final String restrict) throws WrongRestrictionException {
        return new OutcomeCriteria(restrict);
    }

    @Override
    public OutcomeEntity newInstance() {
        return new OutcomeEntity();
    }

    @Override
    public void postCreate(final OutcomeEntity entity) {
        if (entity.getProduct() != null) {
            incomeService.handleWithdraw(entity.getProduct(), entity.getAmount());
        }
    }

    public static void initMapperFactory(MapperFactory mapperFactory, ProductRepository productRepository,
        OutcomeOrderRepository outcomeOrderRepository) {
        mapperFactory.classMap(OutcomeEntity.class, OutcomeView.class)
            .field("amount", "amount").mapNulls(false).mapNullsInReverse(false)
            .field("salePrice", "salePrice").mapNulls(false).mapNullsInReverse(false)
            .customize(new CustomMapper<OutcomeEntity, OutcomeView>() {
                @Override
                public void mapBtoA(OutcomeView view, OutcomeEntity entity, MappingContext context) {
                    if (view.getProductId() != null) {
                        entity.setProduct(productRepository.findById(view.getProductId()).orElse(null));
                    }

                    if (view.getOutcomeOrderId() != null) {
                        entity.setOrder(outcomeOrderRepository.findById(view.getOutcomeOrderId()).orElse(null));
                    }
                }
            }).register();
    }
}
