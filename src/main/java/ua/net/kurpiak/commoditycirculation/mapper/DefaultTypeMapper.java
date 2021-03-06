package ua.net.kurpiak.commoditycirculation.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import ua.net.kurpiak.commoditycirculation.config.DefaultMapperConfig;
import ua.net.kurpiak.commoditycirculation.services.ClockService;

@Mapper(config = DefaultMapperConfig.class)
public abstract class DefaultTypeMapper {

    @Autowired
    private ClockService clockService;

    public static final String MAP_DATE_TIME_OR_DEFAULT = "MAP_DATE_TIME_OR_DEFAULT";

    @Named(MAP_DATE_TIME_OR_DEFAULT)
    public LocalDateTime mapDateTimeOrDefault(final LocalDateTime input) {
        return input == null ? clockService.getLocalDateTime() : input;
    }
}
