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
import io.github.kri2.service.Download;

@Controller
public class WebController {
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
		Download download = new Download();
		
		
		
		//LocalDate today = LocalDate.now();
		LocalDate today = LocalDate.of(2016, 1, 22);
		System.out.println(today);
		/*Generating sample data for the view */
		ArrayList<PortfolioItem> list = new ArrayList<>();
		//list.add(new PortfolioItem(stock, today, 0, 0.0));
		ArrayList<String> tickerList = new ArrayList<>();
		tickerList.add("AAPL");
		tickerList.add("THRM");
		tickerList.add("GOOG");
		tickerList.add("CORE");
		tickerList.add("SNDK");
		for(int i=0;i<5;i++){
			Stock stock = download.download(tickerList.get(i));
			list.add(new PortfolioItem(stock, 
					today.plus(i, ChronoUnit.WEEKS),
					99+6*i,
					400.08+9*i));
		}
		model.addAttribute("portfolioList", list);
		return "welcome";
	}
}
