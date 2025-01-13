package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

    private static RequestSpecification getRequestSpec() {
        return RestAssured
                .given()
                .baseUri(ConfigReader.getProperty("base_url"))
                .header("Authorization", "Bearer " + APIAuthentication.getAccessToken())
                .contentType("application/json");
    }

    public static Response getRequest(String endpoint) {
        return getRequestSpec()
                .get(endpoint);
    }

    public static Response postRequest(String endpoint, String body) {
        return getRequestSpec()
                .body(body)
                .post(endpoint);
    }

    public static Response putRequest(String endpoint, String body) {
        return getRequestSpec()
                .body(body)
                .put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        return getRequestSpec()
                .delete(endpoint);
    }
}
