package com.douglei.tools.instances.scanner;

import java.util.List;

import org.junit.Test;

import com.douglei.tools.instances.scanner.FileScanner;

public class FileScannerTest {
	
	@Test
	public void scan(){
		String basePath = "com/douglei/instances/scanner/tests";
		List<String> allFileNames = new FileScanner("xml").scan(basePath);
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
}
