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

@Controller
public class WebController {
	@Autowired
	UserDao userDao;
	String nameGlobal;
	@RequestMapping("/makeuser")
	public String serveMake(){
		User user = new User();
		user.setName("Krzysztof");
		user.setPassword("hasłoKrzyśka");
		user.addPortfolioItem("THRM");
		user.addPortfolioItem("DAL");
		userDao.save(user);
		return "welcome";
	}
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String displayForm(Model model){
		model.addAttribute("userForm", new UserLogin());
		Boolean addUserButtonState = false;
		model.addAttribute("addUserButton", addUserButtonState);
		return "welcome";
	}
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
		/*display login page based on user name provided load his portfolio */
		/* STEP 1 get portfolio data from db for particular user or create new user*/
		
		//creating dummy user
		User user = new User();
		user.setName("Krzysztof");
		//provide user to database check if present if yes load porfolioItems
		user = userDao.findByName(user.getName()); //now user data is loaded from database
		
		/* STEP 2 using tickers list from portfolio get data from google */
		for(PortfolioItem item : user.getPortfolioItems()){
			String currentTicker = item.getTicker();
			System.out.println("Getting info about "+currentTicker);
			
			GoogleFinClient googleFinClient = new GoogleFinClient();
			Stock stock = googleFinClient.getStock(currentTicker);
			System.out.println("Result: "+stock.toString());
			//tutaj zrobić mapping do stock
			//dalej przypisać ściągnięty stock do portfolioitem
			item.setPrice( Double.valueOf(stock.getPrice()) );
			item.setChangeP( Double.valueOf(stock.getCp()) );
		}
		//teraz można to wyświetlić a także wpisać uaktualnione dane do bazy danych
		
		// zapis uaktualnionych danych
		userDao.save(user);
		
		//now we have list of stocks same to database one, but downloaded from google api
		//we can display them and update portfolio item
		//now need to prepare some kind of structure which i can pass to the model and jsp 
		
		/* STEP 3 Display portfolio summary */
		/* add ability to add/remove stocks from portfolio */
		/* add ability to create/delete user/portfolio */
		return "welcome";
	}
	@RequestMapping(value="portfolio")
	public String showPortfolio(@ModelAttribute("name") String name, Model model){
		System.out.println("from showPortfolio: "+name);
		//show logged user name
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authName = auth.getName();
		model.addAttribute("whoIsLoggedIn", authName);

		//User user = userDao.findByName(name);
		User user = userDao.findByName(authName);
		if(user.getPortfolioItems()!=null){
			model.addAttribute("portfolioItems",user.getPortfolioItems());
		}
		else{
			List<PortfolioItem> portfolioItems = new ArrayList<>(); 
			portfolioItems.add(new PortfolioItem("DUMMY"));
			model.addAttribute("portfolioItems", portfolioItems);
		}
			
			 
		return "portfolio";
	}
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
