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

    /** User's email. */
    private String myEmail;

    /**
     * Constructs the user.
     */
    public User(String theFirstName, String theEmail) {
        myFirstName = theFirstName;
        myEmail = theEmail;
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
     * Returns email of user.
     *
     * @return email of user
     */
    public String getEmail() {
        return myEmail;
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
        sb.append(", Email: ");
        sb.append(myEmail);
        sb.append(']');
        return sb.toString();
    }

}
