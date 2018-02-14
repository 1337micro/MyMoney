package src;
//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: Class to create the Authentification which contains the username and the password
//          
//--------------------------------------------------------


public class AuthentificationUser {
	String username;
	String password;


	/*
	 * default constructor
	 */
	public AuthentificationUser() {
		username="";
		password="";
	}
	/*
	 *  constructor
	 */
	public AuthentificationUser(String user, String pass) {
		this.username = user;
		this.password = pass;
	}

	/*
	 * getters and setters for username and password
	 */
	public String getUsername() {
		return username;
	}

	public  void setUsername(String username) {
		this.username = username;
	}

	public  String getPassword() {
		return password;
	}

	public  void setPassword(String password) {
		this.password = password;
	}


}
