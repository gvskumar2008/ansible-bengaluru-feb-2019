package org.tektutor;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.PendingException;

import static org.junit.Assert.*;

public class Steps {
	private App app;
	private String actualResponse;

	@Given("there is an class by name App")
	public void there_is_an_class_by_name_App() {
		app = new App();
	}

	@When("I invoke sayHello method")
	public void i_invoke_sayHello_method() {
		actualResponse = app.sayHello();
	}

	@Then("I expect the response to be {string}")
	public void i_expect_the_response_to_be(String expectedResponse) {
		assertEquals ( expectedResponse, actualResponse );
	}
}
