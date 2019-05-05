package ua.net.kurpiak.commoditycirculation.convertors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class Converter<T> {

    public abstract Map<String, Object> convert(T object, Collection<String> fields);

    public List<Map<String, Object>> convert(Collection<T> objects, Collection<String> fields){
        List<Map<String, Object>> result = new ArrayList<>();
        for(T t : objects)
            result.add(convert(t, fields));

        return result;
    }

}
