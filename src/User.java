/**
 * TCSS 360 - Iteration 1: Product v0.1
 */

/**
 * The user.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class User {
    /** User's first name. */
    private String myFirstName;

    /** User's password. */
    private char[] myPassword;

    /**
     * Constructs the user.
     */
    public User(String theFirstName, char[] thePassword) {
        myFirstName = theFirstName;
        myPassword = thePassword;
    }

    /**
     * Returns first name of user.
     *
     * @return first name of user
     */
    public String getFirstName() {
        return myFirstName;
    }

    /**
     * For testing only, delete before deployment!
     * Returns password of user.
     *
     * @return first name of user
     */
    public char[] getPassword() {
        return myPassword;
    }

    /**
     * Returns string representation of the user.
     *
     * @return string representation of the user
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(85);
        sb.append("[First Name: ");
        sb.append(myFirstName);
        //remove later!!!!!!!
        sb.append(", Password: ");
        for (char c : myPassword) {
            sb.append(c);
        }
        sb.append(']');
        return sb.toString();
    }

}
