package ua.net.kurpiak.commoditycirculation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;

@Mapper(config = DefaultMapperConfig.class, uses = { ProductRepository.class, IncomeOrderRepository.class })
public interface IncomeMapper extends ViewToEntityMapper<IncomeEntity, IncomeView> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incomeOrder", source = "incomeOrderId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "residual", source = "amount")
    @Mapping(target = "hasMore", constant = "true")
    @Mapping(target = "product", source = "productId")
    @Mapping(target = "incomePrice", source = "incomePrice")
    @Mapping(target = "incomePriceUsd", source = "incomePriceUsd")
    IncomeEntity mapToEntity(IncomeView view);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incomeOrder", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "residual", source = "amount")
    @Mapping(target = "hasMore", constant = "true")
    @Mapping(target = "incomePrice", source = "incomePrice")
    @Mapping(target = "incomePriceUsd", source = "incomePriceUsd")
    void updateEntity(@MappingTarget IncomeEntity entity, IncomeView view);
}
