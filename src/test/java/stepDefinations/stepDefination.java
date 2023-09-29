package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDefination extends Utilities {
    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
   static String place_id;
    TestDataBuild data = new TestDataBuild();

        @Given("Add Place payload with {string} {string} {string}")
        public void addPlacePayloadWith(String name, String language, String address) throws IOException {
             res = given().spec(requestSpecification())
                     .body(data.addPlacePayLoad(name, language, address));

        }

        @When("user calls {string} with {string} http request")
        public void userCallsWithHttpRequest(String resource, String method) {
            APIResources resourceAPI = APIResources.valueOf(resource);
            System.out.println(resourceAPI.getResource());
            resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
            if(method.equalsIgnoreCase("POST"))
             response = res.log().all().when().post(resourceAPI.getResource());
            else if (method.equalsIgnoreCase("GET"))
                response = res.log().all().when().get(resourceAPI.getResource());
            else
                response = res.log().all().when().delete(resourceAPI.getResource());

        }
        @Then("the API call is success with status code {int}")
        public void the_api_call_is_success_with_status_code(Integer int1) {
           assertEquals(response.getStatusCode(),200);
        }
        @Then("{string} in response body is {string}")
        public void in_response_body_is(String keyValue, String ExpectedValue) {
           assertEquals(getJsonPath(response, keyValue), ExpectedValue);

        }

         @Then("verify place_Id created maps to {string} using {string}")
         public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
            //prepare request spec
             place_id = getJsonPath(response,"place_id");
             res = given().spec(requestSpecification()).queryParam("place_id", place_id);
             userCallsWithHttpRequest(resource,"GET");
             String actualName = getJsonPath(response,"name");
             assertEquals(actualName, expectedName);
        }

    @Given("DeletePlace Payload")
    public void deletePlacePayload() throws IOException {
        res = given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
    }

}
