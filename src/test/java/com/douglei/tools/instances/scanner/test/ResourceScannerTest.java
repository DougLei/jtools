package com.douglei.tools.instances.scanner.test;

import java.util.List;

import org.junit.Test;

import com.douglei.tools.scanner.impl.ResourceScanner;

public class ResourceScannerTest {
	
	@Test
	public void scan(){
		List<String> allFileNames = new ResourceScanner("class").multiScan("com/douglei/tools/instances/scanner");
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
	
	@Test
	public void scanJAR(){
		String basePath = "META-INF\\maven\\";
		List<String> allFileNames = new ResourceScanner("xml").scan(true, basePath);
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
}
