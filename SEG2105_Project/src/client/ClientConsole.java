package client;

import java.util.Scanner;

public class ClientConsole {
	
	private static Scanner sc;
	
	public ClientConsole() {
		sc = new Scanner(System.in);
	}
	
	public void send(String str) {
		System.out.println(str);
	}
	
	public String retrieve() {
		return sc.nextLine();
	}
	
	public String callback(String prompt) {
		send(prompt);
		return retrieve();
	}
}
