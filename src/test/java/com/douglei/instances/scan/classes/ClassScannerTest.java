package com.douglei.instances.scan.classes;

import java.util.List;

import org.junit.Test;

import com.douglei.instances.scan.classes.ClassScanner;

public class ClassScannerTest {
	
	@Test
	public void scanClasses(){
		String basePackagePath = "com.douglei.instances.scan.classes.tests";
		List<String> allClassNames = ClassScanner.newInstance().scanClasses(basePackagePath);
		for (String className : allClassNames) {
			System.out.println(className);
		}
	}
}
