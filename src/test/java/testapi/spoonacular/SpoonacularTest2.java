package testapi.spoonacular;


import java.io.File;
import java.io.IOException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testapi.AbstractTest;
import static io.restassured.RestAssured.given;



public class SpoonacularTest2 extends AbstractTest {

    private static final String API_KEY = "ea927ed1674c4bf283cae302953273fe";
    private static RequestSpecification BASE_SPEC;
    private static ResponseSpecification RESPONSE_SPEC;

    @BeforeAll
    static void beforeAll() {

        RestAssured.baseURI = "https://api.spoonacular.com";

        BASE_SPEC = new RequestSpecBuilder()
                .addQueryParam("apiKey", API_KEY)
                .log(LogDetail.ALL)
                .build();

        RESPONSE_SPEC = new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void postRequest() throws IOException, JSONException {

        Response response = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("title", "Homemade dumplings")
                .queryParam("ingredientList", "flour")
                .header("Content-type", "application/json")
                .and()
                .when()
                .post("recipes/cuisine")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void postRequest2() {

        Response response = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("title", "KuraMura")
                .queryParam("ingredientList", "chicken\\ncheese\\na pineapple\\npurring cat")
                .header("Content-type", "application/json")
                .and()
                .when()
                .post("recipes/cuisine")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void postRequest3() {

        File jsonData = new File("D:\\backend-test-feb-1-master\\src\\test\\resources\\testapi\\spoonacular\\1.json");
        Response response = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("username", "tat88")
                .queryParam("hash", "a76077e2cec479fcef00e05e2dd96ea120506b6b")
                .header("Content-type", "application/json")
                .body(jsonData)
                .and()
                .when()
                .post("mealplanner/tat88/items")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    public void postRequest4() {

        File jsonData = new File("D:\\backend-test-feb-1-master\\src\\test\\resources\\testapi\\spoonacular\\2.json");
        Response response = given()
                .queryParam("apiKey", API_KEY)
                .queryParam("username", "tat88")
                .queryParam("hash", "a76077e2cec479fcef00e05e2dd96ea120506b6b")
                .header("Content-type", "application/json")
                .body(jsonData)
                .and()
                .when()
                .post("mealplanner/tat88/items")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}