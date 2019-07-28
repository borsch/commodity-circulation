package ua.net.kurpiak.commoditycirculation.services.outcome;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.services.BaseValidator;

@Component
public class OutcomeOrderValidator extends BaseValidator<OutcomeOrderEntity, Integer> {

    public OutcomeOrderValidator() {
        super(OutcomeOrderEntity.class);
    }
}
