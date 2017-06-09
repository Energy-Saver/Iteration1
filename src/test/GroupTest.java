/**
 * TCSS 360 - Deliverable 3
 */

package test;

import static org.junit.Assert.*;

import java.io.File;

import model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests of the Group class.
 * 
 * @author Nikolai Carlson - coding
 * @author Lola Howell - editing
 * @version 8 June 2017
 */
public class GroupTest {
	/** Group used for testing. */
	private static Group myGroup;

	/**
	 * Unused class setup.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Initializes testing with imported group data from JUnitTests txt file.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		myGroup = new Group();
		myGroup.importGroup(new File("JUnitTests.txt"));
	}

	/**
	 * Tests importing users.
	 */
	@Test
	public void testImportUsers() {
		assertEquals(5, myGroup.groupSize());
	}
	
	/**
	 * Tests adding users.
	 */
	@Test
	public void testAddUsers() {
		myGroup.signUp("firstAddedUser", "testing@gmail.com");
		myGroup.signUp("secondAddedUser", "testing2@hotmail.com");
		assertEquals(7, myGroup.groupSize());
	}
	
	/**
	 * Tests getting a user.
	 */
	@Test
	public void testGetUser() {
		User u = myGroup.getUser("Keegan", "lumen@ous.com");
		assertEquals("Keegan", u.getFirstName());
	}
	
	/**
	 * Tests logging in.
	 */
	@Test
	public void testLogin() {
		myGroup.login("Keegan", "lumen@ous.com");
		assertEquals("Keegan", myGroup.getCurrentUserName());
	}
	
	/**
	 * Tests state while no user is logged in.
	 */
	@Test
	public void testEmptyCurrentUser() {
		assertEquals(null, myGroup.getCurrentUserName());
	}

}
