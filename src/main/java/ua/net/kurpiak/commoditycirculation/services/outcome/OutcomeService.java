package ua.net.kurpiak.commoditycirculation.services.outcome;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.net.kurpiak.commoditycirculation.convertors.OutcomeConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.mapper.OutcomeMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeService;

@Slf4j
@Component
public class OutcomeService extends BaseService<OutcomeEntity, OutcomeView, Integer> {

    private final IncomeService incomeService;

    public OutcomeService(final OutcomeRepository repository, final OutcomeConverter converter, final OutcomeMapper outcomeMapper,
        final CriteriaRepository criteriaRepository, final OutcomeValidator validationService, final IncomeService incomeService) {
        super(repository, converter, outcomeMapper, criteriaRepository, validationService);
        this.incomeService = incomeService;
    }

    @Override
    public Criteria<OutcomeEntity> parse(final String restrict) throws WrongRestrictionException {
        return new OutcomeCriteria(restrict);
    }

    @Override
    public void postCreate(final OutcomeEntity entity) {
        if (entity.getProduct() != null) {
            incomeService.handleWithdraw(entity.getProduct(), entity.getAmount());
        }
    }

}
