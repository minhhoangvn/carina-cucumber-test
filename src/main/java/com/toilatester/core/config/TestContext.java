package com.toilatester.core.config;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
	private static Map<String, Object> sharedData = new HashMap<>();

	private TestContext() {
	}

	public static TestContext getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public void setData(String key, Object value) {
		sharedData.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		return (T) sharedData.get(key);
	}

	private static class InstanceHolder {
		private static final TestContext INSTANCE = new TestContext();
	}

}
