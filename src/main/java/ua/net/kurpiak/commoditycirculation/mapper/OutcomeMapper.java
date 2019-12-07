package ua.net.kurpiak.commoditycirculation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;

@Mapper(config = DefaultMapperConfig.class, uses = { ProductRepository.class, OutcomeOrderRepository.class })
public interface OutcomeMapper extends ViewToEntityMapper<OutcomeEntity, OutcomeView> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "outcomeOrderId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "product", source = "productId")
    @Mapping(target = "salePrice", source = "salePrice")
    OutcomeEntity mapToEntity(OutcomeView view);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "salePrice", source = "salePrice")
    void updateEntity(@MappingTarget OutcomeEntity entity, OutcomeView view);
}
