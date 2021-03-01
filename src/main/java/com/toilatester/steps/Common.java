package com.toilatester.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Common {
	@Given("today is Sunday")
	public void todayIsSunday() {

	}

	@When("I ask whether it's Friday yet")
	public void askItFriday() {

	}

	@Then("I should be told {string}")
	public void i_should_be_told(String string) {
		System.err.println(string);
	}
}
