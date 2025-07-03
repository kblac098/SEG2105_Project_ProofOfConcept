package backend;

public class Organizer extends UserAccount {
	
	String usernameString;
	String password;
	String userID;
	
	public Organizer(String username, String password, String userID) {
		super(username, password, userID);
	}
	
	public void editEvents() {
		System.out.println("events edited!!!");
	}
}