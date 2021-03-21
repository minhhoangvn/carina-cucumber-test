package com.toilatester.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportsTestWithParameters {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsTestWithParameters.class);
	private int itemsCount;

	@Given("I have {int} {string} in my pocket")
	public void iHaveNumberItemInMyPocket(int number, String item) {
		itemsCount = number;
		LOGGER.info("I have {} {} in my pocket", number, item);

	}

	@When("^I eat one$")
	public void iEatOne() {
		itemsCount -= 1;
		LOGGER.info("I eat one");
	}

	@Then("I have {int} in my pocket")
	public void iHaveResultInMyPocket(int result) {
		Assert.assertEquals(result, itemsCount);
		LOGGER.info("I have {} in my pocket", result);
	}
}
