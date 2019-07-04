package com.douglei.tools.instances.scanner;

import java.util.List;

import org.junit.Test;

import com.douglei.tools.instances.scanner.FileScanner;

public class FileScannerTest {
	
	@Test
	public void scan(){
		String basePath = "com/douglei/tools/instances/scanner";
		List<String> allFileNames = new FileScanner("xml").scan(basePath);
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
	
	@Test
	public void scanJAR(){
		String basePath = "META-INF/maven/ch.qos.logback/";
		List<String> allFileNames = new FileScanner("properties").scan(true, basePath);
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
}
