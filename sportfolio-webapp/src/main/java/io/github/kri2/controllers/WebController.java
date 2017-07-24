package io.github.kri2.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.User;
import io.github.kri2.domain.UserDAO;

@Controller
public class WebController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/welcome")
	public String serveWelcome(){
		Iterable<User> allUsers = userDAO.findAll();
		
		allUsers.forEach(var -> System.out.println(var.getName() + " "+var.getPassword()));
		
		User user = new User();
		user.setName("krzysztof");
		user.setPassword("pass");
		userDAO.save(user);
		
		return "welcome";
	}
	
	@RequestMapping("/showall")
	public String serveShowAll(Model model){
		Iterable<User> allUsers = userDAO.findAll();
		//allUsers.iterator().next();
		allUsers.forEach(var -> System.out.println(var.getName() + " "+var.getPassword()+" w portfolio jest: "+var.getPortfolioItems().get(0).getTicker()));
		//converting iterable to list (better for jstl i think)
		List<User> list = new ArrayList<User>();
		for(User item:allUsers){
			list.add(item);
		}
		//model.addAllAttributes(list);
		System.out.println(list.get(0).getName());
		
		model.addAttribute("all",list);
		return "showall";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.GET)
	public String serveAdd(Model model){//tutaj mala forma z wpisem nowego stock
		User user = new User();
		model.addAttribute("userForm",user);
		return "add";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAdd(@ModelAttribute("userForm") User userX, Model model){
		model.addAttribute("userForm",userX);
		
		System.out.println("zapisuje: "+userX.getName()+" "+userX.getPassword());
		userDAO.save(userX);	
		return "redirect:/showall";
	}
	@RequestMapping(value="/addstock", method=RequestMethod.GET)
	public String serveAddStock(Model model){
			userDAO.findAll().forEach(var -> System.out.println(var.getName()));;
			//model.addAttribute("usersList", );
		return "addstock";
	}
	@RequestMapping(value="/testjpa")
	public String serveTestJpa(){
		PortfolioItem item1= new PortfolioItem();
		item1.setPrice(200.0);
		item1.setTicker("XXXX");
		PortfolioItem item2 = new PortfolioItem();
		item2.setPrice(400.0);
		item2.setTicker("YYYY");
		List<PortfolioItem> itemsList = new ArrayList<PortfolioItem>();
		itemsList.add(item1);
		itemsList.add(item2);
		User userXY = new User();
		item1.setOwner(userXY);
		item2.setOwner(userXY);
		userXY.setPortfolioItems(itemsList);
		userXY.setName("Adam");
		userXY.setPassword("AdamsPassword");
		
		
		//System.out.println(userXY.getPortfolioItems().size());
		//System.out.println(userXY.getPortfolioItems().get(0).getTicker());
		//System.out.println(userXY.getPortfolioItems().get(1).getTicker());
		System.out.println(userXY.toString());
		//sprawdzone, user wygląda prawidłowo, teraz spróbować go dać do bazy:
		userDAO.save(userXY);
		System.out.println(userDAO.findById(2).toString());
		
		return "testjpa";
	}
}
