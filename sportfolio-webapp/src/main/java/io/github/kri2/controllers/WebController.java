package io.github.kri2.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import io.github.kri2.domain.Quote;
import io.github.kri2.domain.Stock;

@Controller
public class WebController {
	@RequestMapping("/welcome")
	public String serveWelcome(Model model){
		
		//easiest situation, standard json mapped directrly to a matching quote class
		RestTemplate restTemplate = new RestTemplate();
		try{
			Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			System.out.println(quote.toString());
			model.addAttribute("quote_from_rest", quote.getValue().getQuote());
		}catch(Exception e){
			model.addAttribute("quote_from_rest", "not available");
			System.out.println(e.getMessage());
		}
        //that's it, enough if json is properly structured
        
		//trying to do the same thing with google finance api
		restTemplate = new RestTemplate();    
		//this part is for changing header from text/html to applicaiton/json, google provides json-ish string instead of a full json
		/*
		restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });*/
		
		//same thing different way, changing headers/getForObject didn't like the text/html header
		/*
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        */
		//tried to get it as an array
        //Stock[] forNow = restTemplate.getForObject("http://finance.google.com/finance/info?client=ig&q=AAPL", Stock[].class);
        
		//finally decided to to this way, not practical but proves the point -> get it as string, convert to json, mapp json to an object
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
			System.out.println(stock1.getCp());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       //trying here some combinations when thought that this was going to be an array
        //Stock stock = restTemplateStock.getForObject("http://finance.google.com/finance/info?client=ig&q=AAPL", Stock.class);
        //System.out.println(stock.get_l_curr());
        /*
        ResponseEntity<Stock> rE = restTemplate.exchange("http://gturnquist-quoters.cfapps.io/api/random", 
        													HttpMethod.GET, 
        													null, 
        													new ParameterizedTypeReference<Stock>(){} );
        */
        //ResponseEntity<List<Stock>> googleResponse = restTemplateStock.exchange("finance.google.com/finance/info?client=ig&q=NASDAQ%3AAAPL", HttpMethod.GET, null, new ParameterizedTypeReference<List<Stock>>() {
        //});
        //List<Stock> rates = googleResponse.getBody();
        //System.out.println(rates.get(0).get_l_curr());
        //Stock rate = rE.getBody();
        //System.out.println(rate.get_l_curr());
        //System.out.println(searchList.toString());
		return "welcome";
	}
}


/*
 * Tak wygląda odpowiedz json
 * {"type":"success","value":{"id":1,"quote":"Working with Spring Boot is like pair-programming with the Spring developers."}}
 * tak "pseudo-json" z googla
 * // [ { "id": "22144" ,"t" : "AAPL" ,"e" : "NASDAQ" ,"l" : "144.15" ,"l_fix" : "144.15" ,"l_cur" : "144.15" ,"s": "1" ,"ltt":"4:00PM EDT" ,"lt" : "Jul 7, 4:00PM EDT" ,"lt_dts" : "2017-07-07T16:00:05Z" ,"c" : "+1.42" ,"c_fix" : "1.42" ,"cp" : "0.99" ,"cp_fix" : "0.99" ,"ccol" : "chg" ,"pcls_fix" : "142.73" ,"el": "144.79" ,"el_fix": "144.79" ,"el_cur": "144.79" ,"elt" : "Jul 10, 6:10AM EDT" ,"ec" : "+0.64" ,"ec_fix" : "0.64" ,"ecp" : "0.44" ,"ecp_fix" : "0.44" ,"eccol" : "chg" ,"div" : "0.63" ,"yld" : "1.75" } ]
 * 
 */
 
