package model;

import java.util.HashMap;
import java.util.Map;

public class Project {
   public static final String[] BULB_TYPES = {"Incandescent", "CFL", "LED"};
   public static final int[] BULB_WATTAGES = {60, 14, 8};

   private String myProjectName;
   
   private Map<String, Integer> myBulbs;
   private String myCurrentBulbType;
   private String myReplacementBulbType;
   private int myCurrentBulbWatts;
   private int myReplacementBulbWatts;
   
   private int myNumberOfBulbs;
   private int myHoursUsedPerDay;
   private int myNumberDaysPerWeek;
   private double myElectricRate;
   private double myReplacementBulbCost;

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
   
   public Project(String theProjectName, String theCurrentBulbType, int theCurrentBulbWatts, 
		          String theReplacementBulbType, int theReplacementBulbWatts, 
		          int theNumberOfBulbs, int theHoursUsedPerDay, int theNumberDaysPerWeek,
		          double theElectricRate, double theReplacementBulbCost) {
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
   
   public String getProjectName() {
	   return myProjectName;
   }
   
   public String getCurrentBulbType() {
	   return myCurrentBulbType;
   }
   
   public int getCurrentBulbWatts() {
	   return myCurrentBulbWatts;
   }
   
   public String getReplacementBulbType() {
	   return myReplacementBulbType;
   }
   
   public int getReplacementBulbWatts() {
	   return myReplacementBulbWatts;
   }
   
   public int getNumberOfBulbs() {
	   return myNumberOfBulbs;
   }
   
   public int getHoursUsedPerDay() {
	   return myHoursUsedPerDay;
   }
   
   public int getNumberDaysPerWeek() {
	   return myNumberDaysPerWeek;
   }
   
   public double getElectricRate() {
	   return myElectricRate;
   }
   
   public double getReplacementBulbCost() {
	   return myReplacementBulbCost;
   }
   
   public void setProjectName(String thisProjectName) {
	   myProjectName = thisProjectName;
   }
   
   public boolean setCurrentBulbType(String theCurrentBulbType) {
	   if (!checkBulb(theCurrentBulbType)) {
		   return false;
	   }
	   myCurrentBulbType = theCurrentBulbType;
	   return true;
   }
   
   public void setCurrentBulbWatts(int theCurrentBulbWatts) {
	   myCurrentBulbWatts = theCurrentBulbWatts;
   }
   
   public boolean setReplacementBulbType(String theReplacementBulbType) {
	   if (!checkBulb(theReplacementBulbType)) {
		   return false;
	   }
	   myReplacementBulbType = theReplacementBulbType;
	   return true;
   }
   
   public void setReplacementBulbWatts(int theReplacementBulbWatts) {
	   myReplacementBulbWatts = theReplacementBulbWatts;
   }
   
   public boolean setNumberOfBulbs(int theNumberOfBulbs) {
	   if (theNumberOfBulbs <= 0) {
		   return false;
	   }
	   myNumberOfBulbs = theNumberOfBulbs;
	   return true;
   }
   
   public void setHoursUsedPerDay(int theHoursUsedPerDay) {
	   myHoursUsedPerDay = theHoursUsedPerDay;
   }
   
   public void setNumberDaysPerWeek(int theNumberDaysPerWeek) {
	   myNumberDaysPerWeek = theNumberDaysPerWeek;
   }
   
   public boolean setElectricRate(double theElectricRate) {
	   if (theElectricRate <= .05 || theElectricRate >= .4) {
		   return false; //Hawaii has highest rate @ ~.35, WA lowest ~.07
	   }
	   myElectricRate = theElectricRate;
	   return true;
   }
   
   public void setReplacementBulbCost(double theReplacementBulbCost) {
	   myReplacementBulbCost = theReplacementBulbCost;
   }

   public void setupBulbs() {
      myBulbs = new HashMap<String, Integer>();

      for (int i = 0; i < BULB_TYPES.length; i++) {
         myBulbs.put(BULB_TYPES[i], BULB_WATTAGES[i]);
      }
   }

   public double currentAnnualCost() {
	   return annualCost(myCurrentBulbType);
   }

   public double replacementAnnualCost() {
	   return annualCost(myReplacementBulbType);
   }
   
   public double returnPerYear() {
	   return currentAnnualCost() - replacementAnnualCost();
   }
   
   public double payoffYears() {
	   return (myNumberOfBulbs * myReplacementBulbCost) / returnPerYear();
   }
   
   public double initialCost() {
	   return myNumberOfBulbs * myReplacementBulbCost;
   }
   
   private double annualCost(String theBulbType) {
	   int hoursPerYear = myHoursUsedPerDay * myNumberDaysPerWeek * 56;
	   double costPerHour = myBulbs.get(theBulbType) / 1000.0 * myElectricRate;
	   return hoursPerYear * costPerHour * myNumberOfBulbs;
   }
   
private boolean checkBulb(String theBulbType) {
	for (String b : BULB_TYPES) {
		if (b.equals(theBulbType)) {
			return true;
		}
	}
	return false;
}

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
