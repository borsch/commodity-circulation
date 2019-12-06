package ua.net.kurpiak.commoditycirculation.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ua.net.kurpiak.commoditycirculation.convertors.Converter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.exceptions.not_found.NoSuchEntityException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ServiceErrorException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.BaseRepository;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <ID> Entity primary key
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = BaseException.class)
public abstract class BaseService<E extends IHasId<ID>, V extends IHasId<ID>, ID extends Serializable&Comparable<ID>> {

    protected final BaseRepository<E, ID> repository;
    protected final Converter<E> converter;
    protected final MapperFacade mapperFacade;
    protected final CriteriaRepository criteriaRepository;
    protected final BaseValidator<E, ID> validationService;

    public E getById(ID id) throws BaseException {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Немає об'єкту з таким ID"));
    }

    public Map<String, Object> getById(ID id, Collection<String> fields) throws BaseException {
        return converter.convert(getById(id), fields);
    }

    public List<E> getList(Criteria<E> criteria) throws BaseException {
        return criteriaRepository.find(criteria);
    }

    public List<Map<String, Object>> getList(Collection<String> fields, String restrict) throws BaseException {
        return converter.convert(getList(parse(restrict)), fields);
    }

    public ID create(V view) throws BaseException {
        E entity = newInstance();

        mapperFacade.map(view, entity);
        postCreate(entity);

        validationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if (entity == null)
            throw new ServiceErrorException();
        postCreateSaved(entity);

        return entity.getId();
    }

    public void postCreate(E entity) {

    }

    public void postCreateSaved(E entity) {

    }

    public boolean update(V view) throws BaseException {
        E entity = getById(view.getId());

        mapperFacade.map(view, entity);
        validationService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);

        return entity != null;
    }

    public E updateEntity(E entity) {
        return repository.saveAndFlush(entity);
    }

    public int count(String restrict) throws WrongRestrictionException {
        return count(parse(restrict));
    }

    public int count(Criteria<E> criteria){
        return criteriaRepository.count(criteria);
    }

    public boolean delete(ID id) throws BaseException {
        E entity = getById(id);

        validationService.validForDelete(entity);
        repository.delete(entity);

        return true;
    }

    protected abstract Criteria<E> parse(String restrict) throws WrongRestrictionException;

    protected abstract E newInstance();
}
