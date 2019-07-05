package com.douglei.tools.instances.scanner;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;

public class ClassScannerTest {
	
	String basePackagePath = "com.douglei.tools.instances.scanner.tests";
//	String basePackagePath = "com.douglei";
//	String basePackagePath = "org.slf4j";
	
	@Test
	public void scan(){
		List<String> allClassNames = new ClassScanner().scan(basePackagePath);
		for (String className : allClassNames) {
			System.out.println(className);
		}
	}
	
	@Test
	public void scan2() throws Exception {
		basePackagePath = basePackagePath.replace(".", "/");
		URL url = getClass().getClassLoader().getResource(basePackagePath);
		System.out.println(url.getProtocol());
		System.out.println(URLDecoder.decode(url.getFile(), "utf-8"));
		
		JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
		Enumeration<JarEntry> jes = jar.entries();
		
		while(jes.hasMoreElements()) {
			System.out.println(jes.nextElement().getName());
		}
		jar.close();
		
	}
}
