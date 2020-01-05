package ua.net.kurpiak.commoditycirculation.convertors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;

@Component
public class IncomeConverter extends Converter<IncomeEntity> {

    @Override
    public Map<String, Object> convert(IncomeEntity object, Collection<String> fields) {
        return new HashMap<>();
    }
}
