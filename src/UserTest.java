/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the User class.
 *
 * @author Keegan Kell keeganjk@uw.edu
 * @version 11 October 2016
 */
public class UserTest {
    /** A User to use in the tests. */
    private User myUser;

    /**
     * A method to initialize the test fixture before each test.
     */
    @Before
    public void setUp() { // @throws exception (not needed)
        String pw = "superSecret";
        myUser = new User("John Doe", pw.toCharArray());
    }

    /**
     * Test method for default user constructed first name.
     */
    @Test
    public void testFirstName() {
        assertEquals("John Doe", myUser.getFirstName());
    }

    /**
     * Test method for default user constructed password.
     */
    @Test
    public void testPassword() {
        String pw = "superSecret";
        assertEquals(pw.toCharArray(), myUser.getPassword());
    }

    /**
     * Test method for {@link User#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("toString() produced an unexpedted result!",
                     "[First Name: John Doe, Password: superSecret]",
                     myUser.toString());
    }
}
