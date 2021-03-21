package com.toilatester.core.service;

public interface Service<REQ, RES, TYPE> {

	public String serviceUrl();

	public RES dispatchService(REQ serviceRequest, TYPE serviceType);
}
