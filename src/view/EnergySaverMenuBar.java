/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
package view;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Class PowerPaintMenuBar is a menu bar for program EnergySaver.
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
public class EnergySaverMenuBar extends JMenuBar {
    /**
     * Fake serialVersionUID to squelch warning.
     */
    private static final long serialVersionUID = -1717799894208755241L;

    /** Paint roller icon for JFrame icon and about message dialog. */
    private static final Icon ROLLER = new ImageIcon("icons/estar-large-icon.png");
    
    private static final String WELCOME = "We welcome all of the DIYers out there and encourage\n"
    		                           + "homeowners to help environment while saving money. \n"
    		                           + "Whether you are interested in insulating your home \n"
    		                           + "or replacing bulbs to reduce the use of energy, we \n"
    		                           + "are here to help you make informed decisions. We hope \n"
    		                           + "that this tool will assist you to successfully assess \n"
    		                           + "your energy consumption and make changes necessary for \n"
    		                           + "energy conservation. So go on and give it a try! After \n"
    		                           + "all there is nothing to loose, it’s a win-win for your \n"
    		                           + "pocket and the environment.";
    
    private static final String HOW_TO = "TUTORIAL\n\n"
    								   + "Export/Import\n"
    								   + "Export/Import ability enables you to export/import all "
    								   + "current group of users as well as their projects.\n\n"
    								   + "Export\n"
    								   + "\t1. Click on “Options” at the top of the window\n"
    								   + "\t2. Click “Export”\n"
    								   + "\t3. Choose file name and location\n"
    								   + "\t4. Click “Save”\n\n"
    								   + "Import\n"
    								   + "\t1. Click on “Options” at the top of the window\n"
    								   + "\t2. Click “Export”\n"
    								   + "\t3. Choose file name and location\n"
    								   + "\t4. Click “Open”\n\n"
    								   + "Projects\n"
    								   + "You can save projects, discard projects as needed, load "
    								   + "desired project and make necessary changes.\nThis feature "
    								   + "is only available to members, therefore you have to sign "
    								   + "up to use it.\n\n"
    								   + "Save\n" 
    								   + "If you have account:\n"
    								   + "\t1. Click “Login”\n"
    								   + "If you don’t have account:\n"
    								   + "\t2. Sign Up with your name and email\n"
    								   + "\t3. Click on “New Project”\n"
    								   + "\t4. Input the values for calculations\n"
    								   + "\t5. Press “Calculate” button\n"
    								   + "\t6. Click “Save Project” or Click “Discard” to remove it\n"
    								   + "\t7. Enter the name for the Project\n"
    								   + "\t8. Click “OK”\n\n"
    								   + "Load\n"
    								   + "\t1. Click “Login”\n"
    								   + "\t2. Input your name and email\n"
    								   + "\t3. Click on “Load Project”\n"
    								   + "\t4. Click on the desired Project\n"
    								   + "\t5. Click “Open” or “Delete” to remove it\n"
    								   + "\t6. Adjust any values if desired\n"
    								   + "\t7. Click “Calculate”\n";

    /** Description about the program. */
    private static final String ABOUT_PROGRAM = "TCSS 360\n"
                                              + "Software Development & Quality Assurance Techniques\n"
                                              + "SPRING 2017\n\n"
                                              + "DEVELOPED AND DESIGNED BY\n"
                                              + "Alex R. Smith - developer\n"
                                              + "Darren T. Carpenter - front-end developer\n"
                                              + "Nikolai Carlson - developer\n"
                                              + "Keegan J. Kell - developer\n"
                                              + "Lola Howell - UX design / front-end developer\n\n"
                                              + "GNU licensed icon from: \n"
                                              + "http://icons.iconarchive.com\n\n"
                                              + "Creative Commons CC0 licensed arrow image from:\n"
                                              + "https://pixabay.com\n\n"
                                              + "GPL licensed header theme image from:\n"
                                              + "https://wordpress.org";
    
    private final JFrame myFrame;
    //private final StartPanel myStartPanel;

    /** Panel reference (used to pass menuBar values to the panel).
     *  Needed for passing myGroup to panel? */
    private StartPanel myStartPanel;
    
