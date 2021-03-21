package com.toilatester.core.config;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjectFactory {
	private Injector inject;

	private GuiceInjectFactory() {
		this.inject = Guice.createInjector();
	}

	public static GuiceInjectFactory getIntance() {
		return InstanceHolder.INSTANCE;
	}

	public Injector getInject() {
		return this.inject;
	}

	public void injectToClass(Object object) {
		this.inject.injectMembers(object);
	}

	private static class InstanceHolder {
		private static final GuiceInjectFactory INSTANCE = new GuiceInjectFactory();
	}
}
