package com.toilatester.apis;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toilatester.core.service.rest.BaseRestfulService;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class SampleService extends BaseRestfulService {

	public SampleService() {
		this.setHost("postman-echo.com");
		this.setPort(443);
		this.setPath("post");
	}

	public Response echoPost() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> jsonData = new HashMap<>();
		jsonData.put("data", "This is sample data");
		return this.dispatchService(this.getDefaultRequestSpec().accept(ContentType.JSON)
				.header("Accept", ContentType.JSON).body(objectMapper.writeValueAsString(jsonData)), Method.POST);
	}
}
