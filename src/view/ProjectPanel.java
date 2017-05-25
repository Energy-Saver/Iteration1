/**
 * TCSS 360 - Iteration1: PowerPaint
 */
package view;

import model.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

        int panelW = (int) myFrame.getWidth();
        int panelH = (int) myFrame.getHeight();
        
        int graphW = panelW - 2 * MARGIN;
        int graphH = (int) (panelH - MARGIN - PERCENT_SPACE_BOTTOM * panelH);
        
        for (int i = 0; i < 4; i++) {
        	g2d.drawLine(MARGIN - TICK_LENGTH, (int) (MARGIN + i * 0.25 * graphH), 
        				 MARGIN, (int) (MARGIN + i * 0.25 * graphH));
        	g2d.drawLine((int) (MARGIN + (i + 1) * graphW * 0.25), MARGIN + graphH, 
        				(int) (MARGIN + (i + 1) * graphW * 0.25), MARGIN + graphH + TICK_LENGTH);
        }
        
        int halfHeight = (int)(MARGIN + graphH * 0.5);
        int halfWidth = (int)(MARGIN + graphW * 0.5);
        
        g2d.setColor(Color.BLUE);
        g2d.drawLine((int) (MARGIN + graphW * 0.5), MARGIN + graphH, 
        		     (int) (MARGIN + graphW * 0.5), halfHeight);
        
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < graphH * 0.1; i++) {
        	g2d.drawLine((int) (MARGIN + graphW * 0.5), halfHeight + i * 5, 
        			     (int) (MARGIN + graphW * 0.5), halfHeight + i * 5 + 2);
        }
        
        g2d.setColor(Color.GREEN);
        g2d.drawLine(MARGIN, MARGIN, MARGIN + graphW, MARGIN + graphH);
        g2d.setColor(Color.RED);
        g2d.drawLine(MARGIN, MARGIN + graphH, MARGIN + graphW, MARGIN);
        
        
        DecimalFormat dfDolr = new DecimalFormat("$0.00");
        DecimalFormat dfTime = new DecimalFormat("0.00");
        dfDolr.setMaximumFractionDigits(2);
        dfTime.setMaximumFractionDigits(2);
        
        g2d.setColor(Color.BLACK);
        double initialCost = myGroup.getCurrentUser().getProject().initialCost();
        String stringInitialCost = dfDolr.format(initialCost);
        g2d.drawString(stringInitialCost, 0, MARGIN + 5);
        
        double payoffYears = myGroup.getCurrentUser().getProject().payoffYears();
        String stringPayoffYears = dfTime.format(payoffYears);
        g2d.drawString(stringPayoffYears, halfWidth - 10, MARGIN + graphH + 20);
        
        g2d.drawRect(MARGIN, MARGIN, graphW, graphH);
        

        
        
        
        
        int tickIntrW = (int) (graphW * 0.25);
        int tickIntrH = (int) (graphH * 0.25);
        System.out.println("tiw: " + tickIntrW);
        System.out.println("tih: " + tickIntrH);
        
        double years = myGroup.getCurrentUser().getProject().payoffYears();
        
        //g2d.drawLine(graphH - (int) years, MARGIN, graphH - (int) years, MARGIN);
        System.out.println(years);
        System.out.println("Earning Per Year: " + myGroup.getCurrentUser().getProject().returnPerYear());
        System.out.println("Current: " + myGroup.getCurrentUser().getProject().currentAnnualCost());
        System.out.println("Replace: " + myGroup.getCurrentUser().getProject().replacementAnnualCost());
        System.out.println("Cost: "    + myGroup.getCurrentUser().getProject().getNumberOfBulbs() 
        							   * myGroup.getCurrentUser().getProject().getReplacementBulbCost());
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
