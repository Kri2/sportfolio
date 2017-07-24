package io.github.kri2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;



@Entity
@Table(name = "portfolio_items")
public class PortfolioItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pi_id")
	private Long id;
	private String ticker;
	private Double price;
	
	//@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="user_id",nullable=false)
	@ManyToOne()
	@JoinColumn(name="user_id",nullable=false)
	User owner;
	
	public PortfolioItem(){
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
	public User getOwner() {	
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
		//if(!owner.getPortfolioItems().contains(this)){
		//	owner.getPortfolioItems().add(this);
		//}
	}
}
