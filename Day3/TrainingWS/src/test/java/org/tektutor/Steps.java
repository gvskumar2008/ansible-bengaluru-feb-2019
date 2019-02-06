package org.tektutor;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.*;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Steps {
	private String endpointURL;
	private RequestSpecification request;
	private Response response;
	private int expectedResponseCode, actualResponseCode;
	
	private int recordCount;

	private int id;
	private String name;
	private String duration;

	private String expectedResponseData, actualResponseData;

	@BeforeClass
	public static void beforeClass() {
		System.out.println ( "Before Class");
	}

	@Before
	public void beforeMethod() {
		System.out.println ( "Before Method" );
	}

	@After
	public void afterMethod() {
		System.out.println ( "After Method" );
	}

	@AfterClass
	public static void afterClass() {
		System.out.println ( "After Class");
	}

	@Given("the Training REST API is hosted at endpoint {string}")
	public void the_Training_REST_API_is_hostend_at_endpoint(String endpointURL ) {
		this.endpointURL = endpointURL;
	}

	@When("I invoke the REST API as GET method")
	public void i_invoke_the_REST_API() {
		RestAssured.baseURI = this.endpointURL;
		this.request = RestAssured.given();

		this.request.header( "Content-Type", "application/json" );
		this.response = request.get();

		this.actualResponseCode = this.response.getStatusCode();
	}

	@Then("I expect a status code {string}")
	public void i_expect_a_status_code(String strExpectedResponseCode) {
		this.expectedResponseCode = Integer.parseInt(strExpectedResponseCode);
		assertEquals ( this.expectedResponseCode, this.actualResponseCode );
	}

	@Then("I expect all the existing training records as response")
	public void i_expect_all_the_existing_training_records_as_response() {
		Training[] listOfTrainings = response.getBody().as(Training[].class);
		assertTrue( listOfTrainings.length != this.recordCount );

		for ( int index=0; index < listOfTrainings.length; ++index ) {

			assertEquals ( index+1, listOfTrainings[index].getId() );
		        assertFalse  ( listOfTrainings[index].getName().isEmpty() );	
		        assertFalse  ( listOfTrainings[index].getDuration().isEmpty() );	
		}
	}

	@When("I invoke the REST API with id {string} as GET method")
	public void i_invoke_the_REST_API_with_id(String strId) {
		this.endpointURL = this.endpointURL + "/" + strId;

		RestAssured.baseURI = this.endpointURL;
		this.request = RestAssured.given();

		this.request.header( "Content-Type", "application/json" );
		this.response = request.get();

		this.actualResponseCode = this.response.getStatusCode();
	}

	@Then("I expect a single training record that matches id {string} as response")
	public void i_expect_a_single_training_record_that_matches_id_as_response(String strId) {
		Training training = response.getBody().as(Training.class);

		assertEquals ( Integer.parseInt(strId), training.getId() );
	        assertFalse  ( training.getName().isEmpty() );	
	        assertFalse  ( training.getDuration().isEmpty() );	
	}

	@Given("the training field details are as below")
	public void the_training_field_details_are_as_below(DataTable dataTable) {

	    List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

	    id = Integer.parseInt( data.get(0).get("id") );
	    name = data.get(0).get("name");
	    duration = data.get(0).get("duration");

	}

	@When("I invoke the REST API as PUT method")
	public void i_invoke_the_REST_API_as_PUT_method() {
	    RestAssured.baseURI = this.endpointURL;
	    this.request = RestAssured.given();

	    JSONObject requestParams = new JSONObject();
	    requestParams.put ("id", id );
	    requestParams.put ("name", name );
	    requestParams.put ("duration", duration );

	    request.header("Content-Type", "application/json");
	    request.body(requestParams.toJSONString());

	    this.response = request.put();

	    this.actualResponseCode = this.response.getStatusCode();
	}

	@When("I invoke the REST API as POST method")
	public void i_invoke_the_REST_API_as_POST_method() {
	    RestAssured.baseURI = this.endpointURL;
	    this.request = RestAssured.given();

	    JSONObject requestParams = new JSONObject();
	    requestParams.put ("id", id );
	    requestParams.put ("name", name );
	    requestParams.put ("duration", duration );

	    request.header("Content-Type", "application/json");
	    request.body(requestParams.toJSONString());

	    this.response = request.post();

	    this.actualResponseCode = this.response.getStatusCode();
	}

	@Then("I expect the record count as non-zero")
	public void i_expect_the_record_count_as_non_zero() {
		//Should check it is greater than zero
		this.recordCount = 0;
	}

	@When("I invoke the REST API with id {string} as DELETE method")
	public void i_invoke_the_REST_API_with_id_as_DELETE_method(String strId) {

	    this.endpointURL = this.endpointURL + "/" + strId;	

	    RestAssured.baseURI = this.endpointURL;
	    this.request = RestAssured.given();

	    request.header("Content-Type", "application/json");

	    this.response = request.delete();

	    this.actualResponseCode = this.response.getStatusCode();

	}

}
