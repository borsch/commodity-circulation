package ua.net.kurpiak.commoditycirculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ua.net.kurpiak.commoditycirculation.persistence.dao")
public class CommodityCirculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommodityCirculationApplication.class, args);
	}

}
