package ua.net.kurpiak.commoditycirculation.convertors;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.IncomeOrder.COMMENT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.IncomeOrder.DATE_CREATED;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.CODE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.INCOMES_INFO;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.NAME;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.UNIT;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;

@Component
@RequiredArgsConstructor
public class IncomeOrderConverter extends Converter<IncomeOrderEntity> {

    private final ProductConverter productConverter;

    @Override
    public Map<String, Object> convert(IncomeOrderEntity object, Collection<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(DATE_CREATED))
            map.put(DATE_CREATED, object.getDateCreated());
        if (fields.contains(COMMENT))
            map.put(COMMENT, object.getComment());
        if (fields.contains(INCOMES_INFO)) {
            final List<IncomeDto> incomes = object.getIncomes().stream()
                .map(income -> IncomeDto.builder()
                    .productInfo(productConverter.convert(income.getProduct(), Arrays.asList(NAME, CODE, UNIT)))
                    .residual(income.getResidual())
                    .incomePrice(income.getIncomePrice())
                    .incomePriceUsd(income.getIncomePriceUsd())
                    .amount(income.getAmount())
                    .build())
                .collect(Collectors.toList());

            map.put(INCOMES_INFO, incomes);
        }

        return map;
    }

    @Value
    @Builder
    private static final class IncomeDto {
        private final Map<String, Object> productInfo;
        private final double residual;
        private final double incomePrice;
        private final double incomePriceUsd;
        private final double amount;
    }
}
