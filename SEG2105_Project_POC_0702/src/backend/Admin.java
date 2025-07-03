package backend;

public class Admin extends UserAccount {
	
	String usernameString;
	String password;
	String userID;
	
	Admin(String username, String password, String userID) {
		super(username, password, userID);
	}
	
	void editParticipants() {
		
	}
	
}