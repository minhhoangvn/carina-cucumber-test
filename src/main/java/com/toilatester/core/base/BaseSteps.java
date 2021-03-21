package com.toilatester.core.base;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toilatester.core.config.GuiceInjectFactory;
import com.toilatester.core.config.TestContext;

public abstract class BaseSteps {

	private Supplier<String> getClassName = () -> this.getClass().getSimpleName();
	
	protected final Logger logger = LoggerFactory.getLogger(getClassName.get());

	protected TestContext testContext;

	protected BaseSteps() {
		GuiceInjectFactory.getIntance().injectToClass(this);
		this.testContext = TestContext.getInstance();
	}
}
