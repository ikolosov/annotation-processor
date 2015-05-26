package com.ikolosov.apt.targetapp;

import org.junit.Test;

public class AptPocTest {

	@Test
	public void invokeOriginal() {
		AptPoc aptPoc = new AptPoc();
		aptPoc.print("hello");
		aptPoc.concat("a", 1);
	}

	@Test
	public void invokeWrapper() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String wrapperClassName = "com.ikolosov.apt.targetapp.AptPocWrapper";
		AptPoc aptPocWrapper = (AptPoc) Class.forName(wrapperClassName).newInstance();
		aptPocWrapper.print("hello");
		aptPocWrapper.concat("a", 1);
	}
}
