package com.github.jperucca.cucumber.version;

import com.github.jperucca.RestAssuredIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.is;

public class ReplaceVersionStep extends RestAssuredIntegrationTest {

    @When("^the client post /version (.+)$")
    public void the_client_POST_version(String versionNumber) {
        executePost("/version/{number}", versionNumber);
    }

    @Then("^the client receives on post status code of (\\d+)$")
    public void the_client_receives_on_post_status_code_of(int statusCode) {
        assertStatus(response, statusCode);
    }

    @And("^the client receives header location /version/(.+)$")
    public void the_client_receives_header_location(String version) {
        response.then().header("location", is("/version/" + version));
    }
}
