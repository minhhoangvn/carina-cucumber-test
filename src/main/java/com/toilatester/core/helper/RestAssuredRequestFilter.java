package com.toilatester.core.helper;

import java.nio.charset.Charset;
import java.util.HashSet;

import com.epam.reportportal.listeners.LogLevel;
import com.toilatester.core.listener.LogReportListener;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {

	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		String uri = requestSpec.getURI();
		uri = UrlDecoder.urlDecode(uri,
				Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);

		String requestLog = RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, LogDetail.ALL,
				new HashSet<>(), System.out, true);
		LogReportListener.dispatchLog(requestLog, LogLevel.INFO.toString());
		return ctx.next(requestSpec, responseSpec);
	}

}
