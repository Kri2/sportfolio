package io.github.kri2.domain;

public class Stock {
	String ticker;
	Double price;
	Double percentChange;
	
	
	public Stock(){
	}
	public Stock(String ticker){
		this.ticker=ticker;
		this.percentChange=0.0;
		this.price = null;
	}
	public Stock(String ticker, Double price) {
		super();
		this.price = price;
		this.ticker = ticker;
		this.percentChange = 0.0;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Double getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}
}
