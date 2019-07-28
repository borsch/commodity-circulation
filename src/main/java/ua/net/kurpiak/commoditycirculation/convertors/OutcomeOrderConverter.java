package ua.net.kurpiak.commoditycirculation.convertors;

import static ua.net.kurpiak.commoditycirculation.convertors.Fields.ID;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.COMMENT;
import static ua.net.kurpiak.commoditycirculation.convertors.Fields.OutcomeOrder.DATE_CREATED;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;

@Component
public class OutcomeOrderConverter extends Converter<OutcomeOrderEntity> {

    @Override
    public Map<String, Object> convert(final OutcomeOrderEntity object, final Collection<String> fields) {
        final Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(DATE_CREATED))
            map.put(DATE_CREATED, object.getDateCreated().getTime());
        if (fields.contains(COMMENT))
            map.put(COMMENT, object.getComment());

        return map;
    }
}
