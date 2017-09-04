package io.github.kri2.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kri2.dataaccess.RestGreeting;
/**
 * 
 * @author kriz
 * if Jackson 2 is on the classpath, Spring's MappingJackson2HttpMessageConverter 
 * is automatically chosen to convert the RestGreeting instance to JSON
 */
@RestController // every method returns a domain object, instead of a view, it's a shorhand for @Controller and @ResponseBody
public class RestGController {
	private static final String template= "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("greeting")
	public RestGreeting greeting(@RequestParam(value="name", defaultValue="World") String name){
		return new RestGreeting(counter.incrementAndGet(), String.format(template, name));
		
	}
}
