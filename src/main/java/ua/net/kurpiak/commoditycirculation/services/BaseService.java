package ua.net.kurpiak.commoditycirculation.services;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.net.kurpiak.commoditycirculation.convertors.Converter;
import ua.net.kurpiak.commoditycirculation.exceptions.BaseException;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.exceptions.not_found.NoSuchEntityException;
import ua.net.kurpiak.commoditycirculation.exceptions.service_error.ServiceErrorException;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.Criteria;
import ua.net.kurpiak.commoditycirculation.persistence.criteria.CriteriaRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.BaseRepository;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <ID> Entity primary key
 */
@Transactional(rollbackFor = BaseException.class)
public abstract class BaseService<E extends IHasId<ID>, V extends IHasId<ID>, ID extends Serializable&Comparable<ID>> implements IService<E, V, ID> {

    @Autowired
    protected BaseRepository<E, ID> repository;
    @Autowired
    protected Converter<E> converter;
    @Autowired
    protected MapperFacade mapperFacade;
    @Autowired
    protected CriteriaRepository criteriaRepository;
    @Autowired
    protected IValidator<E> validationService;

    @Override
    public E getById(ID id) throws BaseException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Немає об'єкту з таким ID"));
    }

    @Override
    public Map<String, Object> getById(ID id, Collection<String> fields) throws BaseException {
        return converter.convert(getById(id), fields);
    }

    @Override
    public List<E> getList(Criteria<E> criteria) throws BaseException {
        List<E> entities = criteriaRepository.find(criteria);

        if (entities.isEmpty())
            throw new NoSuchEntityException("Немає об'єкту з таким критеріями пошуку");

        return entities;
    }

    @Override
    public List<Map<String, Object>> getList(Collection<String> fields, String restrict) throws BaseException {
        return converter.convert(getList(parse(restrict)), fields);
    }

    @Override
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

    @Override
    public void postCreate(E entity) {

    }

    @Override
    public void postCreateSaved(E entity) {

    }

    @Override
    public boolean update(V view) throws BaseException {
        E entity = getById(view.getId());

        mapperFacade.map(view, entity);
        validationService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);

        return entity != null;
    }

    @Override
    public E updateEntity(E entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        return count(parse(restrict));
    }

    @Override
    public int count(Criteria<E> criteria){
        return criteriaRepository.count(criteria);
    }

    @Override
    public boolean delete(ID id) throws BaseException {
        E entity = getById(id);

        validationService.validForDelete(entity);
        repository.delete(entity);

        return true;
    }

}
