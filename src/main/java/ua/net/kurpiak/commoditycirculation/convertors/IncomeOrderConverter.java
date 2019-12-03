package ua.net.kurpiak.commoditycirculation.convertors;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.AMOUNT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.INCOME_PRICE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.INCOME_PRICE_USD;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.PRODUCT_INFO;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Income.RESIDUAL;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.IncomeOrder.COMMENT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.IncomeOrder.DATE_CREATED;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.IncomeOrder.INCOMES_ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.INCOMES_INFO;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;

@Component
public class IncomeOrderConverter extends Converter<IncomeOrderEntity> {

    @Autowired
    private IncomeConverter incomeConverter;

    @Override
    public Map<String, Object> convert(IncomeOrderEntity object, Collection<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(DATE_CREATED))
            map.put(DATE_CREATED, object.getDateCreated());
        if (fields.contains(COMMENT))
            map.put(COMMENT, object.getComment());
        if (fields.contains(INCOMES_ID))
            map.put(INCOMES_ID, object.getIncomes().stream().map(IncomeEntity::getId).collect(Collectors.toList()));
        if (fields.contains(INCOMES_INFO))
            map.put(INCOMES_INFO, incomeConverter.convert(object.getIncomes(), Arrays.asList(PRODUCT_INFO, AMOUNT, RESIDUAL, INCOME_PRICE, INCOME_PRICE_USD)));

        return map;
    }
}
