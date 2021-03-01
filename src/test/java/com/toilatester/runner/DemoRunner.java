package com.toilatester.runner;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/demo.feature", glue = "com.toilatester.steps", plugin = {
		"pretty", "html:target/cucumber-core-test-report", "pretty:target/cucumber-core-test-report.txt",
		"json:target/cucumber-core-test-report.json", "junit:target/cucumber-core-test-report.xml" })
public class DemoRunner extends CucumberBaseTest {

}
