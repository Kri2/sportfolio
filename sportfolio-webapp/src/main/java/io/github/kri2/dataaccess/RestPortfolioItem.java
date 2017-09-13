package io.github.kri2.dataaccess;

public class RestPortfolioItem {
	private Long id;
	private String ticker;
	private Double price;
	private Double changep;
	public RestPortfolioItem(){
	}
	
	public RestPortfolioItem(String ticker, Double price, Double changep) {
		super();
		this.ticker = ticker;
		this.price = price;
		this.changep = changep;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getChangep() {
		return changep;
	}
	public void setChangep(Double changep) {
		this.changep = changep;
	}
	
}
