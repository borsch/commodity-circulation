package ua.net.kurpiak.commoditycirculation.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ua.net.kurpiak.commoditycirculation.persistence.dao.IncomeOrderRepository;
import ua.net.kurpiak.commoditycirculation.persistence.dao.ProductRepository;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeOrderService;
import ua.net.kurpiak.commoditycirculation.services.income.IncomeService;
import ua.net.kurpiak.commoditycirculation.services.products.ProductService;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IncomeOrderRepository incomeOrderRepository;

    @Bean
    public MapperFacade mapperFactory() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        ProductService.initMapperFactory(mapperFactory);
        IncomeService.initMapperFactory(mapperFactory, productRepository, incomeOrderRepository);
        IncomeOrderService.initMapperFactory(mapperFactory);

        return mapperFactory.getMapperFacade();
    }

}
