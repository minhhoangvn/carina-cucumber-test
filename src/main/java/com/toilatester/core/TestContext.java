package com.toilatester.core;

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

	public Object getData(String key) {
		return sharedData.get(key);
	}

	private static class InstanceHolder {
		private static final TestContext INSTANCE = new TestContext();
	}

}
