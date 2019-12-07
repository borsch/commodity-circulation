package ua.net.kurpiak.commoditycirculation.services.income;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ua.net.kurpiak.commoditycirculation.convertors.IncomeConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.AmountExceedLimitException;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.mapper.IncomeMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Slf4j
@Service
@Transactional(rollbackFor = BaseException.class)
public class IncomeService extends BaseService<IncomeEntity, IncomeView, Integer> {

    private final ProductRepository productRepository;
    private final IncomeRepository incomeRepository;

    public IncomeService(final IncomeRepository repository, final IncomeConverter converter, final IncomeMapper incomeMapper,
        final CriteriaRepository criteriaRepository, final IncomeValidator validationService, final ProductRepository productRepository) {
        super(repository, converter, incomeMapper, criteriaRepository, validationService);

        this.productRepository = productRepository;
        this.incomeRepository = repository;
    }

    @Override
    public Criteria<IncomeEntity> parse(String restrict) throws WrongRestrictionException {
        return new IncomeCriteria(restrict);
    }

    @Override
    public void postCreate(IncomeEntity entity) {
        entity.setHasMore(entity.getAmount() > 0);
        entity.setResidual(entity.getAmount());
    }

    @Override
    public void postCreateSaved(IncomeEntity entity) {
        ProductEntity product = entity.getProduct();

        product.setResidual(product.getResidual() + entity.getAmount());
        product.setDefaultPurchasePrice(entity.getIncomePrice());
        product.setDefaultPurchasePriceUsd(entity.getIncomePriceUsd());

        productRepository.save(product);
    }

    public void handleWithdraw(ProductEntity product, double amount) {
        List<IncomeEntity> incomes = incomeRepository.findByProductWithHasMore(product.getId());
        log.info("Found {} income(s) for product {}({})\n{}", incomes.size(), product.getName(), product.getCode(), incomes);

        validateWithdrawAmount(incomes, product, amount);

        for (IncomeEntity income : incomes) {
            if (amount == 0) {
                break;
            }

            double residual = income.getResidual() - Math.min(income.getResidual(), amount);
            amount -= Math.min(income.getResidual(), amount);

            income.setResidual(residual);
            income.setHasMore(residual > 0);
        }

        incomeRepository.saveAll(incomes);
    }

    private void validateWithdrawAmount(List<IncomeEntity> incomes, ProductEntity product, double wantedAmount) {
        double existingAmount = incomes.stream()
            .map(IncomeEntity::getResidual)
            .reduce((double) 0, Double::sum);

        if (wantedAmount > existingAmount) {
            throw AmountExceedLimitException.of(product.getName(), product.getCode(), existingAmount, wantedAmount);
        }
    }

}
