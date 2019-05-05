package ua.net.kurpiak.commoditycirculation.services.income;

import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class IncomeOrderValidator extends BaseValidator<IncomeOrderEntity, Integer> {
    public IncomeOrderValidator() {
        super(IncomeOrderEntity.class);
    }
}
