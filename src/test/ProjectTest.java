/**
 * TCSS 360 - Deliverable 3
 */

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Project;

/**
 * Tests of the Project class.
 *
 * @author Nikolai Carlson - editing
 * @version 8 June 2017
 */
public class ProjectTest {
	/** The project I will be using in all of my tests. */
	private static Project myProject;

	/**
	 * Nothing to set up here, unsure if I can get rid of it and everything will still work.
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Sets up the project to its default state before every test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myProject = new Project("ProjectTest");
	}

	/**
	 * Tests the getter and setter for the projects name.
	 */
	@Test
	public void testSetProjectName() {
		myProject.setProjectName("ProjectNameChange");
		assertEquals("ProjectNameChange", myProject.getProjectName());
	}
	
	/**
	 * Tests the getter and setter for the current bulb type.
	 */
	@Test
	public void testSetCurrentBulbType() {
		myProject.setCurrentBulbType("CFL");
		assertEquals("CFL", myProject.getCurrentBulbType());
	}
	
	/**
	 * Tests the getter and setter for the replacement bulb type.
	 */
	@Test
	public void testSetReplacementBulbType() {
		myProject.setReplacementBulbType("CFL");
		assertEquals("CFL", myProject.getReplacementBulbType());
	}
	
	/**
	 * Tests to see if check bulb is filtering out not bulbs.
	 */
	@Test
	public void testCheckBulb() {
		myProject.setReplacementBulbType("Cthulhu");
		assertEquals("LED", myProject.getReplacementBulbType());
	}

	/**
	 * Tests the getter and setter for number of bulbs with a valid value.
	 */
	@Test
	public void testSetNumberOfBulbs() {
		boolean correct = myProject.setNumberOfBulbs(37);
		assertEquals(37, myProject.getNumberOfBulbs());
		assertEquals(true, correct);
	}
	
	/**
	 * Tests the getter and setter for number of bulbs with an invalid value.
	 */
	@Test
	public void testSetNumberOfBulbsError() {
		boolean incorrect = myProject.setNumberOfBulbs(-37);
		assertEquals(12, myProject.getNumberOfBulbs());
		assertEquals(false, incorrect);
	}
	
	/**
	 * Tests the getter and setter for number of days per week.
	 */
	@Test
	public void testSetNumberDaysPerWeek() {
		myProject.setNumberDaysPerWeek(37);
		assertEquals(37, myProject.getNumberDaysPerWeek());
	}
	
	/**
	 * Tests the getter and setter for electric rate for a valid value.
	 */
	@Test
	public void testSetElectricRate() {
		boolean correct = myProject.setElectricRate(0.17);
		assertEquals(0.17, myProject.getElectricRate(), 0.005);
		assertEquals(true, correct);
	}
	
	/**
	 * Tests the getter and setter for electric rate for an invalid value.
	 */
	@Test
	public void testSetElectricRateError() {
		boolean incorrect = myProject.setElectricRate(0.47);
		boolean alsoIncorrect = myProject.setElectricRate(-0.37);
		assertEquals(0.12, myProject.getElectricRate(), 0.005);
		assertEquals(false, incorrect);
		assertEquals(false, alsoIncorrect);
	}
	
	/**
	 * Tests the getter and setter for replacement bulb cost.
	 */
	@Test
	public void testSetReplacementBulbCost() {
		myProject.setReplacementBulbCost(-37.37);
		assertEquals(-37.37, myProject.getReplacementBulbCost(), 0.005);
	}
	
	/**
	 * Tests the initial cost method.
	 */
	@Test
	public void testInitialCost() {
		assertEquals(299.88, myProject.initialCost(), 0.005);
	}
	
	/**
	 * Tests to see if the payoffYears method is working, which if it is working, then
	 * hopefully the method that it is using are also working correctly.
	 */
	@Test
	public void testPayoffYears() {
		assertEquals(2.55, myProject.payoffYears(), 0.005);
	}
	
	/**
	 * Tests the toString function of project. 
	 */
	@Test
	public void testToString() {
		String printed = "ProjectTest[Incandescent->LED, 60->8, #: 12, h/d: 4, d/wk: 7, rate: 0.12, cost: 24.99]";
		assertEquals(printed, myProject.toString());
	}
	
}
