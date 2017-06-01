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
      myReplacementBulbType = BULB_TYPES[2];
      myNumberOfBulbs = 12;
      myHoursUsedPerDay = 4;
      myNumberDaysPerWeek = 7;
      myElectricRate = 0.12;
      myReplacementBulbCost = 24.99;

      setupBulbs();
   }
   
   public String getProjectName() {
	   return myProjectName;
   }
   
   public String getCurrentBulbType() {
	   return myCurrentBulbType;
   }
   
   public String getReplacementBulbType() {
	   return myReplacementBulbType;
   }
   
   public int getNumberOfBulbs() {
	   return myNumberOfBulbs;
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
   
   public void setCurrentBulbType(String theCurrentBulbType) {
	   //if is legal bulb type
	   myCurrentBulbType = theCurrentBulbType;
   }
   
   public void setCurrentBulbWatts(int theCurrentBulbWatts) {
	   myCurrentBulbWatts = theCurrentBulbWatts;
   }
   
   public void setReplacementBulbType(String theReplacementBulbType) {
	   //same
	   myReplacementBulbType = theReplacementBulbType;
   }
   
   public void setReplacementBulbWatts(int theReplacementBulbWatts) {
	   myReplacementBulbWatts = theReplacementBulbWatts;
   }
   
   public void setNumberOfBulbs(int theNumberOfBulbs) {
	   //check if positive
	   myNumberOfBulbs = theNumberOfBulbs;
   }
   
   public void setHoursUsedPerDay(int theHoursUsedPerDay) {
	   myHoursUsedPerDay = theHoursUsedPerDay;
   }
   
   public void setNumberDaysPerWeek(int theNumberDaysPerWeek) {
	   myNumberDaysPerWeek = theNumberDaysPerWeek;
   }
   
   public void setElectricRate(double theElectricRate) {
	   //check something
	   myElectricRate = theElectricRate;
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
