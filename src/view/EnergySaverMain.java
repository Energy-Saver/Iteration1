/**
 * TCSS 360 - Deliverable 3
 */
package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class EnergySaverMain starts the gui as well as sets the look and feel to metal.
 *
 * @author Keegan Kell
 * @author Lola Howell
 * @version 8 June 2017
 */
public final class EnergySaverMain {

    /**
     * Private constructor, to prevent instantiation of this class.
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
    }

    /**
     * Returns string representation of what EnergySaverMain does, namely it starts the program
     * and sets the look and feel.
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
