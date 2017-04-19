package com.github.jperucca;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberDemoApplication.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class RestAssuredIntegrationTest {

    private static final Logger logger = getLogger(RestAssuredIntegrationTest.class);
    @LocalServerPort
    private int serverPort;

    protected Response response;

    public void executeGet(String url, Object... params) {
        logger.info("GET {} {}", url, params);

        response = when().get(url, params);
    }

    public void executePost(String url, Object... params) {
        logger.info("POST {} {}", url, params);

        response = given().contentType(JSON).post(url, params);
    }

    public static void assertStatus(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    @PostConstruct
    public void initHttpClient() {
        RestAssured.port = serverPort;
    }

    @After
    public void teardown() {
        RestAssured.reset();
    }


}
