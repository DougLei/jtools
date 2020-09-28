package com.douglei.tools.instances.scanner;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.douglei.tools.instances.scanner.impl.ResourceScanner;

public class ResourceScannerTest {
	
	@Test
	public void scan(){
//		String basePath = "com/douglei/tools/instances/scanner";
		String basePath = "com\\douglei\\tools\\instances\\scanner";
		List<String> allFileNames = new ResourceScanner("xml").multiScan(basePath, "com/douglei");
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
			System.out.println(new File(fileName).exists());
		}
	}
	
	@Test
	public void scanJAR(){
		String basePath = "META-INF\\maven\\";
		List<String> allFileNames = new ResourceScanner("properties").scan(true, basePath);
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
}
