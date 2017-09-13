package io.github.kri2.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kri2.dataaccess.RestPortfolioItem;
import io.github.kri2.dataaccess.RestUser;
import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.domain.PortfolioItem;
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
	//Returns list of users
	@RequestMapping("userslist")
	public List<RestUser> userList(){
		//Iterable<User> list = userDao.findAll();
		Iterable<User> source = userDao.findAll();
		List<RestUser> names = new ArrayList<>();
		source.iterator().forEachRemaining(n->{names.add(new RestUser(n.getId(),n.getName()));});
		//List<User> target = new ArrayList<>();
		//source.iterator().forEachRemaining(target::add);
		return names;
	}
	//returns desired user's portfolio contents
	@RequestMapping("/{id}")
	public List<RestPortfolioItem> portfolioItemsList(@PathVariable Long id){
		User user = userDao.findById(id);
		List<RestPortfolioItem> rpi = new ArrayList<>();
		
		user.getPortfolioItems().forEach(n->{
			rpi.add(new RestPortfolioItem(n.getTicker(),n.getPrice(),n.getChangeP()));
		});
		return rpi;
	}
}
