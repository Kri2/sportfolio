package io.github.kri2.dataaccess;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.github.kri2.domain.Stock;
/**
 * GoogleFinClient gets data from google api and uses standard StockDao interface
 * @author kriz
 *
 */
public class GoogleFinClient implements StockDao {

	@Override
	public void setStock(Stock stock) {
		// TODO Auto-generated method stub

	}

	@Override
	public Stock getStock(String ticker) {
		// TODO Auto-generated method stub
		// jak zwrócić stock, trzeba zrobić mapping
		RestTemplate restTemplate = new RestTemplate();
		String string="";
		try{
			string = restTemplate.getForObject("http://finance.google.com/finance/info?client=ig&q="+ticker, String.class);
			string = string.replace("//","").replace("[","").replace("]","").replace("\n","").replace("\t","");
			System.out.println(string);
		}catch(Exception e){
			System.out.println(e.getMessage());
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
		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		
		return stock1;
	}

}
