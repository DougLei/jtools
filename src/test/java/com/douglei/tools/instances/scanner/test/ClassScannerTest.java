package com.douglei.tools.instances.scanner.test;

import java.util.List;

import org.junit.Test;

import com.douglei.tools.scanner.impl.ClassScanner;

public class ClassScannerTest {
	
	@Test
	public void scan() throws ClassNotFoundException{
		List<String> classes = new ClassScanner().multiScan("com.douglei.tools.instances.scanner");
		for (String clazz : classes) {
			System.out.println(clazz);
		}
	}
	
	@Test
	public void scanJar() throws Exception {
		List<String> classes = new ClassScanner().scan("org.slf4j.event");
		for (String clazz : classes) {
			System.out.println(clazz);
		}
	}
}
