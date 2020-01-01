package ua.net.kurpiak.commoditycirculation.services.outcome;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.net.kurpiak.commoditycirculation.convertors.OutcomeConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.mapper.OutcomeMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeService;
import ua.net.kurpiak.commoditycirculation.services.products.ProductService;

@Slf4j
@Component
public class OutcomeService extends BaseService<OutcomeEntity, OutcomeView, Integer> {

    private final IncomeService incomeService;
    private final ProductService productService;

    public OutcomeService(final OutcomeRepository repository, final OutcomeConverter converter, final OutcomeMapper outcomeMapper,
        final CriteriaRepository criteriaRepository, final OutcomeValidator validationService, final IncomeService incomeService,
        final ProductService productService) {
        super(repository, converter, outcomeMapper, criteriaRepository, validationService);
        this.incomeService = incomeService;
        this.productService = productService;
    }

    @Override
    public Criteria<OutcomeEntity> parse(final String restrict) throws WrongRestrictionException {
        return new OutcomeCriteria(restrict);
    }

    @Override
    public void postCreate(final OutcomeEntity entity) {
        final ProductEntity product = entity.getProduct();

        if (product != null) {
            final double profit = incomeService.handleWithdraw(product, entity.getAmount(), entity.getSalePrice());
            entity.setProfit(profit);
            recalculateTotalResidual(product);
        }
    }

    private void recalculateTotalResidual(final ProductEntity product) {
        final IncomeCriteria incomeCriteria = new IncomeCriteria();
        incomeCriteria.setProductId(product.getId());
        incomeCriteria.setHasMore(Boolean.TRUE);
        final double residual = incomeService.getList(incomeCriteria).stream()
            .map(IncomeEntity::getResidual)
            .reduce((double) 0, Double::sum);

        product.setResidual(residual);
    }
}
