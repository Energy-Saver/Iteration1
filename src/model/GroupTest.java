package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GroupTest {
	
	private static Group myGroup;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		myGroup = new Group();
		myGroup.importGroup();
	}

	@Test
	public void testImportUsers() {
		assertEquals(5, myGroup.groupSize());
	}
	
	@Test
	public void testAddUsers() {
		myGroup.signUp("firstAddedUser", "testing@gmail.com");
		myGroup.signUp("secondAddedUser", "testing2@hotmail.com");
		assertEquals(7, myGroup.groupSize());
	}
	
	@Test
	public void testGetUser() {
		User u = myGroup.getUser("Keegan", "lumen@ous.com");
		assertEquals("Keegan", u.getFirstName());
	}
	
	@Test
	public void testLogin() {
		myGroup.login("Keegan", "lumen@ous.com");
		assertEquals("Keegan", myGroup.getCurrentUserName());
	}
	
	@Test
	public void testEmptyCurrentUser() {
		assertEquals(null, myGroup.getCurrentUserName());
	}

}
