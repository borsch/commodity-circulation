package ua.net.kurpiak.commoditycirculation.config;

import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.OutcomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeOrderService;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeService;
import ua.net.kurpiak.commoditycirculation.services.outcome.OutcomeOrderService;
import ua.net.kurpiak.commoditycirculation.services.outcome.OutcomeService;
import ua.net.kurpiak.commoditycirculation.services.products.ProductService;

@AllArgsConstructor
@org.springframework.context.annotation.Configuration
public class Configuration {

    private final ProductRepository productRepository;
    private final IncomeOrderRepository incomeOrderRepository;
    private final OutcomeOrderRepository outcomeOrderRepository;

    @Bean
    public MapperFacade mapperFactory() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        ProductService.initMapperFactory(mapperFactory);
        IncomeService.initMapperFactory(mapperFactory, productRepository, incomeOrderRepository);
        IncomeOrderService.initMapperFactory(mapperFactory);
        OutcomeOrderService.initMapperFactory(mapperFactory);
        OutcomeService.initMapperFactory(mapperFactory, productRepository, outcomeOrderRepository);

        return mapperFactory.getMapperFacade();
    }

}
