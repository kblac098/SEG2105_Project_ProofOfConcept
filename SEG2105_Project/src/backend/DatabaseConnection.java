package backend;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.ListIterator;

public class DatabaseConnection {
	
    private static final String DATABASE_URL = "https://localloop-215ad-default-rtdb.firebaseio.com/";
    private static ArrayList<Participant> allParticipants = new ArrayList<Participant>();
    private static ArrayList<Organizer> allOrganizers = new ArrayList<Organizer>();
	private UserAccount user;

	public DatabaseConnection(String username, String password) throws Exception {
		getAllParticipants();
		getAllOrganizers();
		// Check if user exists in participants or organizers
		ListIterator<Participant> iterateParticipants = allParticipants.listIterator();
		ListIterator<Organizer> iterateOrganizers = allOrganizers.listIterator();
		boolean found = false;
		while (iterateParticipants.hasNext() && !found) {
			Participant p = iterateParticipants.next();
			if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
				user = p;
				found = true;
			}
		}
		while (iterateOrganizers.hasNext() && !found) {
			Organizer o = iterateOrganizers.next();
			if (o.getUsername().equals(username) && o.getPassword().equals(password)) {
				user = o; 
				found = true;
			}
		}
		if (!found) {
			throw new Exception("User does not exist in database");
		}
	}
	
    // Write data
    static void writeData(String path, String jsonData) throws Exception {
        URL url = new URL(DATABASE_URL + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonData.getBytes("utf-8"));
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }

    // Read data
    static String readData(String path) throws Exception {
        URL url = new URL(DATABASE_URL + path + ".json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder json = new StringBuilder();
        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }
        scanner.close();

        return json.toString();
    }
    
   
    // Get All Participants
    static ArrayList<Participant> getAllParticipants() throws Exception {
    	allParticipants.clear();
    	String username = "";
    	String password = "";
    	String userID = "";
    	// Loop Through and Update allParticipants
    	String jsonText = readData("users/Participant");
        JSONObject obj = new JSONObject(jsonText);
        for (Object keyObj : obj.keySet()) {
        	JSONObject childObj = obj.getJSONObject(keyObj.toString());
        	username = childObj.getString("username");
        	password = childObj.getString("password");
        	userID = childObj.getString("userID");
        	allParticipants.add(new Participant(username, password, userID));
        }
    	return allParticipants;
    }
    
    // Get All Organizers
    static ArrayList<Organizer> getAllOrganizers() throws Exception {
    	allOrganizers.clear();
    	String username = "";
    	String password = "";
    	String userID = "";
    	// Loop Through and Update allParticipants
    	String jsonText = readData("users/Organizer");
        JSONObject obj = new JSONObject(jsonText);
        for (Object keyObj : obj.keySet()) {
        	JSONObject childObj = obj.getJSONObject(keyObj.toString());
        	username = childObj.getString("username");
        	password = childObj.getString("password");
        	userID = childObj.getString("userID");
        	allOrganizers.add(new Organizer(username, password, userID));
        }
    	return allOrganizers;
    }
	
	public UserAccount getUser() {
		return user;
	}
}
