package stepdefinitions.APIsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utilities.ApiUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class SpotifyUserProfileStepDefs {

    private Response response;

    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = ApiUtils.getRequest(endpoint);
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain {string} with value {string}")
    public void theResponseShouldContainWithValue(String key, String value) {
        response.then().body(key, equalTo(value));
    }

    @Then("the response should contain {string} with value {int}")
    public void theResponseShouldContainWithValue(String key, int value) {
        response.then().body(key, equalTo(value));
    }
}
