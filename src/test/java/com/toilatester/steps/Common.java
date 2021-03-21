package com.toilatester.steps;

import org.testng.Assert;

import com.google.inject.Inject;
import com.toilatester.core.base.BaseSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Common extends BaseSteps {
	@Inject
	Dummy dummy;

	@Given("today is {string}")
	public void todayIsSunday(String actuatDate) {
		dummy.printHello(actuatDate);
		this.testContext.setData("actualToday", actuatDate);
		logger.info("Test choi coi send log len ko bro step given");
	}

	@When("I ask whether it's {string} yet")
	public void askItFriday(String expectedDate) {
		this.testContext.setData("expectedDate", expectedDate);
		logger.info("Test choi coi send log len ko bro step when");
	}

	@Then("I should be told {string}")
	public void verifyDate(String expectedDate) {
		String actualDate = this.testContext.getData("expectedDate");
		Assert.assertEquals(actualDate, expectedDate);
		logger.info("Test choi coi send log len ko bro step assert");
	}
}
