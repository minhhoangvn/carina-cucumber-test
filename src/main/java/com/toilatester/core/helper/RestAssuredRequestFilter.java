package com.toilatester.core.helper;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.filter.log.LogDetail.STATUS;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.epam.reportportal.listeners.LogLevel;
import com.toilatester.core.listener.LogReportListener;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {
	private static final boolean SHOW_URL_ENCODED_URI = true;
	private final LogDetail logDetail;
	private final PrintStream stream;
	private final boolean shouldPrettyPrint;
	private final boolean showUrlEncodedUri;
	private final Set<String> blacklistedHeaders;

	/**
	 * Logs to System.out
	 */
	public RestAssuredRequestFilter() {
		this(ALL, System.out);
	}

	/**
	 * Logs with a specific detail to System.out
	 *
	 * @param logDetail The log detail
	 */
	public RestAssuredRequestFilter(LogDetail logDetail) {
		this(logDetail, System.out);
	}

	/**
	 * Logs everyting to the specified printstream.
	 *
	 * @param printStream The stream to log to.
	 */
	public RestAssuredRequestFilter(PrintStream printStream) {
		this(ALL, printStream);
	}

	/**
	 * Instantiate a logger using a specific print stream and a specific log detail.
	 * Pretty-printing will be enabled if possible.
	 *
	 * @param logDetail The log detail
	 * @param stream    The stream to log to.
	 */
	public RestAssuredRequestFilter(LogDetail logDetail, PrintStream stream) {
		this(logDetail, true, stream);
	}

	/**
	 * Instantiate a logger using a specific print stream and a specific log detail
	 *
	 * @param logDetail         The log detail
	 * @param shouldPrettyPrint <code>true</code> if pretty-printing of the body
	 *                          should occur.
	 * @param stream            The stream to log to.
	 */
	public RestAssuredRequestFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream) {
		this(logDetail, shouldPrettyPrint, stream, SHOW_URL_ENCODED_URI);
	}

	/**
	 * Instantiate a logger using a specific print stream and a specific log detail
	 *
	 * @param logDetail         The log detail
	 * @param shouldPrettyPrint <code>true</code> if pretty-printing of the body
	 *                          should occur.
	 * @param stream            The stream to log to.
	 * @param showUrlEncodedUri Whether or not to show the request URI as url
	 *                          encoded
	 */
	public RestAssuredRequestFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream,
			boolean showUrlEncodedUri) {
		this(logDetail, shouldPrettyPrint, stream, showUrlEncodedUri, Collections.emptySet());
	}

	/**
	 * Instantiate a logger using a specific print stream and a specific log detail
	 *
	 * @param logDetail         The log detail
	 * @param shouldPrettyPrint <code>true</code> if pretty-printing of the body
	 *                          should occur.
	 * @param stream            The stream to log to.
	 * @param showUrlEncodedUri Whether or not to show the request URI as url
	 *                          encoded
	 */
	public RestAssuredRequestFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream,
			boolean showUrlEncodedUri, Set<String> blacklistedHeaders) {
		Validate.notNull(stream, "Print stream cannot be null");
		Validate.notNull(blacklistedHeaders, "Blacklisted headers cannot be null");
		Validate.notNull(logDetail, "Log details cannot be null");
		if (logDetail == STATUS) {
			throw new IllegalArgumentException(
					String.format("%s is not a valid %s for a request.", STATUS, LogDetail.class.getSimpleName()));
		}
		this.stream = stream;
		this.logDetail = logDetail;
		this.blacklistedHeaders = new HashSet<>(blacklistedHeaders);
		this.shouldPrettyPrint = shouldPrettyPrint;
		this.showUrlEncodedUri = showUrlEncodedUri;
	}

	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		String uri = requestSpec.getURI();
		if (!showUrlEncodedUri) {
			uri = UrlDecoder.urlDecode(uri,
					Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
		}

		String requestLog = RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, logDetail,
				blacklistedHeaders, stream, shouldPrettyPrint);
		LogReportListener.dispatchLog(requestLog, LogLevel.INFO.toString());
		return ctx.next(requestSpec, responseSpec);
	}

	/**
	 * Syntactic sugar for doing <code>new RequestLoggingFilter(stream)</code>
	 *
	 * @param stream The stream to log the request to.
	 * @return A new instance of {@link RequestLoggingFilter}.
	 */
	public static RequestLoggingFilter logRequestTo(PrintStream stream) {
		return new RequestLoggingFilter(stream);
	}

}
