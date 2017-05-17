package model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private static final String FILENAME = "exportTest.txt";
	
	private User myCurrentUser;
	private List<User> myUsers;
	
	public Group() {
		myCurrentUser = null;
		myUsers = new ArrayList<User>();
	}
	
	public void signUp(User theUser) {
		myUsers.add(theUser);
		//signIn(theUser.getFirstName(), theUser.getEmail());
	}
	
	public void signUp(String theFirstName, String theEmail) {
		User newUser = new User(theFirstName, theEmail);
		myUsers.add(newUser);
		signIn(newUser.getFirstName(), newUser.getEmail());
	}
	
	public void signIn(String theFirstName, String theEmail) {
		myCurrentUser = getUser(theFirstName, theEmail);
	}
	
	private User getUser(String theFirstName, String theEmail) {
		for (User u : myUsers) {
			if (u.getFirstName().equals(theFirstName) && u.getEmail().equals(theEmail)) {
				return u;
			}
		}
		return null; //no user found with those credentials
	}
	
	public void importGroup() {
		
	}
	
	public void exportGroup() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			for (User u : myUsers) {
				bw.write(u.getFirstName() + ' ' + u.getEmail());
				bw.newLine();
				//System.out.println(u.getFirstName() + ' ' + u.getEmail());
			}
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
