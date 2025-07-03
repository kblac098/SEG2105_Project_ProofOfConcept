package client;

import backend.Participant;

public class UI_Participant {
	
	static Participant user;
	
	public static void main(String[] args) {
		user = (Participant) UI_Login.user;
		user.requestJoinEvent();		
	}
}
