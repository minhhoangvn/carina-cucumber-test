package com.toilatester.core.listener;

import com.epam.reportportal.cucumber.StepReporter;
import com.epam.reportportal.service.ReportPortal;

public class StepReportListener extends StepReporter {
	public static final ThreadLocal<ReportPortal> RP = new ThreadLocal<>();

	@Override
	protected ReportPortal buildReportPortal() {
		return RP.get();
	}

	public static void addReportPortal(ReportPortal rp) {
		setReportPortal(rp);
	}
}
