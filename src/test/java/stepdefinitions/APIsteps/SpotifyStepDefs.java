package stepdefinitions.APIsteps;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.PlaylistRequest;
import utilities.ApiUtils;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpotifyStepDefs {

    private Response response;
    private PlaylistRequest playlistRequest;
    private static String playlistId;

    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = ApiUtils.getRequest(endpoint);
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        assertNotNull("Response is null!", response); // Check if response is null
        assertEquals("Status code mismatch!", statusCode, response.getStatusCode());
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain {string} with value {string}")
    public void theResponseShouldContainWithValue(String key, String value) {
        Object responseValue = response.jsonPath().get(key);
        if (responseValue instanceof Boolean) {
            boolean expectedValue = Boolean.parseBoolean(value);
            response.then().body(key, equalTo(expectedValue));
        }
        else if (responseValue instanceof String) {
            response.then().body(key, equalTo(value));
        }
        else {
            throw new AssertionError("Unexpected type for key " + key);
        }
    }
    @Then("the response should contain {string} with value {int}")
    public void theResponseShouldContainWithValue(String key, int value) {
        response.then().body(key, equalTo(value));
    }

    @Given("I create request body with given : {string}, {string}, {string}")
    public void i_create_request_body_with_given(String name, String description, String isPublic) {
        boolean publicFlag = Boolean.parseBoolean(isPublic);
        playlistRequest = new PlaylistRequest(name, description, publicFlag);
        Gson gson = new Gson();
        String requestBody = gson.toJson(playlistRequest);
        System.out.println(requestBody);
    }

    @When("I send a POST request to {string} with user ID {string} and the playlist details")
    public void i_send_a_post_request_to_with_user_id_and_the_playlist_details(String endpoint, String userId) {
        String finalEndpoint = endpoint.replace("{user_id}", userId);
        response = ApiUtils.postRequest(finalEndpoint, playlistRequest);
        playlistId = response.jsonPath().getString("id");
        assertNotNull(playlistId);
    }

    @Given("I send a GET request to {string} with the stored playlist ID")
    public void i_send_a_get_request_to_with_the_stored_playlist_id(String string) {
        String finalEndpoint = string.replace("{playlist_id}", playlistId);;
        response = ApiUtils.getRequest(finalEndpoint);
        response.prettyPrint();

    }

    @When("I send a PUT request to {string} with stored playlist id and the playlist details")
    public void i_send_a_put_request_to_with_stored_playlist_id_and_the_playlist_details(String string) {
        String finalEndpoint = string.replace("{playlist_id}", playlistId);
        System.out.println(finalEndpoint);
        System.out.println(playlistRequest);
        response = ApiUtils.putRequest(finalEndpoint, playlistRequest);
        response.prettyPrint();
    }


}
