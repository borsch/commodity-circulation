package ua.net.kurpiak.commoditycirculation.controllers.rest;

import static io.restassured.RestAssured.when;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import ua.net.kurpiak.commoditycirculation.AbstractSpringBootTest;

class OutcomeOrderApiControllerTest extends AbstractSpringBootTest {

    @Test
    void getOutcomeList() {
        when()
            .get("/api/orders/outcome/")
        .then()
            .statusCode(HttpStatus.SC_OK);
    }

}