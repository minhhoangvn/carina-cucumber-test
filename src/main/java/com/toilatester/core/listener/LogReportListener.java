package com.toilatester.core.listener;

import java.io.File;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.service.ReportPortal;

import io.cucumber.core.gherkin.DataTableArgument;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Step;
import io.cucumber.plugin.event.TestCase;

public class LogReportListener extends ScenarioReporter {

	@Override
	protected String getDescription(@Nonnull TestCase testCase, @Nonnull URI uri) {
		String markDownTemplate = "# File Location:\n%s\n# Scenario:\n%s";
		return String.format(markDownTemplate, uri.toString(), buiildScenarioDescription(testCase));
	}

	private String buiildScenarioDescription(TestCase testCase) {
		StringBuilder scenario = new StringBuilder();
		testCase.getTestSteps().forEach(testStep -> {
			PickleStepTestStep step = (PickleStepTestStep) testStep;
			Step featureStep = step.getStep();
			DataTableArgument stepArgument = (DataTableArgument) featureStep.getArgument();
			scenario.append(featureStep.getText() + "\n");
			if (stepArgument != null)
				scenario.append(buildTableDesciption(stepArgument.cells()) + "\n");

		});
		return scenario.toString();
	}

	private String buildTableDesciption(List<List<String>> cells) {
		StringBuilder dataTableArgument = new StringBuilder();
		for (List<String> columns : cells) {
			dataTableArgument.append("|");
			for (String cell : columns) {
				dataTableArgument.append(cell);
				dataTableArgument.append("|");
			}
			dataTableArgument.append("\n");
		}
		return dataTableArgument.toString();
	}

	public static void dispatchLog(String logMessage, String logLevel) {
		ReportPortal.emitLog(logMessage, logLevel, generateTimeStamp());
	}

	public static void dispatchLogWithAttachment(String logMessage, String logLevel, File file) {
		ReportPortal.emitLog(logMessage, logLevel, generateTimeStamp(), file);
	}

	private static Date generateTimeStamp() {
		return Calendar.getInstance().getTime();
	}
}
