package io.github.kri2.dataaccess;

import org.springframework.web.client.RestTemplate;
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
		return null;
	}

}
