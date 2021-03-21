package com.toilatester.steps;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.toilatester.apis.SampleService;
import com.toilatester.core.listener.LogReportListener;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ReportsTestWithParameters {
	private int itemsCount;

	@Given("I have {int} {string} in my pocket")
	public void iHaveNumberItemInMyPocket(int number, String item) {
		itemsCount = number;
		LogReportListener.dispatchLog(String.format("I have {%d} {%s} in my pocket", number, item), "INFO");

	}

	@When("^I eat one$")
	public void iEatOne() {
		itemsCount -= 1;
		LogReportListener.dispatchLog("I eat one", "INFO");
	}

	@Then("I have {int} in my pocket")
	public void iHaveResultInMyPocket(int result) {
		Assert.assertEquals(result, itemsCount);
		LogReportListener.dispatchLog(String.format("I have {%d} in my pocket", result), "INFO");
	}

	@Then("I have data in my pocket$")
	public void i_have_result_in_my_pocket(io.cucumber.datatable.DataTable dataTable) throws JsonProcessingException {
		SampleService service = new SampleService();
		Response res = service.echoPost();
		System.err.println(res.getBody().asString());
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

	}
}
