/**
 * TCSS 360 - Iteration1: PowerPaint
 */
package view;

//https://pixabay.com/en/arrow-dashed-pattern-colorful-2207745/

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
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {
	private static final Color ES_BLUE = new Color(86, 132, 197);
	
    /** Default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
    
    private static final int MARGIN = 50;
    private static final int TICK_LENGTH = 5;
    private static final double PERCENT_SPACE_BOTTOM = 0.35;
   
    private JFrame myFrame;
    private Group myGroup;
    private BufferedImage myImage;
    public String myPanel;
    private JPanel mySouthPanel;
    private JButton myToggledButton;

    /**
     * Constructs PaintPanel with default values and sets up the panel.
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
     * Helper method for setting up PaintPanel.
     */
    private void setup() {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        addStartComponents();
        
        File f = new File("icons/title.png");
        try {
            myImage = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //diy energy saver
    //what this program is for
    //if you have an account, login
    //if you would do not care to save, continue to calculations
    //if ... signup
    //picture on left
    //blue: #5684c5
    private void addStartComponents() {
    	setLayout(new BorderLayout());

    	JButton bLogin = createButton("Login");
    	JButton bSignup = createButton("Signup");
    	JButton bContinue = createButton("Continue");
    	
    	bLogin.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (showLogin()) {
            		enableHistory();
            	}
            }
        });
    	
    	bSignup.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
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
    
    private void addNewProjectListener(JButton theButton) {
    	if (theButton.getText().equals("Continue")) {
    		myGroup.guestUser();
    	}
    	
    	theButton.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                removeAll();
                revalidate();
                myPanel = "newProject";
                repaint();
                System.out.println(myGroup.getCurrentUser());
                myGroup.getCurrentUser().createNewProject("dos"); // CORRECT
                
                addNewProjectComponents();
                
                //myFrame.add(new ProjectPanel(myFrame, myGroup)); WRONG-nullPointer
            	
            }
        });
    }
    
    private void enableHistory() {
    	JButton bLoad = createButton("Load Project");
    	JButton bNew = createButton("New Project");
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
    
    private JButton createButton(String theLabel) {
    	JButton b = new JButton(theLabel);
    	b.setBackground(ES_BLUE);
        b.setForeground(Color.WHITE);
        return b;
    }
    
    protected void restart() {
    	myPanel = "start";
    	removeAll();
    	revalidate();
    	repaint();
    }
    
    /**
     * Shows login dialog box to retrieve name & email, part of file menu items.
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
     * Shows signup dialog box to collect name & email, part of file menu items.
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
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
    	super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        int panelW = (int) (myFrame.getWidth() / 2.0);
        int panelH = (int) (myFrame.getHeight() / 2.0);
        
        if (myPanel.equals("start")) {
        	File f = new File("icons/title.png");
            try {
                myImage = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	g2d.drawImage(myImage, 25, 25, this);
        }else if (myPanel.equals("newProject")){
        	File f = new File("icons/blurArrow.png");
            try {
                myImage = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	g2d.drawImage(myImage, panelW + 25, 0, this);
        } else if (myPanel.equals("showResult")){
        	add(new DrawChart(g2d, myFrame, myGroup));
            
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
    
    private void addNewProjectComponents() {
    	setLayout(new GridLayout(0, 2));
    	Container left = new Container();
    	
    	GridLayout gL = new GridLayout(0, 2);
    	gL.setVgap(10);
    	left.setLayout(gL);
    	
    	JLabel lblCurrent = new JLabel("Current Light Bulb");
    	JButton button2 = new JButton("Button 2");
    	
    	//Line 1
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
    	JTextField fieldNumBulbs = new JTextField();
    	
    	JLabel lblHrsPerDay = new JLabel("Hours Used per Day");
    	JTextField fieldHrsPerDay = new JTextField();
    	
    	JLabel lblDaysWk = new JLabel("Number of Days per Week");
    	JTextField fieldDaysWk = new JTextField();
    	
    	JLabel lblCostKWH = new JLabel("Electricity Cost per KWH");
    	JTextField fieldCostKWH = new JTextField();
    	
    	JLabel lblReplBulbCost = new JLabel("Replacement Bulb Cost");
    	JTextField fieldReplBulbCost = new JTextField();
    	
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
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
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
            	System.out.println(myGroup.getCurrentUser().getProject());
            }
    	});
    	left.add(bCalculate);
    	
    	
    	add(left);
    }
    
    private void modifyResultUI(Container theContainer, JButton theFirstButton) {
    	theContainer.remove(theFirstButton);
    	//theContainer.removeAll();
    	
    	//enableHistory();
    	
    	//theContainer.remove(theSecondButton);
    	JButton discard = createButton("Discard");
    	JButton save = createButton("Save Project");
    	Container c = new Container();
    	mySouthPanel = new JPanel();
    	revalidate();
    	repaint();
    	setLayout(new BorderLayout());
    	mySouthPanel.add(discard);
    	mySouthPanel.add(save);
    	mySouthPanel.setBackground(Color.WHITE);
    	add(mySouthPanel, BorderLayout.SOUTH);
    }
    
    private void addBulbsActionListener(JComboBox<String> theComboBox) {
    	theComboBox.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	//@SuppressWarnings("unchecked")
				//JComboBox<String> cb = (JComboBox<String>)theEvent.getSource();
                //String type = (String)cb.getSelectedItem();
            }
        });
    }
    
    private void addWattagesActionListener(JComboBox<Integer> theComboBox) {
    	theComboBox.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	//@SuppressWarnings("unchecked")
				//JComboBox<Integer> cb = (JComboBox<Integer>)theEvent.getSource();
                //int wattage = (int) cb.getSelectedItem();
                //System.out.println(wattage);
            }
        });
    }
}
