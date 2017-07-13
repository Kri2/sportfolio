package io.github.kri2.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.Stock;

@Controller
public class WebController {
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
		RestTemplate restTemplate = new RestTemplate(); 
		String string="";
		try{
			string = restTemplate.getForObject("http://finance.google.com/finance/info?client=ig&q=AAPL", String.class);
			string = string.replace("//","").replace("[","").replace("]","").replace("\n","").replace("\t","");
			System.out.println(string);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		Gson gson = new Gson();
        gson.toJson(string);
        System.out.println(gson.toJson(string));
        
        
        ObjectMapper mapper = new ObjectMapper();
        Stock stock1;
		try {
			stock1 = mapper.readValue(string, Stock.class);
			System.out.println(stock1.getPrice());
			System.out.println(stock1.getTicker());
			System.out.println(stock1.getPercentChange());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//LocalDate today = LocalDate.now();
		LocalDate today = LocalDate.of(2016, 1, 22);
		System.out.println(today);
		/*Generating sample data for the view */
		ArrayList<PortfolioItem> list = new ArrayList<>();
		
		for(int i=0;i<5;i++){
			list.add(new PortfolioItem(new Stock("AAPL", 503.22), 
					today.plus(i, ChronoUnit.WEEKS),
					99+6*i,
					400.08+9*i));
		}
		model.addAttribute("portfolioList", list);
		return "welcome";
	}
}
