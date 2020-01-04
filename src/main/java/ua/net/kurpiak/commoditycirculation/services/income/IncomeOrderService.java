package ua.net.kurpiak.commoditycirculation.services.income;

import static org.springframework.util.CollectionUtils.isEmpty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ua.net.kurpiak.commoditycirculation.convertors.IncomeOrderConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.ActionNotAllowed;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.mapper.IncomeOrderMapper;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Slf4j
@Service
@Transactional(rollbackFor = BaseException.class)
public class IncomeOrderService extends BaseService<IncomeOrderEntity, IncomeOrderView, Integer> {

    private final IncomeService incomeService;

    public IncomeOrderService(final IncomeOrderRepository repository, final IncomeOrderConverter converter, final IncomeOrderMapper incomeOrderMapper,
        final CriteriaRepository criteriaRepository, final IncomeOrderValidator validationService, final IncomeService incomeService) {
        super(repository, converter, incomeOrderMapper, criteriaRepository, validationService);

        this.incomeService = incomeService;
    }

    @Override
    public Criteria<IncomeOrderEntity> parse(String restrict) {
        return new IncomeOrderCriteria(restrict);
    }

    @Override
    public Integer create(IncomeOrderView view) throws BaseException {
        log.info("Create new income order: {}", view);

        if (isEmpty(view.getIncomes())) {
            throw new ValidationException("income order", "Виберіть хоча б одну позицію");
        }

        Integer id = super.create(view);

        for (IncomeView income : view.getIncomes()) {
            income.setIncomeOrderId(id);

            incomeService.create(income);
        }

        return id;
    }

    @Override
    public boolean update(final IncomeOrderView view) throws BaseException {
        log.error("Try to update income order: {}", view);
        throw new ActionNotAllowed("Не можна видаляти замовлення");
    }

    @Override
    public boolean delete(final Integer id) throws BaseException {
        log.error("Try to delete income order: {}", id);
        throw new ActionNotAllowed("Не можна видаляти замовлення");
    }
}
