package testapi.spoonacular;


import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import testapi.AbstractTest;
import io.restassured.RestAssured;
import net.javacrumbs.jsonunit.JsonAssert;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.is;

public class SpoonacularTest extends AbstractTest {

    private static String API_KEY = "ea927ed1674c4bf283cae302953273fe";

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";
    }

    @Test
    void testGetRecipesComplexSearch() throws IOException, JSONException {

        String actual = given()
                .param("apiKey", API_KEY)
                .param("query", "pasta")
                .param("cuisine", "italian")
                .param("includeIngredients", "tomato")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(3000L))
                .body("offset", is(0))
                .body("number", is(10))
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actual,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }

    @Test
    void testGetRecipesComplexSearch2() throws IOException, JSONException {

        String actual = given()
                .param("apiKey", API_KEY)
                .param("excludeCuisine", "italian")
                .param("diet", "vegetarian")
                .param("instructionsRequired", "true")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(3000L))
                .body("offset", is(0))
                .body("number", is(10))
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expected2.json");

        JsonAssert.assertJsonEquals(
                expected,
                actual,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }

    @Test
    void testGetRecipesComplexSearch3() throws IOException, JSONException {

        String actual = given()
                .param("apiKey", API_KEY)
                .param("query", "salad")
                .param("cuisine", "Russia")
                .param("includeIngredients", "cucumber")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(10000L))
                .body("offset", is(0))
                .body("number", is(10))
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expected3.json");

        JsonAssert.assertJsonEquals(
                expected,
                actual,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }

    @Test
    void testGetRecipesComplexSearch4() throws IOException, JSONException {

        String actual = given()
                .param("apiKey", API_KEY)
                .param("query", "soup")
                .param("cuisine", "Georgia")
                .param("addRecipeInformation", "true")
                .param("sortDirection", "asc")
                .log()
                .parameters()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("offset", is(0))
                .body("number", is(10))
                .log()
                .body()
                .when()
                .get("recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = getResourceAsString("expected4.json");

        JsonAssert.assertJsonEquals(
                expected,
                actual,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

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
    }


