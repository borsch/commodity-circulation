package ua.net.kurpiak.commoditycirculation.controllers.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import io.restassured.http.ContentType;
import ua.net.kurpiak.commoditycirculation.AbstractSpringBootTest;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeOrderView;
import ua.net.kurpiak.commoditycirculation.pojo.views.IncomeView;

@DatabaseSetup("/db/products.xml")
@DatabaseTearDown("/tearDown.xml")
class IncomeOrderApiControllerTest extends AbstractSpringBootTest {

    @Test
    @ExpectedDatabase(value = "/IncomeOrderApiControllerTest/expectedAfterNew.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void shouldCreateIncomeOrder() throws Exception {
        final IncomeOrderView view = IncomeOrderView.builder()
            .dateCreated(LocalDate.of(2222, 2, 2))
            .income(
                IncomeView.builder()
                    .productId(1)
                    .amount(10)
                    .incomePrice(200)
                    .incomePriceUsd(8)
                    .build()
            )
            .income(
                IncomeView.builder()
                    .productId(2)
                    .amount(17.2)
                    .incomePrice(15)
                    .incomePriceUsd(1)
                    .build()
            )
            .build();

        given()
            .body(toJson(view))
            .contentType(ContentType.JSON)
        .then()
            .body("result", notNullValue())
        .when()
            .put("/api/orders/income");
    }

}