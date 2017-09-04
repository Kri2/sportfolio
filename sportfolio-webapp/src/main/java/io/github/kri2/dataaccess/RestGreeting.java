package io.github.kri2.dataaccess;

public class RestGreeting {
	private final Long id;
	private final String content;
	public RestGreeting(long id, String content){
		this.id=id;
		this.content=content;
	}
	public long getId(){
		return this.id;
	}
	public String getContent(){
		return content;
	}
}
