package com.douglei.instances.scanner;

import java.util.List;

import org.junit.Test;

import com.douglei.instances.scanner.ClassScanner;

public class ClassScannerTest {
	
	@Test
	public void scan(){
		String basePackagePath = "com.douglei.instances.scanner.tests";
		List<String> allClassNames = ClassScanner.newInstance().scan(basePackagePath);
		for (String className : allClassNames) {
			System.out.println(className);
		}
	}
}
