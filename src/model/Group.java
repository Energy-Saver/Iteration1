/**
 * TCSS 360 - Deliverable 3
 */

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

/**
 * Class group contains a list of users and a current user along with associated
 * group functionality.
 * 
 * @author Nikolai Carlson
 * @version 8 June 2017
 */
public class Group {
	/** The user currently in use. */
	private User myCurrentUser;
	
	/** The collection of all users */
	private List<User> myUsers;
	
	/**
	 * Constructs group.
	 */
	public Group() {
		myCurrentUser = null;
		myUsers = new ArrayList<User>();
	}
	
	/**
	 * Returns current user name.
	 * @return current user name
	 */
	public String getCurrentUserName() {
		if (myCurrentUser != null) {
			return myCurrentUser.getFirstName();
		} else {
			return null;
		}
	}
	
	/**
	 * Creates a guest user if needed.
	 */
	public void guestUser() {
		if (myUsers.isEmpty() || !myUsers.contains(getUser("guest", "null@void.com"))) {
			System.out.println("guest user created");
			myCurrentUser = new User("guest", "null@void.com");
		} else {
			System.out.println("logged in guest");
			login("guest", "null@void.com");
		}
	}
	
	/**
	 * Signs up theUser and then logs them in.
	 * @param theUser user wanting to sign up
	 */
	public void signUp(User theUser) {
		myUsers.add(theUser);
		login(theUser.getFirstName(), theUser.getEmail());
	}
	
	/**
	 * Signs up a user by first name and email.
	 * @param theFirstName the new user's first name
	 * @param theEmail the new user's email
	 */
	public void signUp(String theFirstName, String theEmail) {
		User newUser = new User(theFirstName, theEmail);
		myUsers.add(newUser);
		login(newUser.getFirstName(), newUser.getEmail());
	}
	
	/**
	 * Logs user in by first name and email.
	 * @param theFirstName first name of user
	 * @param theEmail email of user
	 */
	public void login(String theFirstName, String theEmail) {
		myCurrentUser = getUser(theFirstName, theEmail);
	}
	
	/**
	 * Removes guest user account from group.
	 */
	private void removeGuest() {
		myUsers.remove(getUser("guest", "null@void.com"));
	}
	
	/**
	 * Logs out current user, deleting unsaved project if necessary.
	 */
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
	
	/**
	 * Returns current user.
	 * @return current user
	 */
	public User getCurrentUser() {
		return myCurrentUser;
	}
	
	/**
	 * Returns user with theFirstName and theEmail.
	 * @param theFirstName first name of desired user
	 * @param theEmail email of desired user
	 * @return user with theFirstName and theEmail
	 */
	public User getUser(String theFirstName, String theEmail) {
		for (User u : myUsers) {
			if (u.getFirstName().equals(theFirstName) && u.getEmail().equals(theEmail)) {
				return u;
			}
		}
		return null; //no user found with those credentials
	}
	
	/**
	 * Returns size of group.
	 * @return size of group
	 */
	public int groupSize() {
		return myUsers.size();
	}
	
	/**
	 * Imports group from data file.
	 * @param theFile data file for importing
	 */
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
	
	/**
	 * Exports group to data file.
	 * @param theFile data file for exporting
	 */
	public void exportGroup(File theFile) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(theFile))) {
			for (User u : myUsers) {
				bw.write(u.getFirstName() + ' ' + u.getEmail() + ' ');
				for (Project p : u.getProjects()) {
					bw.write(p.getProjectName() + ' ');
					bw.write(p.getCurrentBulbType() + ' ');
					
					Integer cWatts = p.getCurrentBulbWatts();
					bw.write(cWatts.toString() + ' ');
					
					bw.write(p.getReplacementBulbType() + ' ');
					
					Integer rWatts = p.getReplacementBulbWatts();
					bw.write(rWatts.toString() + ' ');
					
					Integer nBulbs = p.getNumberOfBulbs();
					bw.write(nBulbs.toString() + ' ');
					
					Integer hrsDay = p.getHoursUsedPerDay();
					bw.write(hrsDay.toString() + ' ');
					
					Integer daysWk = p.getNumberDaysPerWeek();
					bw.write(daysWk.toString() + ' ');
					
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
