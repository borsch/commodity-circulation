package ua.net.kurpiak.commoditycirculation.services.income;

import javax.validation.Validator;

import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class IncomeOrderValidator extends BaseValidator<IncomeOrderEntity, Integer> {

    public IncomeOrderValidator(final Validator validator) {
        super(validator, IncomeOrderEntity.class);
    }
}
