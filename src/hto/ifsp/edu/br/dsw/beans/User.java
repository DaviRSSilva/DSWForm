package hto.ifsp.edu.br.dsw.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hto.ifsp.edu.br.dsw.UserType;

@ManagedBean
@SessionScoped
public class User {

	private String username;
	private String password;
	private UserType userType = UserType.FINANCES;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	

}
