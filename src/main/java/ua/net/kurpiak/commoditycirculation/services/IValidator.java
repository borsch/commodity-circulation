package ua.net.kurpiak.commoditycirculation.services;

import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IValidator<E> {
    void validForCreate(E entity) throws BaseException;
    void validForUpdate(E entity) throws BaseException;
    void validForDelete(E entity) throws BaseException;
}
