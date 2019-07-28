package ua.net.kurpiak.commoditycirculation;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractSpringBootTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected Flyway flyway;

    @BeforeEach
    void beforeEach() {
        flyway.clean();
        flyway.migrate();

        RestAssured.port = port;
    }

}