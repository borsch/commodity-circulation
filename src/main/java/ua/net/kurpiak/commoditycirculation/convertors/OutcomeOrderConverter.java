package ua.net.kurpiak.commoditycirculation.convertors;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.COMMENT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.DATE_CREATED;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.TOTAL_PRICE;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.TOTAL_PROFIT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.Product.OUTCOMES_INFO;

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
import ua.net.kurpiak.commoditycirculation.convertors.Fields.Product;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;

@Component
@RequiredArgsConstructor
public class OutcomeOrderConverter extends Converter<OutcomeOrderEntity> {

    private final ProductConverter productConverter;

    @Override
    public Map<String, Object> convert(final OutcomeOrderEntity object, final Collection<String> fields) {
        final Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(DATE_CREATED))
            map.put(DATE_CREATED, object.getDateCreated());
        if (fields.contains(COMMENT))
            map.put(COMMENT, object.getComment());
        if (fields.contains(TOTAL_PRICE))
            map.put(TOTAL_PRICE, object.getTotalPrice());
        if (fields.contains(TOTAL_PROFIT))
            map.put(TOTAL_PROFIT, object.getTotalProfit());
        if (fields.contains(OUTCOMES_INFO)) {
            final List<OutcomeDto> outcomes = object.getOutcomes().stream()
                .map(outcome -> OutcomeDto.builder()
                    .productInfo(productConverter.convert(outcome.getProduct(), Arrays.asList(Product.NAME, Product.CODE, Product.UNIT)))
                    .amount(outcome.getAmount())
                    .profit(outcome.getProfit())
                    .salePrice(outcome.getSalePrice())
                    .build()
                )
                .collect(Collectors.toList());

            map.put(OUTCOMES_INFO, outcomes);
        }

        return map;
    }

    @Value
    @Builder
    private static final class OutcomeDto {
        private final Map<String, Object> productInfo;
        private final double amount;
        private final double profit;
        private final double salePrice;
    }
}
