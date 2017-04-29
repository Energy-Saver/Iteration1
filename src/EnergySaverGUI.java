/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import java.awt.BorderLayout;
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
    private static final String TITLE = "TCSS 360 - Iteration 1";

    /** The window for this GUI. */
    private final JFrame myFrame;

    /**
     * Constructs the Graphical User Interface and sets the frame title.
     */
    public EnergySaverGUI() {
        myFrame = new JFrame(TITLE);
        setup();
    }

    /**
     * Sets up the GUI.
     */
    private void setup() {
        //final EnergySaverToolBar toolBar = new EnergySaverToolBar();
        final LayoutPanel panel = new LayoutPanel();
        final EnergySaverMenuBar menuBar = new EnergySaverMenuBar(myFrame, panel);

        myFrame.setJMenuBar(menuBar);
        //myFrame.add(toolBar, BorderLayout.SOUTH);
        myFrame.add(panel, BorderLayout.CENTER);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            myFrame.setIconImage(ImageIO.read(new File("estar-small-icon.png")));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        myFrame.pack();
        myFrame.setLocationRelativeTo(null); // change to better center
        myFrame.setVisible(true);
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
