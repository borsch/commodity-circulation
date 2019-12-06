package ua.net.kurpiak.commoditycirculation.services.outcome;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class OutcomeValidator extends BaseValidator<OutcomeEntity, Integer> {

    public OutcomeValidator(final Validator validator) {
        super(validator, OutcomeEntity.class);
    }
}
