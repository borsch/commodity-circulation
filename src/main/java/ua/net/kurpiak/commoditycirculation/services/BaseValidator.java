package ua.net.kurpiak.commoditycirculation.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public abstract class BaseValidator<E extends IHasId<I>, I extends Comparable<I>> implements IValidator<E> {

    @Autowired
    public Validator validator;

    private Class<E> persistentClass;

    public BaseValidator(Class<E> persistentClass){
        this.persistentClass = persistentClass;
    }


    @Override
    public void validForCreate(E entity) throws BaseException {
        validateConstraints(entity);
    }

    @Override
    public void validForUpdate(E entity) throws BaseException {
        validateConstraints(entity);
    }

    protected void validateConstraints(E entity) throws ValidationException {
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if (violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    @Override
    public void validForDelete(E entity) throws BaseException {

    }

}
