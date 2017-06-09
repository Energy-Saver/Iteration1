/**
 * TCSS 360 - Deliverable 3
 */
package view;

import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class StartPanel is the main panel used in the frame.
 * It is updated and redrawn at various points.
 *
 * Creative Commons CC0 licensed arrow image from:
 * http://www.iconarchive.com/show/crystal-clear-icons-by-everaldo/App-energy-star-icon.html
 * 
 * GPL licensed header theme image from:
 * https://wordpress.org
 *
 * @author Keegan Kell - coding/editing
 * @author Lola Howell - coding/editing
 * @author Darren Carpenter - editing
 * @version 8 June 2017
 */
public class StartPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5938682207504409404L;

	/** RGB Energy Saver Blue #5684c5 */
	private static final Color ES_BLUE = new Color(86, 132, 197);
	
    /** Default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
    
    /** Margin for chart. */
    private static final int MARGIN = 50;
    
    /** Tick Length for chart. */
    private static final int TICK_LENGTH = 5;
    
    /** Percent space below the bottom of the chart. */
    private static final double PERCENT_SPACE_BOTTOM = 0.35;
    
    /** The frame. */
    private JFrame myFrame;
    
    /** The group. */
    private Group myGroup;
    
    /** The image used in the frame. */
    private BufferedImage myImage;
    
    /** Represents what state the panel should display. */
    public String myPanel;
    
    /** South panel used for buttons. */
    private JPanel mySouthPanel;

    /**
     * Constructs the panel with theFrame and theGroup.
     * @param theFrame frame used to display panel
     * @param theGroup group of users for the program
     */
    public StartPanel(JFrame theFrame, Group theGroup) {
        super();

        myFrame = theFrame;
        myGroup = theGroup;
        myPanel = "start";
        mySouthPanel = new JPanel();;

        setup();
    }
    
    /**
     * Sets up the panel.
     */
    private void setup() {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        addStartComponents();
//attempting jar fix
//        URL url = EnergySaverMain.class.getResource("/icons/title.png");
//        try {
//			myImage = ImageIO.read(url);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        
        File f = new File("icons/title.png");
        try {
            myImage = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds components to the program's starting panel state.
     */
    private void addStartComponents() {
    	setLayout(new BorderLayout());
    	
    	JButton bLogin = createButton("Login");
    	JButton bSignup = createButton("Signup");
    	JButton bContinue = createButton("Continue");
    	
    	bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (showLogin()) {
            		enableHistory();
            	}
            }
        });
    	
    	bSignup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (showSignup()) {
            		enableHistory();
            	}
            }
        });

    	addNewProjectListener(bContinue);
    	
    	mySouthPanel.add(bLogin);
    	mySouthPanel.add(bSignup);
    	mySouthPanel.add(bContinue);
    	mySouthPanel.setBackground(Color.WHITE);
    	
        this.add(mySouthPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds project listener for theButton with new project action behavior.
     * @param theButton button to add listener to
     */
    private void addNewProjectListener(JButton theButton) {
    	if (theButton.getText().equals("Continue")) {
    		myGroup.guestUser();
    	}
    	
    	theButton.addActionListener(new ActionListener() {
    		/**
        	 * Opens a new project while using a 'guest' user.
        	 * @param theEvent continue button event
        	 */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                removeAll();
                revalidate();
                myPanel = "newProject";
                repaint();
                myGroup.getCurrentUser().createNewProject("default"); //change after save
                
                addNewProjectComponents();
            }
        });
    }
    
    /**
     * Enables the load project button and sets state of panel once user is signed in.
     */
    private void enableHistory() {
    	if (myGroup.getCurrentUser() != null && !myGroup.getCurrentUser().getFirstName().equals("guest")) {
    		JButton bLoad = createButton("Load Project");
    		JButton bNew = createButton("New Project");
    		addLoadProjectListener(bLoad);
    		addNewProjectListener(bNew);
    		mySouthPanel = new JPanel();
    		removeAll();
    		revalidate();
    		repaint();
    		mySouthPanel.add(bLoad);
    		mySouthPanel.add(bNew);
    		mySouthPanel.setBackground(Color.WHITE);
    		add(mySouthPanel, BorderLayout.SOUTH);
    	}
    }
    
    /**
     * Creates and returns a blue button with white font and given theLabel.
     * @param theLabel
     * @return
     */
    private JButton createButton(String theLabel) {
    	JButton b = new JButton(theLabel);
    	b.setBackground(ES_BLUE);
        b.setForeground(Color.WHITE);
        return b;
    }
    
    /**
     * Restarts the panel to starting state.
     */
    protected void restart() {
    	myPanel = "start";
    	removeAll();
    	mySouthPanel = new JPanel();
    	if (myGroup.getCurrentUser() != null && !myGroup.getCurrentUser().getFirstName().equals("guest")) {
    		enableHistory();
    	} else if (myGroup.getCurrentUser() == null || myGroup.getCurrentUser().getFirstName().equals("guest")) {
    		addStartComponents();
    	}

    	revalidate();
    	repaint();
    }
    
    /**
     * Adds project listener for theButton with load project action behavior.
     * @param theButton button to add listener to
     */
    private void addLoadProjectListener(JButton theButton) {
    	theButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                removeAll();
                revalidate();
                myPanel = "newProject";
                repaint();
                
                DefaultListModel<String> dlm = new DefaultListModel<String>();
                
                //for debugging:
                //System.out.println("User's Projects: ");
                //System.out.println(myGroup.getCurrentUser().getProjectNames());
                
                for (String s : myGroup.getCurrentUser().getProjectNames()) {
                	dlm.addElement(s);
                }
                JList<String> list = new JList<>(dlm);
                
                
                myPanel = "loadSelect";
                
                setLayout(new BorderLayout());
                add(new JScrollPane(list));
                
                Container c = new Container();
                c.setLayout(new FlowLayout());
                
                JButton bOpen = createButton("Open");
                bOpen.addActionListener(new ActionListener() {
                	/**
                	 * Opens currently selected project.
                	 * @param theEvent open button event
                	 */
                    @Override
                    public void actionPerformed(final ActionEvent theEvent) {
                    	myGroup.getCurrentUser().setCurrentProject(list.getSelectedValue());
                    	removeAll();
                    	revalidate();
                    	repaint();
                    	addNewProjectComponents();
                    	
                    }
                });
                
                JButton bDelete = createButton("Delete");
                bDelete.addActionListener(new ActionListener() {
                	/**
                	 * Deletes currently selected project.
                	 * @param theEvent delete button event
                	 */
                    @Override
                    public void actionPerformed(final ActionEvent theEvent) {
                    	myGroup.getCurrentUser().deleteProject(list.getSelectedValue());
                    	dlm.removeElementAt(list.getSelectedIndex());
                    }
                });
                
                c.add(bOpen);
                c.add(bDelete);
                add(c, BorderLayout.SOUTH);
            }
        });
    }
    
    /**
     * Shows login dialog box to parse name & email.
     */
    private boolean showLogin() {
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

        JOptionPane.showMessageDialog(myFrame, p, "Login", JOptionPane.QUESTION_MESSAGE);

        myGroup.login(userName.getText(), userEmail.getText());
        
        if (myGroup.getUser(userName.getText(), userEmail.getText()) == null) {
        	System.out.printf("User %s does not exist with email %s.\n", 
        					  userName.getText(), userEmail.getText());
        	return false;
        } else {
        	System.out.printf("User %s logged in succesfully.\n", 
					  		  userName.getText(), userEmail.getText());
        	return true;
        }
    }
    
    /**
     * Shows signup dialog box to parse name & email.
     */
    private boolean showSignup() {
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

        JOptionPane.showMessageDialog(myFrame, p, "Signup", JOptionPane.QUESTION_MESSAGE);

        myGroup.signUp(userName.getText(), userEmail.getText());
        
        return true;
    }
    
    /**
     * Draws images, graphs, text, etc. when appropriate.
     * @param theGraphics the pen
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
    	super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        int panelW = (int) (myFrame.getWidth() / 2.0);
        int panelH = (int) (myFrame.getHeight() / 2.0);
        
        if (myPanel.equals("start")) {    	
        	File f = new File("icons/titleIntro.png");
            try {
                myImage = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	g2d.drawImage(myImage, 25, 25, this);
        } else if (myPanel.equals("newProject")){
        	File f = new File("icons/blurArrow.png");
            try {
                myImage = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	g2d.drawImage(myImage, panelW + 25, 0, this);
        } else if (myPanel.equals("showResult")){
            int graphW = panelW - 2 * MARGIN;
            int graphH = (int) (panelH - MARGIN - PERCENT_SPACE_BOTTOM * panelH);
            int offsW = (int) (myFrame.getWidth() / 2.0);
            
            for (int i = 0; i < 4; i++) {
            	g2d.drawLine(offsW + MARGIN - TICK_LENGTH, (int) (MARGIN + i * 0.25 * graphH), 
            			     offsW + MARGIN, (int) (MARGIN + i * 0.25 * graphH));
            	g2d.drawLine((int) (offsW + MARGIN + (i + 1) * graphW * 0.25), MARGIN + graphH, 
            				 (int) (offsW + MARGIN + (i + 1) * graphW * 0.25), MARGIN + graphH + TICK_LENGTH);
            }
            
            int halfHeight = (int)(MARGIN + graphH * 0.5);
            int halfWidth = (int)(MARGIN + graphW * 0.5);
            
            g2d.setColor(Color.BLUE);
            g2d.drawLine((int) (offsW + MARGIN + graphW * 0.5), MARGIN + graphH, 
            		     (int) (offsW + MARGIN + graphW * 0.5), halfHeight);
            
            g2d.setColor(Color.WHITE);
            for (int i = 0; i < graphH * 0.1; i++) {
            	g2d.drawLine((int) (offsW + MARGIN + graphW * 0.5), halfHeight + i * 5, 
            			     (int) (offsW + MARGIN + graphW * 0.5), halfHeight + i * 5 + 2);
            }
            
            g2d.setColor(Color.GREEN);
            g2d.drawLine(offsW + MARGIN, MARGIN, offsW + MARGIN + graphW, MARGIN + graphH);
            g2d.setColor(Color.RED);
            g2d.drawLine(offsW + MARGIN, MARGIN + graphH, offsW + MARGIN + graphW, MARGIN);
            
            
            DecimalFormat dfDolr = new DecimalFormat("$0.00");
            DecimalFormat dfTime = new DecimalFormat("0.00");
            dfDolr.setMaximumFractionDigits(2);
            dfTime.setMaximumFractionDigits(2);
            
            g2d.setColor(Color.BLACK);
            double initialCost = myGroup.getCurrentUser().getProject().initialCost();
            String stringInitialCost = dfDolr.format(initialCost);
            String stringHalfInitialCost = dfDolr.format(initialCost * 0.5);
            g2d.drawString(stringInitialCost, offsW, MARGIN + 5);
            g2d.drawString(stringHalfInitialCost, offsW, halfHeight + 5);
            
            double theta = 90 * java.lang.Math.PI/180;
            AffineTransform fontAT = new AffineTransform();
            Font theFont = g2d.getFont();
            fontAT.rotate(theta);
            Font theDerivedFont = theFont.deriveFont(fontAT);
            g2d.setFont(theDerivedFont);
            g2d.drawString("cost", offsW + graphW + MARGIN + 5, halfHeight - 5);
            g2d.setFont(theFont);
            
            double payoffYears = myGroup.getCurrentUser().getProject().payoffYears();
            String stringPayoffYears = dfTime.format(payoffYears);
            String stringInversionYears = dfTime.format(payoffYears / 2.0);
            g2d.drawString("years", offsW + halfWidth - 15, MARGIN - 5);
            g2d.drawString(stringPayoffYears, offsW + graphW + 35, MARGIN + graphH + 20);
            g2d.drawString(stringInversionYears, offsW + halfWidth - 10, MARGIN + graphH + 20);
            
            g2d.drawRect(offsW + MARGIN, MARGIN, graphW, graphH);
            
            double cAnnualCost = myGroup.getCurrentUser().getProject().currentAnnualCost();
            double rAnnualCost = myGroup.getCurrentUser().getProject().replacementAnnualCost();
            double rPerYear = myGroup.getCurrentUser().getProject().returnPerYear();
            
            String inversion = String.format("Start making money in                    %10.2f years", payoffYears / 2.0);
            String payoff = String.format("Payoff entire installation in             %10.2f years", payoffYears);
            String currentAnnualCost = String.format("Annual cost current                       $%.2f", cAnnualCost);
            String replacementAnnualCost = String.format("Annual cost replacement               $%.2f", rAnnualCost);
            String returnPerYear = String.format("Return per year                                $%.2f", rPerYear);
            
            g2d.drawString(inversion, offsW + halfWidth - 125, MARGIN + graphH + 50);
            g2d.drawString(payoff, offsW + halfWidth - 125, MARGIN + graphH + 80);
            g2d.drawString(currentAnnualCost, offsW + halfWidth - 125, MARGIN + graphH + 110);
            g2d.drawString(replacementAnnualCost, offsW + halfWidth - 125, MARGIN + graphH + 140);
            g2d.drawString(returnPerYear, offsW + halfWidth - 125, MARGIN + graphH + 170);
        }
    }
    
    /**
     * Adds data from the current project to the panel.
     */
    private void addNewProjectComponents() {
    	setLayout(new GridLayout(0, 2));
    	Container left = new Container();
    	
    	GridLayout gL = new GridLayout(0, 2);
    	gL.setVgap(10);
    	left.setLayout(gL);
    	
    	Project p = myGroup.getCurrentUser().getProject();
    	
    	//Line 1
    	JLabel lblCurrent = new JLabel("Current Light Bulb");
    	
    	Container combosC = new Container();
    	combosC.setLayout(new GridLayout(0, 2));
    	
    	JComboBox<String> bulbTypes = new JComboBox<String>(Project.BULB_TYPES);
    	bulbTypes.setSelectedIndex(0);
    	addBulbsActionListener(bulbTypes);
    	
    	JComboBox<Integer> bulbWattages = new JComboBox<Integer>();
    	for (int i : Project.BULB_WATTAGES) {
    		bulbWattages.addItem(i);
    	}
    	bulbTypes.setSelectedIndex(0);
    	addWattagesActionListener(bulbWattages);
    	
    	//Line 2
    	JLabel lblReplacement = new JLabel("Replacement Light Bulb");
    	
    	Container combosR = new Container();
    	combosR.setLayout(new GridLayout(0, 2));
    	
    	JComboBox<String> bulbTypes2 = new JComboBox<String>(Project.BULB_TYPES);
    	bulbTypes2.setSelectedIndex(2);
    	addBulbsActionListener(bulbTypes2);
    	
    	JComboBox<Integer> bulbWattages2 = new JComboBox<Integer>();
    	for (int i : Project.BULB_WATTAGES) {
    		bulbWattages2.addItem(i);
    	}
    	bulbWattages2.setSelectedIndex(2);
    	addWattagesActionListener(bulbWattages2);
    	
    	JLabel lblNumBulbs = new JLabel("Number of Light Bulbs");
    	JTextField fieldNumBulbs = new JTextField(Integer.toString(p.getNumberOfBulbs()));
    	
    	JLabel lblHrsPerDay = new JLabel("Hours Used per Day");
    	JTextField fieldHrsPerDay = new JTextField(Integer.toString(p.getHoursUsedPerDay()));
    	
    	JLabel lblDaysWk = new JLabel("Number of Days per Week");
    	JTextField fieldDaysWk = new JTextField(Integer.toString(p.getNumberDaysPerWeek()));
    	
    	JLabel lblCostKWH = new JLabel("Electricity Cost per KWH");
    	JTextField fieldCostKWH = new JTextField(Double.toString(p.getElectricRate()));
    	
    	JLabel lblReplBulbCost = new JLabel("Replacement Bulb Cost");
    	JTextField fieldReplBulbCost = new JTextField(Double.toString(p.getReplacementBulbCost()));
    	
    	//blank space, invisible items
    	String[] b = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
    	JLabel[] labels = new JLabel[b.length];
    	for (int i = 0; i < b.length; i++) {
    		labels[i] = new JLabel(b[i]);
    		labels[i].setVisible(false);
    		left.add(labels[i]);
    	}
    	
    	for (int i = 0; i < (int) (b.length * 0.5); i++) {
    		left.add(labels[i]);
    	}
    	
    	combosC.add(bulbTypes);
    	combosC.add(bulbWattages);
    	
    	combosR.add(bulbTypes2);
    	combosR.add(bulbWattages2);
    	
    	left.add(lblCurrent);
    	left.add(combosC);
    	
    	left.add(lblReplacement);
    	left.add(combosR);
    	
    	left.add(lblNumBulbs);
    	left.add(fieldNumBulbs);
    	
    	left.add(lblHrsPerDay);
    	left.add(fieldHrsPerDay);
    	
    	left.add(lblDaysWk);
    	left.add(fieldDaysWk);
    	
    	left.add(lblCostKWH);
    	left.add(fieldCostKWH);
    	
    	left.add(lblReplBulbCost);
    	left.add(fieldReplBulbCost);
    	
    	for (int i = 0; i < (int) (b.length * 0.5); i++) {
    		left.add(labels[i]);
    	}
    	
    	JLabel blank = new JLabel();
    	blank.setVisible(false);
    	left.add(blank);
    	
    	JButton bCalculate = createButton("Calculate");
    	bCalculate.addActionListener(new ActionListener() {
    		/**
        	 * Calculates the data of the current project.
        	 * @param theEvent calculate button event
        	 */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	String currentType = (String) bulbTypes.getSelectedItem();
            	int currentWatts = (int) bulbWattages.getSelectedItem();
            	
            	String replacementType = (String) bulbTypes2.getSelectedItem();
            	int replacementWatts = (int) bulbWattages2.getSelectedItem();
            	
            	int numBulbs = Integer.parseInt(fieldNumBulbs.getText());
            	int numHours = Integer.parseInt(fieldHrsPerDay.getText());
            	int numDays = Integer.parseInt(fieldDaysWk.getText());
            	double numRate = Double.parseDouble(fieldCostKWH.getText());
            	double numRepl = Double.parseDouble(fieldReplBulbCost.getText());
            	
            	myGroup.getCurrentUser().getProject().setCurrentBulbType(currentType);
            	myGroup.getCurrentUser().getProject().setCurrentBulbWatts(currentWatts);
            	myGroup.getCurrentUser().getProject().setReplacementBulbType(replacementType);
            	myGroup.getCurrentUser().getProject().setReplacementBulbWatts(replacementWatts);
            	
            	myGroup.getCurrentUser().getProject().setNumberOfBulbs(numBulbs);
            	myGroup.getCurrentUser().getProject().setHoursUsedPerDay(numHours);
            	myGroup.getCurrentUser().getProject().setNumberDaysPerWeek(numDays);
            	myGroup.getCurrentUser().getProject().setElectricRate(numRate);
            	myGroup.getCurrentUser().getProject().setReplacementBulbCost(numRepl);
            	
            	myPanel = "showResult";
            	modifyResultUI(left, bCalculate);
            }
    	});
    	left.add(bCalculate);
    	
    	add(left);
    }
    
    /**
     * Modifies the user interface to show discard and save buttons in the south panel.
     * @param theContainer container for to remove the passed button from
     * @param theFirstButton the button to remove
     */
    private void modifyResultUI(Container theContainer, JButton theFirstButton) {
    	theContainer.remove(theFirstButton);
    	JButton discard = createButton("Discard");
    	discard.addActionListener(new ActionListener() {
    		/**
        	 * Discards the current project.
        	 * @param theEvent discard button event
        	 */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	myGroup.getCurrentUser().deleteProject();
            	restart();
            }
        });
    	JButton save = createButton("Save Project");
    	save.addActionListener(new ActionListener() {
    		/**
        	 * Saves the current project, prompts for name.
        	 * @param theEvent save project button event
        	 */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGroup.getCurrentUser().getProjectName().equals("default")) {
            		String name = JOptionPane.showInputDialog("Enter project name: ");
            		while (name.equals("")) {
            		name = JOptionPane.showInputDialog("Enter project name: ");
            		}
            		name = name.replaceAll("\\s+", "");
            		myGroup.getCurrentUser().getProject().setProjectName(name);
            		restart();
            		myGroup.getCurrentUser().clearCurrentProject();
            		//for debugging:
            		//System.out.println("projects: ");
            		//System.out.println(myGroup.getCurrentUser().getProjectNames());
            	} else {
            		restart();
            		myGroup.getCurrentUser().clearCurrentProject();
            		//for debugging:
            		//System.out.println("projects: ");
            		//System.out.println(myGroup.getCurrentUser().getProjectNames());
            	}
            }
        });

    	mySouthPanel = new JPanel();
    	revalidate();
    	repaint();
    	setLayout(new BorderLayout());
    	mySouthPanel.add(discard);
    	mySouthPanel.add(save);
    	mySouthPanel.setBackground(Color.WHITE);
    	add(mySouthPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds action listener to bulbs' theComboBox (for future behavior).
     * @param theComboBox combo box to add the listener to
     */
    private void addBulbsActionListener(JComboBox<String> theComboBox) {
    	theComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	//define later
            }
        });
    }
    
    /**
     * Adds action listener to wattages' theComboBox (for future behavior).
     * @param theComboBox combo box to add the listener to
     */
    private void addWattagesActionListener(JComboBox<Integer> theComboBox) {
    	theComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	//define later
            }
        });
    }
}
