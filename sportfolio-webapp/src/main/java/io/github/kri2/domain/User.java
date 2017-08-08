package io.github.kri2.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity	//indicating that it is a JPA entity
@Table(name="users3")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	Long id;
	String name;
	String password;
	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<PortfolioItem> portfolioItems = new ArrayList<>(); 
	public void addPortfolioItem(String ticker){
		PortfolioItem portfolioItem = new PortfolioItem();
		portfolioItem.setOwner(this);
		portfolioItem.setTicker(ticker);
		portfolioItem.setPrice(0.0);
		portfolioItem.setChangeP(0.0);
		this.portfolioItems.add(portfolioItem);
	}
	
	public List<PortfolioItem> getPortfolioItems() {
		return portfolioItems;
	}
	public void setPortfolioItems(List<PortfolioItem> portfolioItems) {
		this.portfolioItems = portfolioItems;
	}
	public User(){//The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected. 
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	public void addItem(PortfolioItem item) {
        this.portfolioItems.add(item);
        if (item.getOwner() != this) {
            item.setOwner(this);
        }
    }*/
	public String toString(){
		String str="";
		//str += "id= "+ this.id;
		str += "name= "+this.name+" ";
		str += " pass= "+this.password+" ";
		str += " portfolio: ";
		for(int i=0;i<portfolioItems.size();i++)
		{
			str += portfolioItems.get(i).getTicker() + ":" +portfolioItems.get(i).getPrice()+" ";
		}
		//portfolioItems.forEach(var -> str += (var.getTicker()+var.getPrice()));
		System.out.println(str);
		return str;
	}
}
