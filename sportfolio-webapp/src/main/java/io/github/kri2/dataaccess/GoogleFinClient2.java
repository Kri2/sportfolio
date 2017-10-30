package io.github.kri2.dataaccess;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.kri2.domain.Stock;

public class GoogleFinClient2 implements StockDao{

	@Override
	public void setStock(Stock stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stock getStock(String ticker) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String string="";
		try{
			//string = restTemplate.getForObject("http://finance.google.com/finance/info?client=ig&q="+ticker, String.class);
			string = restTemplate.getForObject("http://finance.google.com/finance?output=json&q="+ticker, String.class);
			//string = string.replace("//","").replace("\n","").replace("[","").replace("]","").replace("\t","");
			string = string.replace("// [", "");
			System.out.println("========");
			//System.out.println(string);
			System.out.println("========");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
		
		
		//finally decided to to this way, not practical but proves the point -> get it as string, convert to json, mapp json to an object 
				Gson gson = new Gson();
				gson.toJson(string);
				System.out.println(gson.toJson(string));
				                
				ObjectMapper mapper = new ObjectMapper();
				Stock stock1=null;
				try {
							stock1 = mapper.readValue(string, Stock.class);
							System.out.println(stock1.getPrice());
							System.out.println(stock1.getTicker());
							System.out.println(stock1.getCp());
							//if ticker symbol is invalid response will come but will be emtpy
							if(stock1.getTicker() == null)return null;
				} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				}
				
				return stock1;
	}
	
}
