package client;

import backend.Admin;
import backend.DatabaseConnection;
import backend.Organizer;
import backend.Participant;
import backend.UserAccount;

public class UI_Login {
	static UserAccount user;
	public static void main(String[] args) throws Exception {
        // Valid Organizer Credentials
        String username = "uOttawa";
        String password = "password1";
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
	}

}
