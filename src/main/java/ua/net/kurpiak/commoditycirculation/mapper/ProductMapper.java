package ua.net.kurpiak.commoditycirculation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.pojo.entities.ProductEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.ProductView;

@Mapper(config = DefaultMapperConfig.class)
public interface ProductMapper extends ViewToEntityMapper<ProductEntity, ProductView> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unit", source = "unit")
    @Mapping(target = "residual", ignore = true)
    @Mapping(target = "defaultPurchasePrice", source = "defaultPurchasePrice")
    @Mapping(target = "defaultPurchasePriceUsd", source = "defaultPurchasePriceUsd")
    @Mapping(target = "defaultSalePrice", source = "defaultSalePrice")
    ProductEntity mapToEntity(ProductView view);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unit", source = "unit")
    @Mapping(target = "residual", ignore = true)
    @Mapping(target = "defaultPurchasePrice", source = "defaultPurchasePrice")
    @Mapping(target = "defaultPurchasePriceUsd", source = "defaultPurchasePriceUsd")
    @Mapping(target = "defaultSalePrice", source = "defaultSalePrice")
    void updateEntity(@MappingTarget ProductEntity entity, ProductView view);
}
