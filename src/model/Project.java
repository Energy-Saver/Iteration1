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

   public void setupBulbs() {
      myBulbs = new HashMap<String, Integer>();

      for (int i = 0; i < BULB_TYPES.length; i++) {
         myBulbs.put(BULB_TYPES[i], BULB_WATTAGES[i]);
      }
   }

   public double oldAnnualCost() {
	   return annualCost(myCurrentBulbType);
   }

   public double newAnnualCost() {
	   return annualCost(myReplacementBulbType);
   }
   
   public double returnPerYear() {
	   return oldAnnualCost() - newAnnualCost();
   }
   
   public double payoffYears() {
	   return (myNumberOfBulbs * myReplacementBulbCost) / returnPerYear();
   }
   
   private double annualCost(String theBulbType) {
	   int hoursPerYear = myHoursUsedPerDay * myNumberDaysPerWeek * 56;
	   double costPerHour = myBulbs.get(theBulbType) / 1000.0 * myElectricRate;
	   return hoursPerYear * costPerHour * myNumberOfBulbs;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(75);
      
      sb.append(myProjectName);
      sb.append('[');
      sb.append(myCurrentBulbType);
      sb.append("->");
      sb.append(myReplacementBulbType);
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
