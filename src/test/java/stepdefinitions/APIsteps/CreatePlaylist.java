package stepdefinitions.APIsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.CreatePlaylistRequestBody;
import utilities.ApiUtils;

public class CreatePlaylist {
    private Response response;

    @Given("I send a POST request to given endpoint with the request body:")
    public void i_send_a_post_request_to_given_endpoint_with_the_request_body(io.cucumber.datatable.DataTable dataTable) {
        CreatePlaylistRequestBody requestBody = new CreatePlaylistRequestBody();

        response = ApiUtils.postRequest(dataTable)
    }
    }





}
