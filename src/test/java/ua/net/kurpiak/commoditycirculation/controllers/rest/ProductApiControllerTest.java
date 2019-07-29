package ua.net.kurpiak.commoditycirculation.controllers.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.restassured.http.ContentType;
import ua.net.kurpiak.commoditycirculation.AbstractSpringBootTest;

@DatabaseSetup("/db/products.xml")
class ProductApiControllerTest extends AbstractSpringBootTest {

    @Test
    void getProductById() {
        when()
            .get("/api/products/1")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .body("result.id", is(1));
    }

}