package io.github.kri2.domain;

import java.time.LocalDate;

public class PortfolioItem extends Stock{
	LocalDate datePurchased;
	Integer sharesCount;
	Double purchasePrice;
	
	public PortfolioItem(){
	}
	public PortfolioItem(Stock stock, LocalDate datePurchased, Integer sharesCount, Double purchasePrice){
		super(stock.ticker,stock.price);
		this.datePurchased = datePurchased;
		this.sharesCount = sharesCount;
		this.purchasePrice = purchasePrice;
	}
	public LocalDate getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(LocalDate datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Integer getSharesCount() {
		return sharesCount;
	}

	public void setSharesCount(Integer sharesCount) {
		this.sharesCount = sharesCount;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}	
	
}
