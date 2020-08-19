package com.spacey.effective._03;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AccessibleObject;

public class AccessibleObjectDemo {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {
		AccessibleObject sampleField = SampleClass.class.getDeclaredField("sampleField");
		System.out.println("sampleField.isAccessible: " + sampleField.isAccessible());
		sampleField.setAccessible(true);
		System.out.println("sampleField.isAccessible: " + sampleField.isAccessible());
	}
}

@CustomAnnotation(name = "SampleClass", value = "Sample Class Annotation")
class SampleClass {
	private String sampleField;

	@CustomAnnotation(name = "sampleMethod", value = "Sample Method Annotation")
	public String sampleMethod() {
		return "sample";
	}

	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {
	public String name();

	public String value();
}