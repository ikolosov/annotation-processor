package com.ikolosov.apt.targetapp;

import com.ikolosov.apt.annotation.CustomAnnotation;

/**
 * @author ikolosov
 */
@CustomAnnotation
public class AptPoc {

	@CustomAnnotation
	public String concat(String text, Integer number) {
		String result = text + number;
		System.out.println("concatenation result is: " + result);
		return result;
	}

	@CustomAnnotation
	public void print(String text) {
		System.out.println("text to print: " + text);
	}
}
