package ua.net.kurpiak.commoditycirculation.persistence.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import ua.net.kurpiak.commoditycirculation.exceptions.WrongRestrictionException;
import ua.net.kurpiak.commoditycirculation.pojo.enums.OrderDirectionEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Criteria<T> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private int offset;
    private int limit;
    private String order_by = "id";
    private OrderDirectionEnum order_direction = OrderDirectionEnum.ASC;

    @JsonIgnore
    private String userCriteria;
    @JsonIgnore
    private final Class<T> entityClass;

    public Criteria(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public OrderDirectionEnum getOrder_direction() {
        return order_direction;
    }

    public void setOrder_direction(OrderDirectionEnum order_direction) {
        this.order_direction = order_direction;
    }

    /**
     * implement this to set query params
     *
     * @param root - entity root.
     *             use <code>root.get("field_name")</code> to get the field from root entity
     * @param cb - create main query expressions
     *
     * @return list of predicates to be applied to query
     */
    public abstract List<Predicate> query(Root<T> root, CriteriaBuilder cb);

    public Query createQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);

        Root<T> root = query.from(entityClass);
        query.select(root);
        query.distinct(true);
        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        query.orderBy(formOrder(cb, root));

        return em.createQuery(query);
    }

    public Query createCountQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<T> root = query.from(entityClass);
        query.select(cb.count(root));
        query.distinct(true);
        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        query.orderBy(formOrder(cb, root));

        return em.createQuery(query);
    }

    protected Order formOrder(CriteriaBuilder cb, Root<T> root) {
        Order order;

        if (order_direction != null && order_by != null && !order_by.isEmpty()) {
            if (order_direction == OrderDirectionEnum.ASC) {
                order = cb.asc(root.get(order_by));
            } else {
                order = cb.desc(root.get(order_by));
            }
        } else {
            order = cb.asc(root.get("id"));
        }

        return order;
    }

    /**
     * return null if {#restriction} is empty or null
     *
     * @param restriction - json representation of object
     * @param clazz - class of object
     * @param <T> - type that extend Criteria
     * @return
     * @throws WrongRestrictionException - if passed wrong json format
     */
    protected <T extends Criteria> T parse(String restriction, Class<T> clazz) throws WrongRestrictionException {
        if(restriction == null || restriction.isEmpty() || restriction.equals("{}"))
            return null;

        this.userCriteria = restriction;

        try {
            T parsed = OBJECT_MAPPER.readValue(restriction, clazz);

            if (parsed.getLimit() > 0) {
                setLimit(parsed.getLimit());
            }

            if (parsed.getOffset() > 0) {
                setOffset(parsed.getOffset());
            }

            if (parsed.getOrder_by() != null)
                setOrder_by(parsed.getOrder_by());
            if (parsed.getOrder_direction() != null)
                setOrder_direction(parsed.getOrder_direction());

            return parsed;
        } catch (Exception e){
            throw new WrongRestrictionException();
        }
    }

    @Override
    @SneakyThrows
    public String toString() {
        return userCriteria == null
               ? OBJECT_MAPPER.writeValueAsString(this)
               : userCriteria;
    }
}
