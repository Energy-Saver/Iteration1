/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
package view;

import model.*;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class EnergySaverMain starts the gui as well as sets the look and feel to metal.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public final class EnergySaverMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     *
     * @throws IllegalStateException if called
     */
    private EnergySaverMain() {
        throw new IllegalStateException();
    }

    /**
     * Sets the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }
    }

    /**
     * The main method, invokes the EnergySaver GUI. Command line arguments are ignored.
     *
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new EnergySaverGUI();
            }
        });
        
        User u1 = new User("Keegan", "keegankell@gmail.com");
        u1.createNewProject("proj1");

        Project p1 = new Project("First Project");
        System.out.println(p1);
        System.out.printf("Old cost per year: %f\n", p1.oldAnnualCost());
        System.out.printf("New cost per year: %f\n", p1.newAnnualCost());
        System.out.printf("Return per year: %f\n", p1.returnPerYear());
        System.out.printf("Payoff years:: %f\n", p1.payoffYears());
        
    }

    /**
     * Returns string representation of what EnergySaverMain does, namely it starts the program
     * and sets the look and feel.
     *
     * @return string representation of EnergySaverMain
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(50);
        sb.append("Starts program with Look and Feel: ");
        sb.append(UIManager.getLookAndFeel());
        return sb.toString();
    }

}
