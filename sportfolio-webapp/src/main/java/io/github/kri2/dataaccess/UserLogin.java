package io.github.kri2.dataaccess;

public class UserLogin {
	private String name;
	private String password;
	private Boolean buttonAddUser;
	public Boolean getButtonAddUser() {
		return buttonAddUser;
	}
	public void setButtonAddUser(Boolean buttonAddUser) {
		this.buttonAddUser = buttonAddUser;
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
