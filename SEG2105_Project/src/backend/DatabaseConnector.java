package backend;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class DatabaseConnector {
	
    private static final String DATABASE_URL = "https://localloop-215ad-default-rtdb.firebaseio.com/";
    
    public static void createNew(Participant p) throws Exception {
        URL url = new URL(DATABASE_URL + "users/Participant");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        
        String jsonData = "{\""+p.getUserID()+"\"}";
        System.out.println(jsonData);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonData.getBytes("utf-8"));
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
    
    public static void createNew(Organizer o) {
    	
    }
}
