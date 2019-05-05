package ua.net.kurpiak.commoditycirculation.persistence.dao;

import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;

public interface ProductRepository extends BaseRepository<ProductEntity, Integer> {

    ProductEntity findByCode(String code);

}
