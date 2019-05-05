package ua.net.kurpiak.commoditycirculation.exceptions.service_error;

import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 10.09.2016.
 */
public class ValidationException extends BaseException {

    private Set<String> codes;

    public <T> ValidationException(String className, Set<ConstraintViolation<T>> violations){
        super(String.format(DEFAULT_MESSAGE, className));
        this.codes = new HashSet<>();
        if(violations != null) {
            for (ConstraintViolation<T> t : violations) {
                codes.add(t.getMessage());
            }
        }
    }

    public ValidationException(String className, String code){
        super(String.format(DEFAULT_MESSAGE, className));
        this.codes = new HashSet<>();
        this.codes.add(code);
    }

    @Override
    public String formMessage() {
        return "Помилка валідації";
    }

    @Override
    public List<String> formListErrors() {
        return new ArrayList<>(codes);
    }

    private static final String DEFAULT_MESSAGE = "Entity of type %s got violations";

}
