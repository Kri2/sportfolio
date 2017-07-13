package io.github.kri2.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.Stock;

@Controller
public class WebController {
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
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
