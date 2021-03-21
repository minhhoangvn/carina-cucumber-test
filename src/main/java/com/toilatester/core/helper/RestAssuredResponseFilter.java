package com.toilatester.core.helper;

import java.util.HashSet;

import com.epam.reportportal.listeners.LogLevel;
import com.toilatester.core.listener.LogReportListener;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredResponseFilter implements Filter {
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		Response response = ctx.next(requestSpec, responseSpec);
		String responseLog = ResponsePrinter.print(response, response, System.out, LogDetail.ALL, true,
				new HashSet<>());
		LogReportListener.dispatchLog(responseLog, LogLevel.INFO.toString());
		return response;
	}
}
