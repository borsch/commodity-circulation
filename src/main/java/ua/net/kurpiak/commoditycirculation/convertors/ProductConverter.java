package ua.net.kurpiak.commoditycirculation.convertors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeCriteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.*;

@Component
public class ProductConverter extends Converter<ProductEntity> {

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public Map<String, Object> convert(ProductEntity object, Collection fields) {
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

            TotalPrice totalPrice = criteriaRepository.find(incomeCriteria).stream()
                   .map(in -> new TotalPrice(in.getIncomePrice() * in.getResidual(), in.getIncomePriceUsd() * in.getResidual()))
                   .reduce(new TotalPrice(0, 0), (p1, p2) -> new TotalPrice(p1.totalIncomePrice + p2.totalIncomePrice, p1.totalIncomePriceUsd + p2.totalIncomePriceUsd));

            map.put(INCOMES_INFO, totalPrice);
        }

        return map;
    }

    private static final class TotalPrice {
        private double totalIncomePrice;
        private double totalIncomePriceUsd;

        public TotalPrice(double totalIncomePrice, double totalIncomePriceUsd) {
            this.totalIncomePrice = totalIncomePrice;
            this.totalIncomePriceUsd = totalIncomePriceUsd;
        }

        public double getTotalIncomePrice() {
            return totalIncomePrice;
        }

        public double getTotalIncomePriceUsd() {
            return totalIncomePriceUsd;
        }

    }
}
