package api_tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Tag("api")
public class ApiTests {

    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Example base URL
    }

    @Test
    public void testGetPostById() {
        given().
                pathParam("id", 1).
                when().
                get("/posts/{id}").
                then().
                statusCode(200).
                body("id", equalTo(1));
    }

    @Test
    public void testGetAllPosts() {
        when().
                get("/posts").
                then().
                statusCode(200).
                body("size()", greaterThan(0));
    }

    @Test
    public void testGetPostByInvalidId() {
        given().
                pathParam("id", 99999).
                when().
                get("/posts/{id}").
                then().
                statusCode(404);
    }
}