    /** Similar to creating the board in Tetris.*/
    private Group myGroup;

    /** Tools menu on the menu bar, contains the various tools. */
    //private final JMenu myToolsMenu;

    /**
     * Constructs the menu bar.
     *
     * @param theFrame b
     * @param thePanel b
     */
    public EnergySaverMenuBar(final JFrame theFrame, Group theGroup, StartPanel theStartPanel) {
        super();
        myFrame = theFrame;
        myStartPanel = theStartPanel;
        myGroup = theGroup;

        setup(theFrame);
    }

    /**
     * Helps build the menu bar for this GUI.
     *
     * @param theFrame the containing JFrame of this menu bar
     */
    private void setup(final JFrame theFrame) {
        add(buildFileMenu(theFrame));
        add(buildOptionsMenu());
        add(buildHelpMenu());
    }

    /**
     * Builds/returns file menu.
     *
     * @param theFrame the containing JFrame of this menu bar
     * @return a "file" menu with some menu items
     */
    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("Account");
        fileMenu.setMnemonic(KeyEvent.VK_A);

        final JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setMnemonic(KeyEvent.VK_L);
        logoutItem.addActionListener(new ActionListener() {

            /**
             * Logout menuItem event.
             *
             * @param theEvent logout menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	//myStartPanel.restart();
            	//if (myGroup.getCurrentUser().getFirstName().equals("guest")) {
            	//	myGroup.getCurrentUser().deleteProject();
            	//}
            	myGroup.logout();
            	myStartPanel.restart();
            }
        });

        fileMenu.add(logoutItem);

        fileMenu.addSeparator();

        final JMenuItem exitItem = new JMenuItem("Quit");
        exitItem.setMnemonic(KeyEvent.VK_Q);
        exitItem.addActionListener(new ActionListener() {

            /**
             * Closes window upon quit menuItem event.
             *
             * @param theEvent quit menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        final JMenuItem imp = new JMenuItem("Import");
        imp.setMnemonic(KeyEvent.VK_I);
        imp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	JFileChooser fc = new JFileChooser();
            	switch (fc.showOpenDialog(myFrame)) {
            		case JFileChooser.APPROVE_OPTION: //open file
            			myGroup.importGroup(fc.getSelectedFile());
            			break;
            		case JFileChooser.CANCEL_OPTION:
            			break;
            	}
            }
        });
        
        final JMenuItem exp = new JMenuItem("Export");
        exp.setMnemonic(KeyEvent.VK_E);
        exp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	JFileChooser fc = new JFileChooser();
            	switch (fc.showSaveDialog(myFrame)) {
            		case JFileChooser.APPROVE_OPTION: //open file
            			myGroup.exportGroup(fc.getSelectedFile());
            			break;
            		case JFileChooser.CANCEL_OPTION:
            			break;
            	}
            }
        });
        
        optionsMenu.add(imp);
        optionsMenu.add(exp);

        return optionsMenu;
    }

    /**
     * Returns/builds help menu.
     *
     * @return a "help" menu with an "About..." menu item
     */
    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
      //welcome message
        final JMenuItem welcomeItem = new JMenuItem("Welcome");
        welcomeItem.setMnemonic(KeyEvent.VK_W);
        welcomeItem.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,  WELCOME, "Welcome",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        //tutorial for how to use this software
        final JMenuItem howToItem = new JMenuItem("How To");
        howToItem.setMnemonic(KeyEvent.VK_H);
        howToItem.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,  HOW_TO, "How To",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });

        final JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(new ActionListener() {

            /**
             * Shows a dialog screen describing program upon menu selection.
             *
             * @param theEvent menu selection of About...
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null,  ABOUT_PROGRAM, "About Energy Saver",
                                              JOptionPane.PLAIN_MESSAGE, ROLLER);
            }
        });

        helpMenu.add(welcomeItem);
        helpMenu.add(howToItem);
        helpMenu.add(aboutItem);

        return helpMenu;
    }

    /**
     * Returns String representation of menu bar.
     *
     * @return string representation of menu bar
     */
    @Override
    public String toString() {
        return "Account|Options|Help";
    }

}
