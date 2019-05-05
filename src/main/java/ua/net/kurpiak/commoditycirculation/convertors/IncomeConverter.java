package ua.net.kurpiak.commoditycirculation.convertors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.*;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.RESIDUAL;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.*;

@Component
public class IncomeConverter extends Converter<IncomeEntity> {

    @Autowired
    private ProductConverter productConverter;

    @Override
    public Map<String, Object> convert(IncomeEntity object, Collection<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(PRODUCT_ID))
            map.put(PRODUCT_ID, object.getProduct().getId());
        if (fields.contains(PRODUCT_INFO))
            map.put(PRODUCT_INFO, productConverter.convert(object.getProduct(), Arrays.asList(NAME, CODE, UNIT)));
        if (fields.contains(INCOME_ORDER_ID))
            map.put(INCOME_ORDER_ID, object.getIncomeOrder().getId());
        if (fields.contains(AMOUNT))
            map.put(AMOUNT, object.getAmount());
        if (fields.contains(RESIDUAL))
            map.put(RESIDUAL, object.getResidual());
        if (fields.contains(INCOME_PRICE))
            map.put(INCOME_PRICE, object.getIncomePrice());
        if (fields.contains(INCOME_PRICE_USD))
            map.put(INCOME_PRICE_USD, object.getIncomePriceUsd());

        return map;
    }
}
