package com.github.jperucca.cucumber.version;

import com.github.jperucca.RestAssuredIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.is;

public class GetVersionStep extends RestAssuredIntegrationTest {

    @When("^the client calls /version$")
    public void the_client_calls_GET_version() {
        executeGet("/version");
    }

    @Then("^the client receives on get status code of (\\d+)$")
    public void the_client_receives_on_get_status_code_of(int statusCode) {
        assertStatus(response, statusCode);
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) {
        response.then().body("number", is(version));
    }
}