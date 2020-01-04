package ua.net.kurpiak.commoditycirculation.services.outcome;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import ua.net.kurpiak.commoditycirculation.convertors.OutcomeOrderConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.ActionNotAllowed;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ServiceErrorException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.mapper.OutcomeOrderMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Slf4j
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
        log.info("Create new outcome order: {}", view);

        if (CollectionUtils.isEmpty(view.getOutcomes())) {
            throw new ValidationException("outcomes", "Потрібно вибрати хоча б один товар до продажу");
        }

        final Integer orderId = super.create(view);

        for (final OutcomeView outcomeView : view.getOutcomes()) {
            outcomeView.setOutcomeOrderId(orderId);
            outcomeService.create(outcomeView);
        }

        enrichTotalFieldsOfOrder(orderId);

        return orderId;
    }

    @Override
    public boolean update(final OutcomeOrderView view) throws BaseException {
        log.error("Try to update outcome order: {}", view);
        throw new ActionNotAllowed("Не можна редагувати замовлення");
    }

    @Override
    public boolean delete(final Integer id) throws BaseException {
        log.error("Try to delete outcome order: {}", id);
        throw new ActionNotAllowed("Не можна видалити замовлення");
    }

    private void enrichTotalFieldsOfOrder(final int orderId) {
        final OutcomeCriteria outcomeCriteria = new OutcomeCriteria();
        outcomeCriteria.setOrderId(orderId);

        double totalPrice = 0;
        double totalProfit = 0;

        final List<OutcomeEntity> outcomes = outcomeService.getList(outcomeCriteria);
        for (OutcomeEntity outcome : outcomes) {
            totalPrice += outcome.getSalePrice() * outcome.getAmount();
            totalProfit += outcome.getProfit();
        }

        final OutcomeOrderEntity outcomeOrder = repository.findById(orderId)
            .orElseThrow(ServiceErrorException::new);

        outcomeOrder.setTotalProfit(totalProfit);
        outcomeOrder.setTotalPrice(totalPrice);
    }

}
