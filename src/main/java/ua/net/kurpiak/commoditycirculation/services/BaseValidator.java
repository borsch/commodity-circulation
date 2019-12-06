package ua.net.kurpiak.commoditycirculation.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import lombok.RequiredArgsConstructor;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ValidationException;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@RequiredArgsConstructor
public abstract class BaseValidator<E extends IHasId<I>, I extends Comparable<I>> {

    protected final Validator validator;
    private final Class<E> persistentClass;

    public void validForCreate(E entity) throws BaseException {
        validateConstraints(entity);
    }

    public void validForUpdate(E entity) throws BaseException {
        validateConstraints(entity);
    }

    protected void validateConstraints(E entity) throws ValidationException {
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if (violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    public void validForDelete(E entity) throws BaseException {

    }

}
