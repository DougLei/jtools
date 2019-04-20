package com.douglei.instances.scanner;

import java.util.List;

import org.junit.Test;

public class FileScannerTest {
	
	@Test
	public void scan(){
		String basePath = "com/douglei/instances/scanner/tests";
		List<String> allFileNames = FileScanner.newInstance().scan(basePath, "xml");
		System.out.println(allFileNames.size());
		for (String fileName : allFileNames) {
			System.out.println(fileName);
		}
	}
}
