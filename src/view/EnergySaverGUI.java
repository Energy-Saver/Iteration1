/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
package view;

import model.*;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * The Graphical User Interface for the EnergySaver program.
 *
 * GNU Licensed icon from:
 * http://www.iconarchive.com/show/crystal-clear-icons-by-everaldo/App-energy-star-icon.html
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class EnergySaverGUI {
    /** Title for the JFrame. */
    private static final String TITLE = "TCSS 360";

    /** The window for this GUI. */
    private final JFrame myFrame;
    
    private Group myGroup;

    /**
     * Constructs the Graphical User Interface and sets the frame title.
     */
    public EnergySaverGUI() {
        myFrame = new JFrame(TITLE);
        myGroup = new Group();
        setup();
    }

    /**
     * Sets up the GUI.
     */
    private void setup() {
    	//myGroup.signUp("Keegan", "lumen@ous.com");
        //myGroup.getCurrentUser().createNewProject("first project");
        
        //final ProjectPanel pp = new ProjectPanel(myFrame, myGroup);
        final StartPanel sp = new StartPanel(myFrame, myGroup/*, pp*/);
        final EnergySaverMenuBar menuBar = new EnergySaverMenuBar(myFrame, myGroup, sp);
        
        myFrame.add(sp);
        myFrame.setJMenuBar(menuBar);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            myFrame.setIconImage(ImageIO.read(new File("icons/estar-small-icon.png")));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        myFrame.pack();
        myFrame.setLocationRelativeTo(null); // change to better center
        myFrame.setVisible(true);
        myFrame.setMinimumSize(new Dimension(600, 400));
        myFrame.setResizable(false);
    }

    /**
     * Returns string representation of EnergySaverGUI, namely the title of the JFrame.
     *
     * @return string representation of EnergySaverGUI, namely the title of the JFrame
     */
    @Override
    public String toString() {
        return TITLE;
    }

}
