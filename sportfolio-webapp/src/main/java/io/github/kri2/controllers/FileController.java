package io.github.kri2.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

public class FileController {
	/**
	 * Work in progress, For testing file loading
	 * Check whether the resources-folder is included in the deployment-assembly
	 */
	@Value(value="classpath:files/file_to_read.txt")
	private Resource moj_resource;
	@RequestMapping("/getfile")
	String getFile(){
		//first create and write file display message if succeeds
		File file;
		try {
			file = new ClassPathResource("test_file.txt").getFile();
			InputStream inputStream = new FileInputStream(file);
			System.out.println(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * another way to get file (use 
		 * import org.springframework.core.io.Resource and
		 * @Value(value="classpath:files/file_to_read.txt")
			private Resource moj_resource;
		 */
		try {
			InputStream inputStream2 = moj_resource.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * third way
		 */
		ClassLoader classLoader = getClass().getClassLoader();
		File file3 = new File(classLoader.getResource("test_file.txt").getFile());
		//read the file display its contents
		return "redirect:/";
		
		/**
		 * fourth way
		 */
		/*
		Resource resource = ClassPathResource("test_file.txt");
		InputStream inputStream4 = resource.getInputStream();
		*/
		/*
		ApplicationContext applicationContext;
		Resource resource5 = applicationContext.getResource("classpath:xyz.xml");
		*/
	}
}
