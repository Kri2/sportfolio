package io.github.kri2.service;

import org.springframework.stereotype.Service;

import io.github.kri2.dataaccess.GoogleFinClient;
import io.github.kri2.dataaccess.GoogleFinClient2;
import io.github.kri2.domain.PortfolioItem;
import io.github.kri2.domain.Stock;
import io.github.kri2.domain.User;

@Service
public class GoogleFinService {
	GoogleFinService(){
	}
	public static User updateData(User user){
		for(PortfolioItem item : user.getPortfolioItems()){
			String currentTicker = item.getTicker();
			System.out.println("Getting info about "+currentTicker);
			
			GoogleFinClient2 googleFinClient = new GoogleFinClient2();
			Stock stock = googleFinClient.getStock( currentTicker );
			if(stock!=null){
				System.out.println("Result: " + stock.toString());
				//tutaj zrobić mapping do stock
				//dalej przypisać ściągnięty stock do portfolioitem
				item.setPrice( Double.valueOf(stock.getPrice()) );
				item.setChangeP( Double.valueOf(stock.getCp()) );
			}
			else{//if something wrong with googlefin server don't update data, return as is
				//later add some marker/timestamp to let know that data is not updated 
			}
		}
		return user;
	}
}
