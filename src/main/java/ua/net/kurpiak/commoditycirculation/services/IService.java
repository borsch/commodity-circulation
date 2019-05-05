package ua.net.kurpiak.commoditycirculation.services;

import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <I> Entity primary key
 */
public interface IService<E, V, ID extends Serializable> {

    E getById(ID id) throws BaseException;

    Map<String, Object> getById(ID id, Collection<String> fields) throws BaseException;

    List<E> getList(Criteria<E> criteria) throws BaseException;

    List<Map<String, Object>> getList(Collection<String> fields, String restrict) throws BaseException;

    Criteria<E> parse(String restrict) throws WrongRestrictionException;

    ID create(V view) throws BaseException;

    void postCreate(E entity);

    void postCreateSaved(E entity);

    boolean update(V view) throws BaseException;

    E updateEntity(E entity);

    int count(String restrict) throws WrongRestrictionException;

    boolean delete(ID id) throws BaseException;

    int count(Criteria<E> criteria);

    E newInstance();
}
