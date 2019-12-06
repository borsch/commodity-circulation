package ua.net.kurpiak.commoditycirculation.services.income;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ua.net.kurpiak.commoditycirculation.convertors.IncomeOrderConverter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Service
@Transactional(rollbackFor = BaseException.class)
public class IncomeOrderService extends BaseService<IncomeOrderEntity, IncomeOrderView, Integer> {

    private final IncomeService incomeService;

    public IncomeOrderService(final IncomeOrderRepository repository, final IncomeOrderConverter converter, final MapperFacade mapperFacade,
        final CriteriaRepository criteriaRepository, final IncomeOrderValidator validationService, final IncomeService incomeService) {
        super(repository, converter, mapperFacade, criteriaRepository, validationService);

        this.incomeService = incomeService;
    }

    @Override
    public Criteria<IncomeOrderEntity> parse(String restrict) {
        return new IncomeOrderCriteria(restrict);
    }

    @Override
    public IncomeOrderEntity newInstance() {
        return new IncomeOrderEntity();
    }

    @Override
    public Integer create(IncomeOrderView view) throws BaseException {
        if (isEmpty(view.getIncomes()))
            throw new ValidationException("income order", "Виберіть хоча б одну позицію");

        Integer id = super.create(view);

        for (IncomeView income : view.getIncomes()) {
            income.setIncomeOrderId(id);

            incomeService.create(income);
        }

        return id;
    }

    public static void initMapperFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(IncomeOrderEntity.class, IncomeOrderView.class)
                     .field("comment", "comment").mapNulls(false).mapNullsInReverse(false)
                     .customize(new CustomMapper<IncomeOrderEntity, IncomeOrderView>() {
                         @Override
                         public void mapBtoA(IncomeOrderView view, IncomeOrderEntity entity, MappingContext context) {
                             if (!StringUtils.isEmpty(view.getDateCreated())) {
                                 try {
                                     entity.setDateCreated(LocalDate.parse(view.getDateCreated()));
                                 } catch (Exception e) {
                                     throw new ValidationException("income order date", "Дата приходу має неправельний формат");
                                 }
                             } else {
                                 entity.setDateCreated(LocalDate.now());
                             }
                         }
                     })
                     .register();
    }
}
