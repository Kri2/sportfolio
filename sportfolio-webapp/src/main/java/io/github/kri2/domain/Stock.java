package io.github.kri2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
	private String t;
	private String l_cur;
	public Stock(){
	}
	public String getT(){
		return this.t;
	}
	public void setT(String t){
		this.t=t;
	}
	public String getL_cur(){
		return this.l_cur;
	}
	public void setL_cur(String l_cur){
		this.l_cur=l_cur;
	}
}
