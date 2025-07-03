package client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import backend.Admin;
import backend.DatabaseConnection;
import backend.Organizer;
import backend.UserAccount;

public class UI_Instance {
    private static final String DATABASE_URL = "https://localloop-215ad-default-rtdb.firebaseio.com/";
	private static UserAccount user;
	public static void main(String[] args) throws Exception {
        // Valid Organizer Credentials
        String username = "uOttawa";
        String password = "password1";
        try {
        	DatabaseConnection dbConnect = new DatabaseConnection(username, password);
            UserAccount genericUser = dbConnect.getUser();
            if (genericUser instanceof Organizer) {
            	user = new Organizer (genericUser.getUsername(), genericUser.getPassword(), genericUser.getUserID());
                ((Organizer)user).editEvents();
            }
        } catch (Exception e) {
        	System.err.println("User does not exist in database");
        }
	}

}
