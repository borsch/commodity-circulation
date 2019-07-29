package ua.net.kurpiak.commoditycirculation.convertors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;

@Component
public class OutcomeConverter extends Converter<OutcomeEntity> {

    @Override
    public Map<String, Object> convert(final OutcomeEntity object, final Collection<String> fields) {
        return new HashMap<>();
    }
}
