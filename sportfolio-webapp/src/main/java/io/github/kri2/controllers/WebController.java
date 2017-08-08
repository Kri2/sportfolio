package io.github.kri2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.kri2.dataaccess.GoogleFinClient;
import io.github.kri2.dataaccess.UserDao;
import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.Stock;
import io.github.kri2.domain.User;

@Controller
public class WebController {
	@Autowired
	UserDao userDao;
	
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
	@RequestMapping("/welcome")
	public String serveWelcome(){
		/*display login page based on user name provided load his portfolio */
		/* STEP 1 get portfolio data from db for particular user*/
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
}
