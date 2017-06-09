/**
 * TCSS 360 - Deliverable 3
 */

package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Project contains information surrounding an energy comparison.
 * 
 * @author Darren Carpenter - coding/editing
 * @version 8 June 2017
 */
public class Project {
	/** Types of bulbs. */
	public static final String[] BULB_TYPES = { "Incandescent", "CFL", "LED" };
	
	/** Available wattages. */
	public static final int[] BULB_WATTAGES = {60, 14, 8};

	/** Project name. */
	private String myProjectName;

	/** For future binding between bulb types and wattages. */
	private Map<String, Integer> myBulbs;
	
	/** Current bulb type. */
	private String myCurrentBulbType;
	
	/** Replacement bulb type. */
	private String myReplacementBulbType;
	
	/** Watts of current bulb. */
	private int myCurrentBulbWatts;
	
	/** Watts of replacement bulb. */
	private int myReplacementBulbWatts;

	/** Number of bulbs in desired area for comparison. */
	private int myNumberOfBulbs;
	
	/** Hours lights are operated per day. */
	private int myHoursUsedPerDay;
	
	/** Days per week lights are operated. */
	private int myNumberDaysPerWeek;
	
	/** Electricity cost in KWH. Typical WA state rate is 0.08/KWH */
	private double myElectricRate;
	
	/** Cost of the replacement fixture or bulb. */
	private double myReplacementBulbCost;

	/**
	 * Constructs a new project with given theProjectName.
	 * @param theProjectName desired name for the project
	 */
	public Project(String theProjectName) {
		myProjectName = theProjectName;
		myCurrentBulbType = BULB_TYPES[0];
		myCurrentBulbWatts = BULB_WATTAGES[0];
		myReplacementBulbType = BULB_TYPES[2];
		myReplacementBulbWatts = BULB_WATTAGES[2];
		myNumberOfBulbs = 12;
		myHoursUsedPerDay = 4;
		myNumberDaysPerWeek = 7;
		myElectricRate = 0.12;
		myReplacementBulbCost = 24.99;

		setupBulbs();
	}

	/**
	 * Constructs a new project with supplied parameter values.
	 * @param theProjectName name of the project
	 * @param theCurrentBulbType type of the current bulb
	 * @param theCurrentBulbWatts watts of the current bulb
	 * @param theReplacementBulbType type of the replacement bulb
	 * @param theReplacementBulbWatts watts of the replacement bulb
	 * @param theNumberOfBulbs number of bulbs in the project
	 * @param theHoursUsedPerDay hours per day the lights are operated
	 * @param theNumberDaysPerWeek days per week the lights are operated
	 * @param theElectricRate electricity rate
	 * @param theReplacementBulbCost cost of the replacment bulb or fixture
	 */
	public Project(String theProjectName, String theCurrentBulbType, int theCurrentBulbWatts,
			String theReplacementBulbType, int theReplacementBulbWatts, int theNumberOfBulbs, int theHoursUsedPerDay,
			int theNumberDaysPerWeek, double theElectricRate, double theReplacementBulbCost) {
		myProjectName = theProjectName;
		myCurrentBulbType = theCurrentBulbType;
		myCurrentBulbWatts = theCurrentBulbWatts;
		myReplacementBulbType = theReplacementBulbType;
		myReplacementBulbWatts = theReplacementBulbWatts;
		myNumberOfBulbs = theNumberOfBulbs;
		myHoursUsedPerDay = theHoursUsedPerDay;
		myNumberDaysPerWeek = theNumberDaysPerWeek;
		myElectricRate = theElectricRate;
		myReplacementBulbCost = theReplacementBulbCost;

		setupBulbs();
	}

	/**
	 * Returns project's name.
	 * @return project's name
	 */
	public String getProjectName() {
		return myProjectName;
	}

	/**
	 * Returns current bulb type.
	 * @return current bulb type
	 */
	public String getCurrentBulbType() {
		return myCurrentBulbType;
	}

