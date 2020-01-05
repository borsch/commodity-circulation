package ua.net.kurpiak.commoditycirculation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.pojo.entities.IncomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;

@Mapper(config = DefaultMapperConfig.class, uses = DefaultTypeMapper.class)
public interface IncomeOrderMapper extends ViewToEntityMapper<IncomeOrderEntity, IncomeOrderView> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incomes", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "totalPriceUsd", ignore = true)
    @Mapping(target = "dateCreated", source = "dateCreated", qualifiedByName = DefaultTypeMapper.MAP_DATE_TIME_OR_DEFAULT)
    @Mapping(target = "comment", source = "comment")
    IncomeOrderEntity mapToEntity(IncomeOrderView view);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incomes", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "totalPriceUsd", ignore = true)
    @Mapping(target = "comment", source = "comment")
    void updateEntity(@MappingTarget IncomeOrderEntity entity, IncomeOrderView view);
}
