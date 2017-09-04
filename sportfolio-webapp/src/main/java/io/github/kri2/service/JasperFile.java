package io.github.kri2.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

public class JasperFile {
	public static InputStream getStream(String path) throws FileNotFoundException{
		File file = null;
		try {
			file = new ClassPathResource(path).getFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file.getParent());
		return new FileInputStream(file);
	}
}

