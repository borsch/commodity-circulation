package ua.net.kurpiak.commoditycirculation.convertors;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.CODE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.DEFAULT_PURCHASE_PRICE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.DEFAULT_PURCHASE_PRICE_USD;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.DEFAULT_SALE_PRICE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.INCOMES_INFO;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.NAME;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.OUTCOMES_INFO;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.RESIDUAL;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.UNIT;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeCriteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;

@Component
@RequiredArgsConstructor
public class ProductConverter extends Converter<ProductEntity> {

    private final CriteriaRepository criteriaRepository;

    @Override
    public Map<String, Object> convert(ProductEntity object, Collection<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(CODE))
            map.put(CODE, object.getCode());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());
        if (fields.contains(UNIT))
            map.put(UNIT, object.getUnit());
        if (fields.contains(RESIDUAL))
            map.put(RESIDUAL, object.getResidual());
        if (fields.contains(DEFAULT_PURCHASE_PRICE))
            map.put(DEFAULT_PURCHASE_PRICE, object.getDefaultPurchasePrice());
        if (fields.contains(DEFAULT_PURCHASE_PRICE_USD))
            map.put(DEFAULT_PURCHASE_PRICE_USD, object.getDefaultPurchasePriceUsd());
        if (fields.contains(DEFAULT_SALE_PRICE))
            map.put(DEFAULT_SALE_PRICE, object.getDefaultSalePrice());

        if (fields.contains(INCOMES_INFO)) {
            IncomeCriteria incomeCriteria = new IncomeCriteria();
            incomeCriteria.setProductId(object.getId());
            incomeCriteria.setHasMore(true);
            incomeCriteria.setFetch(false);

            IncomeTotalPrice totalPrice = criteriaRepository.find(incomeCriteria).stream()
                   .map(IncomeTotalPrice::of)
                   .reduce(new IncomeTotalPrice(0, 0), IncomeTotalPrice::merge);

            map.put(INCOMES_INFO, totalPrice);
        }

        if (fields.contains(OUTCOMES_INFO)) {
            OutcomeCriteria outcomeCriteria = new OutcomeCriteria();
            outcomeCriteria.setProductId(object.getId());
            outcomeCriteria.setFetch(false);

            final OutcomeTotalPrice totalPrice = criteriaRepository.find(outcomeCriteria).stream()
                .map(OutcomeTotalPrice::of)
                .reduce(new OutcomeTotalPrice(0, 0), OutcomeTotalPrice::merge);

            map.put(OUTCOMES_INFO, totalPrice);
        }

        return map;
    }

    @Getter
    @RequiredArgsConstructor
    private static final class IncomeTotalPrice {
        private final double totalIncomePrice;
        private final double totalIncomePriceUsd;

        private static IncomeTotalPrice of(final IncomeEntity in) {
            return new IncomeTotalPrice(in.getIncomePrice() * in.getResidual(), in.getIncomePriceUsd() * in.getResidual());
        }

        private static IncomeTotalPrice merge(final IncomeTotalPrice p1, final IncomeTotalPrice p2) {
            return new IncomeTotalPrice(p1.totalIncomePrice + p2.totalIncomePrice, p1.totalIncomePriceUsd + p2.totalIncomePriceUsd);
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static final class OutcomeTotalPrice {
        private final double totalSalePrice;
        private final double totalProfit;

        private static OutcomeTotalPrice of(final OutcomeEntity out) {
            return new OutcomeTotalPrice(out.getAmount() * out.getSalePrice(), out.getProfit());
        }

        private static OutcomeTotalPrice merge(final OutcomeTotalPrice p1, final OutcomeTotalPrice p2) {
            return new OutcomeTotalPrice(p1.totalSalePrice + p2.totalSalePrice, p1.totalProfit + p2.totalProfit);
        }
    }
}
