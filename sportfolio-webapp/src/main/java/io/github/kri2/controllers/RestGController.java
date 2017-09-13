package io.github.kri2.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.domain.RestGreeting;
import io.github.kri2.domain.User;
/**
 * 
 * @author kriz
 * if Jackson 2 is on the classpath, Spring's MappingJackson2HttpMessageConverter 
 * is automatically chosen to convert the RestGreeting instance to JSON
 */
@RestController // every method returns a domain object, instead of a view, it's a shorhand for @Controller and @ResponseBody
//@CrossOrigin//allows to test on localhost with no same origin problems
public class RestGController {
	private static final String template= "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	UserDao userDao;
	
	
	@RequestMapping("greeting")
	public RestGreeting greeting(@RequestParam(value="name", defaultValue="World") String name){
		return new RestGreeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@RequestMapping("userslist")
	public List<String> userList(){
		//Iterable<User> list = userDao.findAll();
		Iterable<User> source = userDao.findAll();
		List<String> names = new ArrayList<>();
		source.iterator().forEachRemaining(n->{names.add(n.getName());});
		//List<User> target = new ArrayList<>();
		//source.iterator().forEachRemaining(target::add);
		return names;
	}
}
