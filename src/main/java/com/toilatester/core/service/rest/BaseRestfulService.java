package com.toilatester.core.service.rest;

import com.toilatester.core.execption.TestRunTimeException;
import com.toilatester.core.helper.RestAssuredRequestFilter;
import com.toilatester.core.helper.RestAssuredResponseFilter;
import com.toilatester.core.service.Service;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseRestfulService implements Service<RequestSpecification, Response, Method> {

	private String host;
	private String path;
	private int port = 443;

	protected void setHost(String host) {
		this.host = host;
	}

	protected void setPath(String path) {
		if (path.startsWith("/"))
			this.path = path;
		else
			this.path = String.format("/%s", path);
	}

	protected void setPort(int port) {
		this.port = port;
	}

	protected RequestSpecification getDefaultRequestSpec() {
		return RestAssured.given().baseUri(getServiceUrl());
	}

	@Override
	public String getServiceUrl() {
		if (port == 443)
			return String.format("https://%s:%d%s", host, port, path);
		return String.format("http://%s%d%s", host, port, path);
	}

	@Override
	public Response dispatchService(RequestSpecification serviceRequest, Method serviceType) {
		switch (serviceType) {
		case GET:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().get().andReturn();
		case POST:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().post().andReturn();
		case PUT:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().put().andReturn();
		case DELETE:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().delete().andReturn();
		case OPTIONS:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().options().andReturn();
		case HEAD:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().head().andReturn();
		case PATCH:
			return serviceRequest.filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
					.relaxedHTTPSValidation().patch().andReturn();
		case TRACE:
		default:
			throw new TestRunTimeException("Request Method Does Not Support" + serviceType.name());
		}
	}

}
