package client;

import backend.Admin;
import backend.DatabaseConnection;
import backend.DatabaseConnector;
import backend.Organizer;
import backend.Participant;
import backend.UserAccount;

public class UI_Login {
	static UserAccount user;
	static ClientConsole ui;
	private static void print(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception {
        
		ui = new ClientConsole();
		ui.send("Welcome to LocalLoop\n");
		String choice = ui.callback("Do you have an account?: \"Y\" to login or \"N\" to create an account");
		
		if (choice.equals("Y")) {
			String username = ui.callback("Please enter your username");
			String password = ui.callback("Please enter your password");;
	        try {
	        	DatabaseConnection dbConnect = new DatabaseConnection(username, password);
	            UserAccount genericUser = dbConnect.getUser();
	            if (genericUser instanceof Participant) {
	            	user = new Participant(genericUser.getUsername(), genericUser.getPassword(), genericUser.getUserID());
	            	UI_Participant.main(args);
	            } else if (genericUser instanceof Organizer) {
	            	user = new Organizer (genericUser.getUsername(), genericUser.getPassword(), genericUser.getUserID());
	            	UI_Organizer.main(args);
	            } else if (genericUser instanceof Admin) {
	            	user = new Admin(genericUser.getUsername(), genericUser.getPassword(), genericUser.getUserID());
	            	UI_Admin.main(args);
	            } else {
	            	throw new Exception("Invalid user type");
	            }
	        } catch (Exception e) {
	        	System.err.println("User does not exist in database");
	        }
		} else if (choice.equals("N")) {
			String accountType = ui.callback("Do you wish to be a \"P\" or an \"O\"?:");
			String username = ui.callback("Please enter the username for your new account:");
			String password = ui.callback("Please enter the password for your new account:");
			if (accountType.equals("P")) {
				user = new Participant(username, password, null);
				DatabaseConnector.createNew((Participant)user);
			} else if (accountType.equals("O")) {
				user = new Organizer(username, password, null);
				DatabaseConnector.createNew((Organizer)user);
			} else {
				throw new Exception("Invalid account type");
			}
		} else {
			throw new Exception("Invalid user choice");
		}
	}

}
