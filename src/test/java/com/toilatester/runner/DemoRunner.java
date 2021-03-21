package com.toilatester.runner;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "com.toilatester.steps", plugin = {
		"html:target/cucumber-core-test-report", "pretty:target/cucumber-core-test-report.txt",
		"json:target/cucumber-core-test-report.json", "junit:target/cucumber-core-test-report.xml",
		"com.epam.reportportal.cucumber.StepReporter" })
public class DemoRunner extends CucumberBaseTest {

}
