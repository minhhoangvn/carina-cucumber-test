package com.toilatester.core.service;

public interface Service<REQ, RES, TYPE> {

	public String getServiceUrl();

	public RES dispatchService(REQ serviceRequest, TYPE serviceType);
}
