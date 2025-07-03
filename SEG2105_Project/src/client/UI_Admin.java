package client;

import backend.Admin;

public class UI_Admin {
	
	static Admin user;
	
	public static void main(String[] args) {
		user = (Admin) UI_Login.user;
		String userID = "lp";
		user.removeUser(userID);		
	}
}
