package ua.net.kurpiak.commoditycirculation.services.outcome;

import javax.validation.Validator;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class OutcomeOrderValidator extends BaseValidator<OutcomeOrderEntity, Integer> {

    public OutcomeOrderValidator(final Validator validator) {
        super(validator, OutcomeOrderEntity.class);
    }
}
