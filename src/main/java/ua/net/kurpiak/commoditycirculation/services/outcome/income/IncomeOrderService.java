package ua.net.kurpiak.commoditycirculation.services.outcome.income;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.IncomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional(rollbackFor = BaseException.class)
public class IncomeOrderService extends BaseService<IncomeOrderEntity, IncomeOrderView, Integer> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private IncomeService incomeService;

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
                                     entity.setDateCreated(DATE_FORMAT.parse(view.getDateCreated()));
                                 } catch (ParseException e) {
                                     throw new ValidationException("income order date", "Дата приходу має неправельний формат");
                                 }
                             } else {
                                 entity.setDateCreated(new Date());
                             }
                         }
                     })
                     .register();
    }
}
