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

import io.github.kri2.dataaccess.TickerByUser;
import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.User;
import io.github.kri2.service.GoogleFinService;

@Controller
public class PortfolioController {
	@Autowired
	UserDao userDao;
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
		
		if(user!=null && user.getPortfolioItems()!=null){
			/* STEP 2 using tickers list from portfolio get data from google */
			user = GoogleFinService.updateData(user);
			model.addAttribute("portfolioItems",user.getPortfolioItems());
		}
		else{
			System.out.println("no user account found");
			List<PortfolioItem> portfolioItems = new ArrayList<>(); 
			portfolioItems.add(new PortfolioItem("AAPL"));
			model.addAttribute("portfolioItems", portfolioItems);
		}
				 
		return "portfolio";
	}
	
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
	@RequestMapping(value="remove")
	public String remove(){
		return "portfolio";
	}
}
