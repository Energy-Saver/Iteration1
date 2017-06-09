/**
 * TCSS 360 - Deliverable 3
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class user defines a single user with associated login information and projects.
 *
 * @author Alex Smith - coding/editing
 * @version 8 June 2017
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
    
    /**
     * Returns current project.
     * @return current project
     */
    public Project getProject() {
    	return myCurrentProject;
    }
    
    /**
     * Returns collection of this user's projects.
     * @return collection of this user's projects
     */
    public List<Project> getProjects() {
    	return myProjects;
    }
    
    /**
     * Returns number of projects for this user.
     * @return number of projects for this user
     */
    public int getNumberOfProjects() {
    	return myProjects.size();
    }
    
    /**
     * Returns true if user has a project with theName.
     * @param theName name of the project to verify
     * @return true if user has a project with theName
     */
    public boolean contains(String theName) {
    	for (Project p : myProjects) {
    		if (p.getProjectName().equals(theName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Returns collection of user's project names.
     * @return collection of user's project names
     */
    public List<String> getProjectNames() {
    	List<String> list = new ArrayList<String>();
    	for (Project p : myProjects) {
    		list.add(p.getProjectName());
    	}
    	return list;
    }
    
    /**
     * Sets current project to theProjectName, or to null if not found.
     * @param theProjectName name of project to set current to
     */
    public void setCurrentProject(String theProjectName) {
    	for (Project p : myProjects) {
    		if (p.getProjectName().equals(theProjectName)) {
    			myCurrentProject = p;
    			return;
    		}
    	}
    	myCurrentProject = null; //if not found, verify clear field
    }
    
    /**
     * Deletes the currently loaded project.
     */
    public void deleteProject() {
    	myProjects.remove(myCurrentProject);
    	myCurrentProject = null;
    }
    
    /**
     * Deletes theProject from this user.
     * @param theProject project to delete
     */
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
    
    /**
     * Creates new project with theProjectName, returning success of 0.  If there is an existing
     * project with that name, returns -1 indicating failure.
     * @param theProjectName name of new project
     * @return 0 if successfully created project, else -1
     */
    public int createNewProject(String theProjectName) {
    	if (myProjects.contains(theProjectName)) {
    		return -1;
    	}
    	myCurrentProject = new Project(theProjectName);
    	myProjects.add(myCurrentProject);
    	return 0;
    }
    
    public void addProject(Project theProject) {
    	myProjects.add(theProject);
    }
    
    //if not found returns -1, returns 0 if success
    /**
     * Loads theProject returning success of 0, if theProject does not exist, returns -1.
     * @param theProject project to load
     * @return 0 if successfully loaded, else -1
     */
    public int loadProject(String theProject) {
    	if (myProjects.indexOf(theProject) == -1) {
    		return -1;
    	}
    	myCurrentProject = myProjects.get(myProjects.indexOf(theProject));
    	return 0;
    }

    /**
     * Returns first name of user.
     * @return first name of user
     */
    public String getFirstName() {
        return myFirstName;
    }

    /**
     * Returns email of user.
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
