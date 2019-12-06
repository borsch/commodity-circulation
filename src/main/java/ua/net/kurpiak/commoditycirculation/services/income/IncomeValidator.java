package ua.net.kurpiak.commoditycirculation.services.income;

import javax.validation.Validator;

import org.springframework.stereotype.Component;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class IncomeValidator extends BaseValidator<IncomeEntity, Integer> {

    public IncomeValidator(final Validator validator) {
        super(validator, IncomeEntity.class);
    }

}
