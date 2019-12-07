package ua.net.kurpiak.commoditycirculation.services.outcome;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ua.net.kurpiak.commoditycirculation.convertors.OutcomeOrderConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.mapper.OutcomeOrderMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Component
public class OutcomeOrderService extends BaseService<OutcomeOrderEntity, OutcomeOrderView, Integer> {

    private final OutcomeService outcomeService;

    public OutcomeOrderService(final OutcomeOrderRepository repository, final OutcomeOrderConverter converter, final OutcomeOrderMapper outcomeOrderMapper,
        final CriteriaRepository criteriaRepository, final OutcomeOrderValidator validationService, final OutcomeService outcomeService) {
        super(repository, converter, outcomeOrderMapper, criteriaRepository, validationService);

        this.outcomeService = outcomeService;
    }

    @Override
    public Criteria<OutcomeOrderEntity> parse(final String restrict) throws WrongRestrictionException {
        return new OutcomeOrderCriteria(restrict);
    }

    @Override
    public Integer create(final OutcomeOrderView view) throws BaseException {
        if (CollectionUtils.isEmpty(view.getOutcomes())) {
            throw new ValidationException("outcomes", "Потрібно вибрати хоча б один товар до продажу");
        }

        final Integer id = super.create(view);

        for (OutcomeView outcomeView : view.getOutcomes()) {
            outcomeView.setOutcomeOrderId(id);

            outcomeService.create(outcomeView);
        }

        return id;
    }

}
