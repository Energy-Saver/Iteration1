/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the User class.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
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

    /*
     * Test method for default user constructed password.
     */
    //@Test
    //public void testPassword() {
    //    String pw = "superSecret";
    //    System.out.println();
    //    assertArrayEquals(pw.toCharArray(), myUser.getPassword());
    //}

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
