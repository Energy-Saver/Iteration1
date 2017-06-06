package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.File;   
import javax.swing.*;

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
	
	public void guestUser() {
		if (myUsers.isEmpty() || !myUsers.contains(getUser("guest", "null@void.com"))) {
			System.out.println("guest user created");
			myCurrentUser = new User("guest", "null@void.com");
		} else {
			System.out.println("logged in guest");
			login("guest", "null@void.com");
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
	
	private void removeGuest() {
		myUsers.remove(getUser("guest", "null@void.com"));
	}
	
	public void logout() {
		if (myCurrentUser == null) {
			return;
		}
		myCurrentUser.deleteProject(); //save protects project, logout must delete displayed
		if (myCurrentUser.getFirstName().equals("guest")) {
			removeGuest();
		}
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
	
	public void importGroup(File theFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(theFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(line);
				User u = new User(tokens.nextToken(), tokens.nextToken());
				
				myUsers.add(u);
				while (tokens.hasMoreTokens()) {
					String name = tokens.nextToken();
					String cBT = tokens.nextToken();
					int cBW = Integer.parseInt(tokens.nextToken());
					String rBT = tokens.nextToken();
					int rBW = Integer.parseInt(tokens.nextToken());
					int numBulbs = Integer.parseInt(tokens.nextToken());
					int hrsPrDay = Integer.parseInt(tokens.nextToken());
					int daysPrWk = Integer.parseInt(tokens.nextToken());
					double elecRate = Double.parseDouble(tokens.nextToken());
					double replCost = Double.parseDouble(tokens.nextToken());
					
					Project p = new Project(name, cBT, cBW, rBT, rBW, numBulbs, hrsPrDay,
											daysPrWk, elecRate, replCost);
					u.addProject(p);
				}
			}
			System.out.printf("Import Complete: %d users imported.\n", myUsers.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportGroup(File theFile) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(theFile))) {
			for (User u : myUsers) {
				bw.write(u.getFirstName() + ' ' + u.getEmail() + ' ');
				for (Project p : u.getProjects()) {
					bw.write(p.getProjectName() + ' ');
					bw.write(p.getCurrentBulbType() + ' ');
					bw.write(p.getCurrentBulbWatts() + ' ');
					bw.write(p.getReplacementBulbType() + ' ');
					bw.write(p.getReplacementBulbWatts() + ' ');
					bw.write(p.getNumberOfBulbs() + ' ');
					bw.write(p.getHoursUsedPerDay() + ' ');
					bw.write(p.getNumberDaysPerWeek() + ' ');
					
					Double rate = p.getElectricRate();
					bw.write(rate.toString() + ' ');
					
					Double cost = p.getReplacementBulbCost();
					bw.write(cost.toString() + ' ');
				}
				bw.newLine();
			}
			System.out.printf("Export Complete: %d users exported.\n", myUsers.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
