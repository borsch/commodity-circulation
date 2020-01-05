package ua.net.kurpiak.commoditycirculation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.pojo.entities.OutcomeOrderEntity;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;

@Mapper(config = DefaultMapperConfig.class, uses = DefaultTypeMapper.class)
public interface OutcomeOrderMapper extends ViewToEntityMapper<OutcomeOrderEntity, OutcomeOrderView> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "outcomes", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "totalProfit", ignore = true)
    @Mapping(target = "dateCreated", source = "dateCreated", qualifiedByName = DefaultTypeMapper.MAP_DATE_TIME_OR_DEFAULT)
    @Mapping(target = "comment", source = "comment")
    OutcomeOrderEntity mapToEntity(OutcomeOrderView view);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "outcomes", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "totalProfit", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "comment", source = "comment")
    void updateEntity(@MappingTarget OutcomeOrderEntity entity, OutcomeOrderView view);
}
