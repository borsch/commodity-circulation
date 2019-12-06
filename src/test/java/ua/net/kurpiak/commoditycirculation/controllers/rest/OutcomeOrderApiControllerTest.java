package ua.net.kurpiak.commoditycirculation.controllers.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import io.restassured.http.ContentType;
import ua.net.kurpiak.commoditycirculation.AbstractSpringBootTest;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.OutcomeView;

@DatabaseTearDown("/tearDown.xml")
class OutcomeOrderApiControllerTest extends AbstractSpringBootTest {

    @Test
    @DatabaseSetup("/OutcomeOrderApiControllerTest/initDb.xml")
    @ExpectedDatabase(value = "/OutcomeOrderApiControllerTest/expectedAfterSuccessWithdraw.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void shouldCreateOutcomeOrder() throws Exception {
        final OutcomeOrderView outcomeOrderView = OutcomeOrderView.builder()
            .outcome(
                OutcomeView.builder()
                    .amount(15)
                    .productId(1)
                    .salePrice(10)
                .build()
            )
            .build();

        given()
            .body(toJson(outcomeOrderView))
            .contentType(ContentType.JSON)
        .then()
            .body("result", notNullValue())
        .when()
            .put("/api/orders/outcome/");
    }



}