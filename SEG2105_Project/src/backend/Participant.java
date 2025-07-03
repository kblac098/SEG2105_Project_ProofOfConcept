package backend;

public class Participant extends UserAccount {
	
	String usernameString;
	String password;
	String userID;
	
	public Participant(String username, String password, String userID) {
		super(username, password, userID);
	}
	
	public void requestJoinEvent() {
		
	}
}