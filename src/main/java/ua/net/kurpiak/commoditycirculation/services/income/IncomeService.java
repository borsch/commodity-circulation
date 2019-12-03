package ua.net.kurpiak.commoditycirculation.services.income;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ua.net.kurpiak.commoditycirculation.exceptions.AmountExceedLimitException;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
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

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private IncomeRepository incomeRepository;

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

    @Override
    public IncomeEntity newInstance() {
        return new IncomeEntity();
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

    public static void initMapperFactory(MapperFactory mapperFactory, ProductRepository productRepository,
                                         IncomeOrderRepository incomeOrderRepository) {
        mapperFactory.classMap(IncomeEntity.class, IncomeView.class)
                     .field("residual", "residual").mapNulls(false).mapNullsInReverse(false)
                     .field("amount", "amount").mapNulls(false).mapNullsInReverse(false)
                     .field("incomePrice", "incomePrice").mapNulls(false).mapNullsInReverse(false)
                     .field("incomePriceUsd", "incomePriceUsd").mapNulls(false).mapNullsInReverse(false)
                     .customize(new CustomMapper<IncomeEntity, IncomeView>() {
                         @Override
                         public void mapBtoA(IncomeView view, IncomeEntity entity, MappingContext context) {
                             if (view.getProductId() != null) {
                                 entity.setProduct(productRepository.findById(view.getProductId()).orElse(null));
                             }

                             if (view.getIncomeOrderId() != null) {
                                 entity.setIncomeOrder(incomeOrderRepository.findById(view.getIncomeOrderId()).orElse(null));
                             }
                         }
                     }).register();
    }
}
