/**
 * TCSS 360 - Iteration 1: Product v0.1
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The user.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class User {
    /** User's first name. */
    private String myFirstName;

    /** User's email. */
    private String myEmail;
    
    /** User's currently in-use project. */
    private Project myCurrentProject;
    
    /** User's projects. */
    private List<Project> myProjects;

    /**
     * Constructs the user.
     */
    public User(String theFirstName, String theEmail) {
        myFirstName = theFirstName;
        myEmail = theEmail;
        myProjects = new ArrayList<Project>();
    }
    
    public Project getProject() {
    	return myCurrentProject;
    }
    
    public int getNumberOfProjects() {
    	return myProjects.size();
    }
    
    public boolean contains(String theName) {
    	for (Project p : myProjects) {
    		if (p.getProjectName().equals(theName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //new, return copy?
    public List<String> getProjectNames() {
    	List<String> list = new ArrayList<String>();
    	for (Project p : myProjects) {
    		list.add(p.getProjectName());
    	}
    	return list;
    }
    
    public void setCurrentProject(String theProjectName) {
    	for (Project p : myProjects) {
    		if (p.getProjectName().equals(theProjectName)) {
    			myCurrentProject = p;
    			return;
    		}
    	}
    	myCurrentProject = null; //if not found, verify clear field
    }
    
    public void deleteProject() {
    	myProjects.remove(myCurrentProject);
    	myCurrentProject = null;
    	System.out.println(myProjects.size());
    }
    
    public void deleteProject(String theProject) {
    	Iterator<Project> i = myProjects.iterator();
    	while (i.hasNext()) {
    		Project p = i.next(); //must be called before iterator removes project, see java api
    		if (p.getProjectName().equals(theProject)) {
    			i.remove();
    		}
    	}
    }
    
    public void clearCurrentProject() {
    	myCurrentProject = null;
    }
    
    //returns -1 if project name used previously, make GUI re-prompt for project name if 0 received.
    //loads newly created project as well
    public int createNewProject(String theProjectName) {
    	if (myProjects.contains(theProjectName)) {
    		return -1;
    	}
    	myCurrentProject = new Project(theProjectName);
    	myProjects.add(myCurrentProject);
    	return 0;
    }
    
    //if not found returns -1, returns 0 if success
    public int loadProject(String theProject) {
    	if (myProjects.indexOf(theProject) == -1) {
    		return -1;
    	}
    	myCurrentProject = myProjects.get(myProjects.indexOf(theProject));
    	return 0;
    }

    /**
     * Returns first name of user.
     *
     * @return first name of user
     */
    public String getFirstName() {
        return myFirstName;
    }

    /**
     * Returns email of user.
     *
     * @return email of user
     */
    public String getEmail() {
        return myEmail;
    }
    
    public String getProjectName() {
    	return myCurrentProject.getProjectName();
    }

    /**
     * Returns string representation of the user.
     *
     * @return string representation of the user
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(85);
        sb.append("[First Name: ");
        sb.append(myFirstName);
        sb.append(", Email: ");
        sb.append(myEmail);
        sb.append(']');
        return sb.toString();
    }

}
