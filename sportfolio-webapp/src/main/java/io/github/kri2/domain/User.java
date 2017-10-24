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

import io.github.kri2.dataaccess.TickerByUser;

@Entity	//indicating that it is a JPA entity
@Table(name="users3")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	Long id;
	String name;
	String password;
	Boolean enabled;
	/**
	 * Elements of this list (PortfoliItem) provide information about one item in porfolio
	 * The meaning of CascadeType.ALL is that the persistence will propagate (cascade) all 
	 * EntityManager operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) to the relating 
	 * entities.
	 * Marking a reference field with CascadeType.REMOVE (or CascadeType.ALL, which includes 
	 * REMOVE) indicates that remove operations should be cascaded automatically to entity 
	 * objects that are referenced by that field (multiple entity objects can be referenced 
	 * by a collection field):
	 * orphanRmoval - removes childs when parent deleted
	 */
	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval=true)
	private List<PortfolioItem> portfolioItems = new ArrayList<>(); 
	public void addPortfolioItem(TickerByUser tickerByUser){
		PortfolioItem portfolioItem = new PortfolioItem();
		portfolioItem.setOwner(this);
		portfolioItem.setTicker(tickerByUser.getTicker());
		portfolioItem.setSharesCount(Double.valueOf(tickerByUser.getSharesCount()));
		portfolioItem.setPurchasePrice(Double.valueOf(tickerByUser.getPurchasePrice()));
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
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