	/**
	 * Returns current bulb watts.
	 * @return current bulb watts
	 */
	public int getCurrentBulbWatts() {
		return myCurrentBulbWatts;
	}

	/**
	 * Returns replacement bulb type.
	 * @return replacement bulb type
	 */
	public String getReplacementBulbType() {
		return myReplacementBulbType;
	}

	/**
	 * Returns replacement bulb watts.
	 * @return replacement bulb watts
	 */
	public int getReplacementBulbWatts() {
		return myReplacementBulbWatts;
	}

	/**
	 * Returns number of bulbs.
	 * @return number of bulbs
	 */
	public int getNumberOfBulbs() {
		return myNumberOfBulbs;
	}

	/**
	 * Returns hours used per day.
	 * @return hours used per day
	 */
	public int getHoursUsedPerDay() {
		return myHoursUsedPerDay;
	}

	/**
	 * Returns number of days per week lights are operated
	 * @return number of days per week lights are operated
	 */
	public int getNumberDaysPerWeek() {
		return myNumberDaysPerWeek;
	}

	/**
	 * Returns electric rate.
	 * @return electric rate
	 */
	public double getElectricRate() {
		return myElectricRate;
	}

	/**
	 * Returns cost of replacement bulb.
	 * @return cost of replacement bulb
	 */
	public double getReplacementBulbCost() {
		return myReplacementBulbCost;
	}

	/**
	 * Sets project name to thisProjectName.
	 * @param thisProjectName name to set project to
	 */
	public void setProjectName(String thisProjectName) {
		myProjectName = thisProjectName;
	}

	/**
	 * Returns true if successfully sets current bulb type to theCurrentBulbType.
	 * @param theCurrentBulbType type of current 
	 * @return true if successfully sets current bulb type to theCurrentBulbType
	 */
	public boolean setCurrentBulbType(String theCurrentBulbType) {
		if (!checkBulb(theCurrentBulbType)) {
			return false;
		}
		myCurrentBulbType = theCurrentBulbType;
		return true;
	}

	/**
	 * Sets watts of current bulb.
	 * @param theCurrentBulbWatts watts of current bulb
	 */
	public void setCurrentBulbWatts(int theCurrentBulbWatts) {
		myCurrentBulbWatts = theCurrentBulbWatts;
	}

	/**
	 * Returns true if successfully sets the replacement bulb type.
	 * @param theReplacementBulbType replacement bulb type
	 * @return true if successfully sets the replacement bulb type
	 */
	public boolean setReplacementBulbType(String theReplacementBulbType) {
		if (!checkBulb(theReplacementBulbType)) {
			return false;
		}
		myReplacementBulbType = theReplacementBulbType;
		return true;
	}

	/**
	 * Sets watts of the replacement bulb.
	 * @param theReplacementBulbWatts watts of the replacement bulb
	 */
	public void setReplacementBulbWatts(int theReplacementBulbWatts) {
		myReplacementBulbWatts = theReplacementBulbWatts;
	}

	/**
	 * Returns true if successfully sets the number of bulbs to theNumberOfBulbs.
	 * @param theNumberOfBulbs number of bulbs in this project
	 * @return true if successfully sets number of bulbs
	 */
	public boolean setNumberOfBulbs(int theNumberOfBulbs) {
		if (theNumberOfBulbs <= 0) {
			return false;
		}
		myNumberOfBulbs = theNumberOfBulbs;
		return true;
	}

	/**
	 * Sets the hours the lights are used per day to theHoursUsedPerDay.
	 * @param theHoursUsedPerDay hours per day the lights are used
	 */
	public void setHoursUsedPerDay(int theHoursUsedPerDay) {
		myHoursUsedPerDay = theHoursUsedPerDay;
	}

