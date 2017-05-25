package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Group {
	
	private static final String FILENAME = "exportTest.txt";
	
	private User myCurrentUser;
	private List<User> myUsers;
	
	public Group() {
		myCurrentUser = null;
		myUsers = new ArrayList<User>();
	}
	
	public String getCurrentUserName() {
		if (myCurrentUser != null) {
			return myCurrentUser.getFirstName();
		} else {
			return null;
		}
	}
	
	public void signUp(User theUser) {
		myUsers.add(theUser);
		login(theUser.getFirstName(), theUser.getEmail());
	}
	
	public void signUp(String theFirstName, String theEmail) {
		User newUser = new User(theFirstName, theEmail);
		myUsers.add(newUser);
		login(newUser.getFirstName(), newUser.getEmail());
	}
	
	public void login(String theFirstName, String theEmail) {
		myCurrentUser = getUser(theFirstName, theEmail);
	}
	
	public void logout() {
		myCurrentUser = null;
	}
	
	public User getCurrentUser() {
		return myCurrentUser;
	}
	
	public User getUser(String theFirstName, String theEmail) {
		for (User u : myUsers) {
			if (u.getFirstName().equals(theFirstName) && u.getEmail().equals(theEmail)) {
				return u;
			}
		}
		return null; //no user found with those credentials
	}
	
	public int groupSize() {
		return myUsers.size();
	}
	
	public void importGroup() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);
				myUsers.add(new User(st.nextToken(), st.nextToken()));
			}
			System.out.printf("Import Complete: %d users imported.\n", myUsers.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportGroup() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			for (User u : myUsers) {
				bw.write(u.getFirstName() + ' ' + u.getEmail());
				bw.newLine();
			}
			System.out.printf("Export Complete: %d users exported.\n", myUsers.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
