package com.toilatester.runner;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/TwoScenarioOutlineParameters.feature", glue = "com.toilatester.steps", plugin = {
		"html:target/cucumber-core-test-report", "pretty:target/cucumber-core-test-report.txt",
		"json:target/cucumber-core-test-report.json", "junit:target/cucumber-core-test-report.xml",
		"com.toilatester.core.listener.LogReportListener" })
public class DemoRunner extends CucumberBaseTest {

}