	/**
	 * Sets number of days lights are used per week to theNumberDaysPerWeek.
	 * @param theNumberDaysPerWeek number of days lights are used per week
	 */
	public void setNumberDaysPerWeek(int theNumberDaysPerWeek) {
		myNumberDaysPerWeek = theNumberDaysPerWeek;
	}

	/**
	 * Sets electric rate to theElectricRate.
	 * @param theElectricRate rate the electricity is billed at per KWH
	 * @return
	 */
	public boolean setElectricRate(double theElectricRate) {
		if (theElectricRate <= .05 || theElectricRate >= .4) {
			return false; // Hawaii has highest rate @ ~.35, WA lowest ~.07
		}
		myElectricRate = theElectricRate;
		return true;
	}

	/**
	 * Sets replacement bulb cost to theReplacementBulbCost.
	 * @param theReplacementBulbCost cost of the replacement bulb
	 */
	public void setReplacementBulbCost(double theReplacementBulbCost) {
		myReplacementBulbCost = theReplacementBulbCost;
	}

	/**
	 * Sets up the relationship between bulb types and wattages (for future).
	 */
	public void setupBulbs() {
		myBulbs = new HashMap<String, Integer>();

		for (int i = 0; i < BULB_TYPES.length; i++) {
			myBulbs.put(BULB_TYPES[i], BULB_WATTAGES[i]);
		}
	}

	/**
	 * Returns the annual cost of operating the current bulbs.
	 * @return the annual cost of operating the current bulbs
	 */
	public double currentAnnualCost() {
		return annualCost(myCurrentBulbType);
	}

	/**
	 * Returns the annual cost of operating the replacement bulbs.
	 * @return the annual cost of operating the replacement bulbs
	 */
	public double replacementAnnualCost() {
		return annualCost(myReplacementBulbType);
	}

	/**
	 * Returns the per year return from upgrading the lights.
	 * @return the per year return from upgrading the lights
	 */
	public double returnPerYear() {
		return currentAnnualCost() - replacementAnnualCost();
	}

	/**
	 * Returns years need to payoff initial cost.
	 * @return years need to payoff initial cost
	 */
	public double payoffYears() {
		return (myNumberOfBulbs * myReplacementBulbCost) / returnPerYear();
	}

	/**
	 * Returns initial cost of replacement fixtures/bulbs.
	 * @return initial cost of replacement fixtures/bulbs
	 */
	public double initialCost() {
		return myNumberOfBulbs * myReplacementBulbCost;
	}

	/**
	 * Returns annual cost of operating theBulbType.
	 * @param theBulbType theBulbType to use in the calculation
	 * @return annual cost of operating theBulbType
	 */
	private double annualCost(String theBulbType) {
		int hoursPerYear = myHoursUsedPerDay * myNumberDaysPerWeek * 56;
		double costPerHour = myBulbs.get(theBulbType) / 1000.0 * myElectricRate;
		return hoursPerYear * costPerHour * myNumberOfBulbs;
	}

	/**
	 * Returns true if theBulbType is legal.
	 * @param theBulbType the bulb type to verify
	 * @return true if theBulbType is legal
	 */
	private boolean checkBulb(String theBulbType) {
		for (String b : BULB_TYPES) {
			if (b.equals(theBulbType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns string representation of the Project, namely all contained values.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(80);

		sb.append(myProjectName);
		sb.append('[');
		sb.append(myCurrentBulbType);
		sb.append("->");
		sb.append(myReplacementBulbType);
		sb.append(", ");
		sb.append(myCurrentBulbWatts);
		sb.append("->");
		sb.append(myReplacementBulbWatts);
		sb.append(", #: ");
		sb.append(myNumberOfBulbs);
		sb.append(", h/d: ");
		sb.append(myHoursUsedPerDay);
		sb.append(", d/wk: ");
		sb.append(myNumberDaysPerWeek);
		sb.append(", rate: ");
		sb.append(myElectricRate);
		sb.append(", cost: ");
		sb.append(myReplacementBulbCost);
		sb.append(']');

		return sb.toString();
	}
}
