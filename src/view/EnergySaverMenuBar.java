<<<<<<< HEAD:src/view/EnergySaverMenuBar.java
/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
package view;

import model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private static final Icon ROLLER = new ImageIcon("estar-large-icon.png");

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
                                              + "GNU Licensed Icon from: \n"
                                              + "http://icons.iconarchive.com";

    /** Large tick mark spacing value for stroke width. */
    private static final int MAJOR_TICK_SPACING = 5;

    /** Small tick mark spacing value for stroke width. */
    private static final int MINOR_TICK_SPACING = 1;

    /** Panel reference (used to pass menuBar values to the panel). */
    private final LayoutPanel myPanel;

    /** Slider used in the thickness subMenu for stroke size. */
    private final JSlider mySlider;

    /** Tools menu on the menu bar, contains the various tools. */
    private final JMenu myToolsMenu;

    /**
     * Constructs the menu bar.
     *
     * @param theFrame b
     * @param thePanel b
     */
    public EnergySaverMenuBar(final JFrame theFrame, final LayoutPanel thePanel) {
        super();
        myPanel = thePanel;
        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0, myPanel.getMaxStrokeSize(),
                             myPanel.getStrokeSize());
        myToolsMenu = new JMenu("Tools");

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
        add(myToolsMenu);
        add(buildHelpMenu());
    }

    /**
     * Shows signup dialog box to collect name & email, part of file menu items.
     *
     * @param frame the frame (might not be needed)/////////////////////////////////////////
     */
    private void showSignup(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("First Name:", SwingConstants.RIGHT));
        labels.add(new JLabel("Email:", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        JTextField userName = new JTextField();
        JTextField userEmail = new JTextField();
        controls.add(userName);
        controls.add(userEmail);

        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "Signup", JOptionPane.QUESTION_MESSAGE);

        //build data structure for added users somewhere - example only//////////////////////
        User firstUser = new User(userName.getText(), userEmail.getText());
        System.out.println(firstUser);
    }

    /**
     * Builds/returns file menu.
     *
     * @param theFrame the containing JFrame of this menu bar
     * @return a "file" menu with some menu items
     */
    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem signupItem = new JMenuItem("Signup");
        signupItem.setMnemonic(KeyEvent.VK_S);
        signupItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //JOptionPane.showInputDialog(null, "Enter your first name:","Signup",
                //                            JOptionPane.QUESTION_MESSAGE);
                showSignup(theFrame);
            }
        });

        fileMenu.add(signupItem);

        fileMenu.addSeparator();

        final JMenuItem loginItem = new JMenuItem("Login");
        loginItem.setMnemonic(KeyEvent.VK_L);
        loginItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //do login stuff
            }
        });

        fileMenu.add(loginItem);

        final JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setMnemonic(KeyEvent.VK_O);
        logoutItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //do logout stuff
            }
        });

        fileMenu.add(logoutItem);

        fileMenu.addSeparator();

        final JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic(KeyEvent.VK_C);
        clearItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //clear needed?
            }
        });

        fileMenu.add(clearItem);

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

    /**
     * Builds/returns options menu.
     *
     * @return an "options" menu with some menu items
     */
    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        optionsMenu.add(createThicknessMenu());

        optionsMenu.add(buildDrawColorChooser("Draw Color...", myPanel.getMyDrawColor()));
        optionsMenu.add(buildFillColorChooser("Fill Color...", myPanel.getMyFillColor()));

        optionsMenu.addSeparator();

        final JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem("Fill", true);
        checkBox.setToolTipText("When checked, drawn shapes are filled");
        checkBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                                                       KeyEvent.CTRL_DOWN_MASK));
        checkBox.addActionListener(new ActionListener() {

            /**
             * Action listener for checkBox, updates isFilled property.
             *
             * @param theEvent checkBox event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (checkBox.isSelected()) {
                    myPanel.setIsFilled(true);
                } else { //check box not selected
                    myPanel.setIsFilled(false);
                }
            }
        });

        optionsMenu.add(checkBox);

        return optionsMenu;
    }

    /**
     * Builds/returns draw color chooser.
     *
     * @param theName name for the chooser dialog
     * @param theColor color to initially set the draw color chooser
     * @return draw color chooser
     */
    private JMenuItem buildDrawColorChooser(final String theName, final Color theColor) {
        final JMenuItem colorChooserItem = new JMenuItem(theName);
        colorChooserItem.setMnemonic(KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        final ColorIcon customIcon = new ColorIcon(theColor);
        colorChooserItem.setIcon(customIcon);
        colorChooserItem.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, theName,
                                                              myPanel.getMyDrawColor());
                if (result != null) {
                    myPanel.setDrawColor(result);
                    customIcon.setColor(result);
                }
            }
        });

        return colorChooserItem;
    }

    /**
     * Builds/returns fill color chooser.
     *
     * @param theName name for the chooser dialog
     * @param theColor color to initially set the fill color chooser
     * @return fill color chooser
     */
    private JMenuItem buildFillColorChooser(final String theName, final Color theColor) {
        final JMenuItem colorChooserItem = new JMenuItem(theName);
        colorChooserItem.setMnemonic(KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        final ColorIcon customIcon = new ColorIcon(theColor);
        colorChooserItem.setIcon(customIcon);
        colorChooserItem.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates fill color based on user selection.
             *
             * @param theEvent fill color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, theName,
                                                              myPanel.getMyFillColor());
                if (result != null) {
                    myPanel.setFillColor(result);
                    customIcon.setColor(result);
                }
            }
        });

        return colorChooserItem;
    }

      /**
       * Create/returns thickness subMenu with slider for stroke size.
       *
       * @return subMenu containing a thickness slider
       */
    private JMenu createThicknessMenu() {
        final JMenu subMenu = new JMenu("Thickness");
        subMenu.setMnemonic(KeyEvent.VK_T);
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        mySlider.addChangeListener(new ChangeListener() {

            /**
             * Change listener for slider, updates stroke size based on slider's value.
             *
             * @param theEvent slider selection change
             */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myPanel.setStrokeSize(mySlider.getValue());
            }
        });

        subMenu.add(mySlider);

        return subMenu;
    }

    /**
     * Returns/builds help menu.
     *
     * @return a "help" menu with an "About..." menu item
     */
    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

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

        helpMenu.add(aboutItem);

        return helpMenu;
    }

    /**
     * Creates a radio button menu item, associates an action with the button,
     * adds the button to a button group, adds the button to the Tool menu.
     *
     * @param theAction the Action to associate with the new button being created
     */
    public void createToolMenuButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);
        final ButtonGroup toolButtonGroup = new ButtonGroup();
        toolButtonGroup.add(createdButton);
        myToolsMenu.add(createdButton);
    }

    /**
     * Returns String representation of menu bar.
     *
     * @return string representation of menu bar
     */
    @Override
    public String toString() {
        return "File|Options|Tools|Help";
    }

}
=======
/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private static final Icon ROLLER = new ImageIcon("estar-large-icon.png");

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
                                              + "GNU Licensed Icon from: \n"
                                              + "http://icons.iconarchive.com";

    /** Large tick mark spacing value for stroke width. */
    private static final int MAJOR_TICK_SPACING = 5;

    /** Small tick mark spacing value for stroke width. */
    private static final int MINOR_TICK_SPACING = 1;

    /** Panel reference (used to pass menuBar values to the panel). */
    private final LayoutPanel myPanel;

    /** Slider used in the thickness subMenu for stroke size. */
    private final JSlider mySlider;

    /** Tools menu on the menu bar, contains the various tools. */
    private final JMenu myToolsMenu;

    /**
     * Constructs the menu bar.
     *
     * @param theFrame b
     * @param thePanel b
     */
    public EnergySaverMenuBar(final JFrame theFrame, final LayoutPanel thePanel) {
        super();
        myPanel = thePanel;
        mySlider = new JSlider(SwingConstants.HORIZONTAL, 0, myPanel.getMaxStrokeSize(),
                             myPanel.getStrokeSize());
        myToolsMenu = new JMenu("Tools");

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
        add(myToolsMenu);
        add(buildHelpMenu());
    }

    /**
     * Shows signup dialog box to collect name & email, part of file menu items.
     *
     * @param frame the frame (might not be needed)/////////////////////////////////////////
     */
    private void showSignup(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("First Name:", SwingConstants.RIGHT));
        labels.add(new JLabel("Email:", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        JTextField userName = new JTextField();
        JTextField userEmail = new JTextField();
        controls.add(userName);
        controls.add(userEmail);

        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "Signup", JOptionPane.QUESTION_MESSAGE);

        //build data structure for added users somewhere - example only//////////////////////
        User firstUser = new User(userName.getText(), userEmail.getText());
        System.out.println(firstUser);
    }

    /**
     * Builds/returns file menu.
     *
     * @param theFrame the containing JFrame of this menu bar
     * @return a "file" menu with some menu items
     */
    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenuItem signupItem = new JMenuItem("Signup");
        signupItem.setMnemonic(KeyEvent.VK_S);
        signupItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //JOptionPane.showInputDialog(null, "Enter your first name:","Signup",
                //                            JOptionPane.QUESTION_MESSAGE);
                showSignup(theFrame);
            }
        });

        fileMenu.add(signupItem);

        fileMenu.addSeparator();

        final JMenuItem loginItem = new JMenuItem("Login");
        loginItem.setMnemonic(KeyEvent.VK_L);
        loginItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //do login stuff
            }
        });

        fileMenu.add(loginItem);

        final JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setMnemonic(KeyEvent.VK_O);
        logoutItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //do logout stuff
            }
        });

        fileMenu.add(logoutItem);

        fileMenu.addSeparator();

        final JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic(KeyEvent.VK_C);
        clearItem.addActionListener(new ActionListener() {

            /**
             * Clears previous shapes upon clear menuItem event.
             *
             * @param theEvent clear menuItem event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //clear needed?
            }
        });

        fileMenu.add(clearItem);

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

    /**
     * Builds/returns options menu.
     *
     * @return an "options" menu with some menu items
     */
    private JMenu buildOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        optionsMenu.add(createThicknessMenu());

        optionsMenu.add(buildDrawColorChooser("Draw Color...", myPanel.getMyDrawColor()));
        optionsMenu.add(buildFillColorChooser("Fill Color...", myPanel.getMyFillColor()));

        optionsMenu.addSeparator();

        final JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem("Fill", true);
        checkBox.setToolTipText("When checked, drawn shapes are filled");
        checkBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                                                       KeyEvent.CTRL_DOWN_MASK));
        checkBox.addActionListener(new ActionListener() {

            /**
             * Action listener for checkBox, updates isFilled property.
             *
             * @param theEvent checkBox event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (checkBox.isSelected()) {
                    myPanel.setIsFilled(true);
                } else { //check box not selected
                    myPanel.setIsFilled(false);
                }
            }
        });

        optionsMenu.add(checkBox);

        return optionsMenu;
    }

    /**
     * Builds/returns draw color chooser.
     *
     * @param theName name for the chooser dialog
     * @param theColor color to initially set the draw color chooser
     * @return draw color chooser
     */
    private JMenuItem buildDrawColorChooser(final String theName, final Color theColor) {
        final JMenuItem colorChooserItem = new JMenuItem(theName);
        colorChooserItem.setMnemonic(KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        final ColorIcon customIcon = new ColorIcon(theColor);
        colorChooserItem.setIcon(customIcon);
        colorChooserItem.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, theName,
                                                              myPanel.getMyDrawColor());
                if (result != null) {
                    myPanel.setDrawColor(result);
                    customIcon.setColor(result);
                }
            }
        });

        return colorChooserItem;
    }

    /**
     * Builds/returns fill color chooser.
     *
     * @param theName name for the chooser dialog
     * @param theColor color to initially set the fill color chooser
     * @return fill color chooser
     */
    private JMenuItem buildFillColorChooser(final String theName, final Color theColor) {
        final JMenuItem colorChooserItem = new JMenuItem(theName);
        colorChooserItem.setMnemonic(KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        final ColorIcon customIcon = new ColorIcon(theColor);
        colorChooserItem.setIcon(customIcon);
        colorChooserItem.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates fill color based on user selection.
             *
             * @param theEvent fill color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, theName,
                                                              myPanel.getMyFillColor());
                if (result != null) {
                    myPanel.setFillColor(result);
                    customIcon.setColor(result);
                }
            }
        });

        return colorChooserItem;
    }

      /**
       * Create/returns thickness subMenu with slider for stroke size.
       *
       * @return subMenu containing a thickness slider
       */
    private JMenu createThicknessMenu() {
        final JMenu subMenu = new JMenu("Thickness");
        subMenu.setMnemonic(KeyEvent.VK_T);
        mySlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        mySlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        mySlider.setPaintLabels(true);
        mySlider.setPaintTicks(true);
        mySlider.addChangeListener(new ChangeListener() {

            /**
             * Change listener for slider, updates stroke size based on slider's value.
             *
             * @param theEvent slider selection change
             */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myPanel.setStrokeSize(mySlider.getValue());
            }
        });

        subMenu.add(mySlider);

        return subMenu;
    }

    /**
     * Returns/builds help menu.
     *
     * @return a "help" menu with an "About..." menu item
     */
    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

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

        helpMenu.add(aboutItem);

        return helpMenu;
    }

    /**
     * Creates a radio button menu item, associates an action with the button,
     * adds the button to a button group, adds the button to the Tool menu.
     *
     * @param theAction the Action to associate with the new button being created
     */
    public void createToolMenuButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);
        final ButtonGroup toolButtonGroup = new ButtonGroup();
        toolButtonGroup.add(createdButton);
        myToolsMenu.add(createdButton);
    }

    /**
     * Returns String representation of menu bar.
     *
     * @return string representation of menu bar
     */
    @Override
    public String toString() {
        return "File|Options|Tools|Help";
    }

}
>>>>>>> upstream/master:src/EnergySaverMenuBar.java
