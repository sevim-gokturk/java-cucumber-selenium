import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Set the base URL for the API

        String requestBody = "{\n" + // Create a JSON object for the request body
                "  \"name\": \"John Doe\",\n" +
                "  \"username\": \"johndoe\",\n" +
                "  \"email\": \"johndoe@example.com\"\n" +
                "}";

        given() // Start setting up the POST request
                    .header("Content-Type", "application/json")  // Set the content type as JSON to tell the API that we're sending JSON data
                    .body(requestBody)  // Attach the JSON request body
                .when()
                    .post("/users")  // Send a POST request to the /users endpoint to create a new user
                .then()
                    .statusCode(201)  // Ensure the status code is 201 (Created), indicating the user was successfully created
                    .body("name", equalTo("John Doe"))  // Verify that the "name" field in the response matches the expected value "John Doe"
                    .body("username", equalTo("johndoe"))  // Verify that the "username" field in the response matches the expected value "johndoe"
                    .body("email", equalTo("johndoe@example.com"));  // Verify that the "email" field in the response matches the expected value "johndoe@example.com"
    }
}
