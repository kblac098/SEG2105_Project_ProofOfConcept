package client;

import backend.Organizer;

public class UI_Organizer {
	
	static Organizer user;
	
	public static void main(String[] args) {
		user = (Organizer) UI_Login.user;
		user.editEvents();
		
		
	}
}
