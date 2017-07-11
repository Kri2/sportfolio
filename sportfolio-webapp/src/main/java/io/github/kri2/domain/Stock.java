package io.github.kri2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
	@JsonProperty("t")
	private String ticker;
	@JsonProperty("l_cur")
	private String price;
	private String cp;
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public Stock(){
	}
	public String getTicker(){
		return this.ticker;
	}
	public void setTicker(String ticker){
		this.ticker=ticker;
	}
	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price=price;
	}
	
}
