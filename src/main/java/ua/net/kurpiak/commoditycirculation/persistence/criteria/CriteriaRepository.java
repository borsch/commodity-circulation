package ua.net.kurpiak.commoditycirculation.persistence.criteria;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by oleh_kurpiak on 14.10.2016.
 */
@Repository
public class CriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> List<T> find(Criteria<T> criteria) {
        Query query = criteria.createQuery(entityManager);
        if(criteria.getOffset() > 0)
            query.setFirstResult(criteria.getOffset());
        if(criteria.getLimit() > 0)
            query.setMaxResults(criteria.getLimit());
        return query.getResultList();
    }

    public <T> int count(Criteria<T> criteria) {
        Query query = criteria.createCountQuery(entityManager);
        Long count = (Long)query.getSingleResult();

        return count.intValue();
    }

}
