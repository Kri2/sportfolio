package io.github.kri2.service;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.kri2.domain.Stock;

public class Download {
	public Stock download(String ticker){
		RestTemplate restTemplate = new RestTemplate(); 
		String string="";
		try{
			string = restTemplate.getForObject("http://finance.google.com/finance/info?client=ig&q="+ticker, String.class);
			string = string.replace("//","").replace("[","").replace("]","").replace("\n","").replace("\t","");
			System.out.println(string);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		Gson gson = new Gson();
        gson.toJson(string);
        System.out.println(gson.toJson(string));
        
        
        ObjectMapper mapper = new ObjectMapper();
        Stock stock1=new Stock();
		try {
			stock1 = mapper.readValue(string, Stock.class);
			System.out.println(stock1.getPrice());
			System.out.println(stock1.getTicker());
			System.out.println(stock1.getPercentChange());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock1;
	}

}
