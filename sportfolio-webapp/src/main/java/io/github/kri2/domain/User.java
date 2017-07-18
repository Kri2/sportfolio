package io.github.kri2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	//indicating that it is a JPA entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	String name;
	String password;
	String portfolio_items="";
	public String getPortfolio_items() {
		return portfolio_items;
	}
	public void setPortfolio_items(String portfolio_items) {
		this.portfolio_items = portfolio_items;
	}
	public User(){//The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected. 
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
}
