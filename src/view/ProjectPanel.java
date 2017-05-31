/**
 * TCSS 360 - Iteration1: PowerPaint
 */
package view;

import model.*;

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
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The JPanel upon which everything will be painted.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class ProjectPanel extends JPanel {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 4433564918619415939L;

    /** Default draw color. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);

    /** Default fill color. */
    private static final Color UW_GOLD = new Color(232, 211, 162);
    
    private static final double PERCENT_SPACE_BOTTOM = 0.35;

    /** Default stroke size. */
    private static final int DEFAULT_STROKE = 1;

    /** Maximum stroke size. */
    private static final int MAX_STROKE_SIZE = 20;

    /** Default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
    
    private static final int MARGIN = 50;
    private static final int TICK_LENGTH = 5;

    /** The color to draw shapes with. */
    private Color myDrawColor;

    /** The color to fill shapes with. */
    private Color myFillColor;

    /** The stroke size used for drawing. */
    private int myStrokeSize;
    
    private JFrame myFrame;
    private Group myGroup;

    /**
     * Constructs PaintPanel with default values and sets up the panel.
     */
    public ProjectPanel(JFrame theFrame, Group theGroup) {
        super();
        myDrawColor = UW_PURPLE;
        myFillColor = UW_GOLD;
        myStrokeSize = DEFAULT_STROKE;
        myFrame = theFrame;
        myGroup = theGroup;

        setup();
    }

    /**
     * Helper method for setting up PaintPanel.
     */
    private void setup() {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        addComponentsTopane();
    }
    
    private void addComponentsTopane() {
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
    	bulbTypes.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)theEvent.getSource();
                String type = (String)cb.getSelectedItem();
            }
        });
    	
    	
    	JComboBox<Integer> bulbWattages = new JComboBox<Integer>();
    	for (int i : Project.BULB_WATTAGES) {
    		bulbWattages.addItem(i);
    	}
    	bulbTypes.setSelectedIndex(0);
    	bulbTypes.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)theEvent.getSource();
                String wattage = (String)cb.getSelectedItem();
            }
        });
    	
    	//Line 2
    	JLabel lblReplacement = new JLabel("Replacement Light Bulb");
    	
    	Container combosR = new Container();
    	combosR.setLayout(new GridLayout(0, 2));
    	
    	JComboBox<String> bulbTypes2 = new JComboBox<String>(Project.BULB_TYPES);
    	bulbTypes2.setSelectedIndex(2);
    	bulbTypes2.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)theEvent.getSource();
                String type = (String)cb.getSelectedItem();
            }
        });
    	
    	
    	JComboBox<Integer> bulbWattages2 = new JComboBox<Integer>();
    	for (int i : Project.BULB_WATTAGES) {
    		bulbWattages2.addItem(i);
    	}
    	bulbWattages2.setSelectedIndex(2);
    	bulbWattages2.addActionListener(new ActionListener() {

            /**
             * Action listener for color chooser, updates draw color based on user selection.
             *
             * @param theEvent draw color chooser event
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)theEvent.getSource();
                String wattage = (String)cb.getSelectedItem();
            }
        });
    	
    	// Line 3
    	JLabel lblNumBulbs = new JLabel("Number of Light Bulbs");
    	JTextField fieldNumBulbs = new JTextField();
    	
    	// Line 4
    	JLabel lblHrsPerDay = new JLabel("Hours Used per Day");
    	JTextField fieldHrsPerDay = new JTextField();
    	
    	// Line 5
    	JLabel lblDaysWk = new JLabel("Number of Days per Week");
    	JTextField fieldDaysWk = new JTextField();
    	
    	// Line 6
    	JLabel lblCostKWH = new JLabel("Electricity Cost per KWH");
    	JTextField fieldCostKWH = new JTextField();
    	
    	// Line 6
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
    	
    	
    	add(left);
    }

    /**
     * Returns the stroke size.
     *
     * @return the stroke size
     */
    protected int getStrokeSize() {
        return myStrokeSize;
    }

    /**
     * Returns max stroke size.
     *
     * @return max stroke size
     */
    protected int getMaxStrokeSize() {
        return MAX_STROKE_SIZE;
    }

    /**
     * Returns the draw color.
     *
     * @return the draw color
     */
    public Color getMyDrawColor() {
        return myDrawColor;
    }

    /**
     * Returns the fill color.
     *
     * @return the fill color
     */
    protected Color getMyFillColor() {
        return myFillColor;
    }

    /**
     * Sets draw color.
     *
     * @param theDrawColor the color to set this draw color to
     */
    public void setDrawColor(final Color theDrawColor) {
        myDrawColor = theDrawColor;
    }

    /**
     * Sets fill color.
     *
     * @param theFillColor the color to set this fill color to
     */
    public void setFillColor(final Color theFillColor) {
        myFillColor = theFillColor;
    }

    /**
     * Sets stroke size.
     *
     * @param theStrokeSize stroke size value to set to
     */
    public void setStrokeSize(final int theStrokeSize) {
        myStrokeSize = theStrokeSize;
    }

    /**
     * Draws previously drawn shapes first, followed by shapes in the process of drawing.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        int panelW = (int) (myFrame.getWidth() / 2.0);
        int panelH = (int) (myFrame.getHeight() / 2.0);
        
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
        
        //double theta = -10.97;
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

    /**
     * Returns string representation of LayoutPanel, a listing of previously drawn shapes.
     *
     * @return string representation of LayoutPanel, a listing of previously drawn shapes
     */
    @Override
    public String toString() {
        return "hmm";
    }

}
