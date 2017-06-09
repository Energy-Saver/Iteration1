/**
 * TCSS 360 - Deliverable 3
 */

package test;

import static org.junit.Assert.assertEquals;

import model.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the User class.
 *
 * @author Alex Smith - editing
 * @author Darren Carpenter - coding
 * @version 8 June 2017
 */
public class UserTest {
    /** A User to use in the tests. */
    private User myUser;

    /**
     * A method to initialize the test fixture before each test.
     */
    @Before
    public void setUp() { // @throws exception (not needed)
        myUser = new User("John Doe", "johndoe@gmail.com");
    }

    /**
     * Test method for default user constructed first name.
     */
    @Test
    public void testFirstName() {
        assertEquals("John Doe", myUser.getFirstName());
    }

    /**
     * Test method for default user constructed email.
     */
    @Test
    public void testEmail() {
        assertEquals("johndoe@gmail.com", myUser.getEmail());
    }

    /**
     * Test method for {@link User#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("toString() produced an unexpedted result!",
                     "[First Name: John Doe, Email: johndoe@gmail.com]",
                     myUser.toString());
    }
}
