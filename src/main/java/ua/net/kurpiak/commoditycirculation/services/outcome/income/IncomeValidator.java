package ua.net.kurpiak.commoditycirculation.services.outcome.income;

import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class IncomeValidator extends BaseValidator<IncomeEntity, Integer> {

    public IncomeValidator() {
        super(IncomeEntity.class);
    }

}
