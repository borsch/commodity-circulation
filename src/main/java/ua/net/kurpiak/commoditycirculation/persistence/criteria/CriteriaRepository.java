package ua.net.kurpiak.commoditycirculation.persistence.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * Created by oleh_kurpiak on 14.10.2016.
 */
@Repository
public class CriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public <T> List<T> find(Criteria<T> criteria) {
        Query query = criteria.createQuery(entityManager);
        if(criteria.getOffset() > 0)
            query.setFirstResult(criteria.getOffset());
        if(criteria.getLimit() > 0)
            query.setMaxResults(criteria.getLimit());
        return (List<T>) query.getResultList();
    }

    public <T> int count(Criteria<T> criteria) {
        Query query = criteria.createCountQuery(entityManager);
        Long count = (Long)query.getSingleResult();

        return count.intValue();
    }

}
