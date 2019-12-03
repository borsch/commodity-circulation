package ua.net.kurpiak.commoditycirculation.services.outcome;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.impl.OutcomeOrderCriteria;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;
import ua.net.kurpiak.commoditycirculation.services.BaseService;

@Component
public class OutcomeOrderService extends BaseService<OutcomeOrderEntity, OutcomeOrderView, Integer> {

    @Autowired
    private OutcomeService outcomeService;

    @Override
    public Criteria<OutcomeOrderEntity> parse(final String restrict) throws WrongRestrictionException {
        return new OutcomeOrderCriteria(restrict);
    }

    @Override
    public OutcomeOrderEntity newInstance() {
        return new OutcomeOrderEntity();
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

    public static void initMapperFactory(MapperFactory mapperFactory) {
        mapperFactory.classMap(OutcomeOrderEntity.class, OutcomeOrderView.class)
            .field("comment", "comment").mapNulls(false).mapNullsInReverse(false)
            .customize(new CustomMapper<OutcomeOrderEntity, OutcomeOrderView>() {
                @Override
                public void mapBtoA(OutcomeOrderView view, OutcomeOrderEntity entity, MappingContext context) {
                    if (!StringUtils.isEmpty(view.getDateCreated())) {
                        try {
                            entity.setDateCreated(LocalDate.parse(view.getDateCreated()));
                        } catch (Exception e) {
                            throw new ValidationException("income order date", "Дата продажу має неправельний формат");
                        }
                    } else {
                        entity.setDateCreated(LocalDate.now());
                    }
                }
            })
            .register();
    }
}
