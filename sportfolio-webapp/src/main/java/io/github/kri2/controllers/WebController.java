package io.github.kri2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.kri2.dataaccess.GoogleFinClient;
import io.github.kri2.dataaccess.TickerByUser;
import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.dataaccess.UserLogin;
import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.Stock;
import io.github.kri2.domain.User;
import io.github.kri2.service.GoogleFinService;

@Controller
public class WebController {
	@Autowired
	UserDao userDao;
	String nameGlobal;
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String displayForm(Model model){
		model.addAttribute("userForm", new UserLogin());
		return "welcome";
	}
	/*this was for login, when spring security hadn't been set up yet*/
	@RequestMapping(value="/start", method=RequestMethod.POST)
	public String processForm(@ModelAttribute("userForm") UserLogin userLogin, 
								Model model){
		System.out.println(userLogin.getName());
		nameGlobal = userLogin.getName();
		String result="";
		if( userDao.findByName(userLogin.getName()) != null){
			//result="Welcome back "+userLogin.getName();//when on the same page
			model.addAttribute("name", userLogin.getName());
			return "redirect:/portfolio";
		}
		else{
			result="sorry no such user found";
			model.addAttribute("result", result);
			return "welcome";
		}
	}
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
		return "welcome";
	}
	@RequestMapping(value="portfolio")
	public String showPortfolio(@ModelAttribute("name") String name, Model model){
		System.out.println("from showPortfolio: "+name);
		//show logged user name
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);

		User user = userDao.findByName(authName); 
		System.out.println("authname: "+authName);//tu jest problem authName jeszcze nie działa bo mój login nie jest połaczony ze spring security
		//need to log in two places before can use this
		
		if(user.getPortfolioItems()!=null){
			/* STEP 2 using tickers list from portfolio get data from google */
			user = GoogleFinService.updateData(user);
			model.addAttribute("portfolioItems",user.getPortfolioItems());
		}
		else{
			List<PortfolioItem> portfolioItems = new ArrayList<>(); 
			portfolioItems.add(new PortfolioItem("AAPL"));
			model.addAttribute("portfolioItems", portfolioItems);
		}
				 
		return "portfolio";
	}
	// adduser start
	@RequestMapping(value="adduser",method=RequestMethod.GET)
	public String addUserForm(Model model){
		model.addAttribute("userCredentials", new UserLogin());
		//shows who is currently logged in
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);
		return "adduser";
	}
	@RequestMapping(value="adduser",method=RequestMethod.POST)
	public String addUserFormHandle(@ModelAttribute("userCredentials") UserLogin userLogin){
		User user = new User();
		user.setName(userLogin.getName());
		user.setPassword(userLogin.getPassword());
		user.setEnabled(true);
		userDao.save(user);
		return "redirect:/portfolio";
	}
	//adduser end
	@RequestMapping(value="addportfolioitem", method=RequestMethod.GET)
	public String addItemForm(Model model){
		model.addAttribute("addItemForm", new TickerByUser());
		return "addportfolioitem";
	}
	@RequestMapping(value="addportfolioitem", method=RequestMethod.POST)
	public String addItemFormHandle(@ModelAttribute("addItemForm") TickerByUser tickerByUser){
		//PortfolioItem portfolioItem= new PortfolioItem();
		//portfolioItem.setTicker(tickerByUser.getTicker());
		//need to know which user was it for. How? Need to know which user is logged on
		//spring security needed? For now use clumsy solution
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		
		User user = userDao.findByName(authName);
		user.addPortfolioItem(tickerByUser.getTicker());
		userDao.save(user);//powinno być update raczej, ale jeszcze nie wiem jak
		return "redirect:/portfolio";
	}
}
