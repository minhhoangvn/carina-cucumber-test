package com.toilatester.steps;

import java.util.Calendar;

import org.testng.Assert;

import com.toilatester.core.listener.LogReportListener;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportsTestWithParameters {
	private int itemsCount;

	@SuppressWarnings("static-access")
	@Given("I have {int} {string} in my pocket")
	public void iHaveNumberItemInMyPocket(int number, String item) {
		itemsCount = number;
		LogReportListener.getReportPortal().emitLog(String.format("I have {%d} {%s} in my pocket", number, item),
				"INFO", Calendar.getInstance().getTime());

	}

	@SuppressWarnings("static-access")
	@When("^I eat one$")
	public void iEatOne() {
		itemsCount -= 1;
		LogReportListener.getReportPortal().emitLog("I eat one", "INFO", Calendar.getInstance().getTime());
	}

	@SuppressWarnings("static-access")
	@Then("I have {int} in my pocket")
	public void iHaveResultInMyPocket(int result) {
		Assert.assertEquals(result, itemsCount);
		LogReportListener.getReportPortal().emitLog(String.format("I have {%d} in my pocket", result), "INFO",
				Calendar.getInstance().getTime());
	}
}
