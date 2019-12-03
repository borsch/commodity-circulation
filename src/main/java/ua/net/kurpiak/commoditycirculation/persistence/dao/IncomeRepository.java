package ua.net.kurpiak.commoditycirculation.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;

public interface IncomeRepository extends BaseRepository<IncomeEntity, Integer> {

    @Query("SELECT income FROM IncomeEntity income "
        + "WHERE income.product.id = :productId "
        + "AND income.hasMore = true "
        + "ORDER BY income.incomeOrder.dateCreated")
    List<IncomeEntity> findByProductWithHasMore(@Param("productId") int productId);

}
