package es.uco.pw.display.javabean;

import java.io.Serializable;

public class Customerbean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email = "";
	private String password = "";
	
	public String getEmail() {
		return email;
	}

	public void setemail(String login) {
		this.email = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
