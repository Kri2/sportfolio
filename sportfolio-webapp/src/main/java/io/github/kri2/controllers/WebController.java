package io.github.kri2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.kri2.dataaccess.GoogleFinClient;
import io.github.kri2.domain.Stock;

@Controller
public class WebController {
	@RequestMapping("/welcome")
	public String serveWelcome(){
		/*display login page based on user name provided load his portfolio */
		/* STEP 1 get portfolio data from db for particular user*/
		User user = new User();
		
		/* STEP 2 using tickers list from portfolio get data from google */
		GoogleFinClient googleFinClient = new GoogleFinClient();
		Stock stock = googleFinClient.getStock("AAPL");
		System.out.println(stock.toString());
		/* STEP 3 Display portfolio summary */
		/* add ability to add/remove stocks from portfolio */
		/* add ability to create/delete user/portfolio */
		return "welcome";
	}
}
