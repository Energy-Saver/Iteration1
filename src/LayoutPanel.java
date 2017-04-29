/**
 * TCSS 360 - Iteration1: PowerPaint
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
public class LayoutPanel extends JPanel {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 4433564918619415939L;

    /** Default draw color. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);

    /** Default fill color. */
    private static final Color UW_GOLD = new Color(232, 211, 162);

    /** Default stroke size. */
    private static final int DEFAULT_STROKE = 1;

    /** Maximum stroke size. */
    private static final int MAX_STROKE_SIZE = 20;

    /** Default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);

    /** The color to draw shapes with. */
    private Color myDrawColor;

    /** The color to fill shapes with. */
    private Color myFillColor;

    /** The state of the fill checkBox. */
    private boolean myFillFlag;

    /** The stroke size used for drawing. */
    private int myStrokeSize;

    /**
     * Constructs PaintPanel with default values and sets up the panel.
     */
    public LayoutPanel() {
        super();
        myDrawColor = UW_PURPLE;
        myFillColor = UW_GOLD;
        myFillFlag = true;
        myStrokeSize = DEFAULT_STROKE;

        setup();
    }

    /**
     * Helper method for setting up PaintPanel.
     */
    private void setup() {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
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
     * Sets flag based on passed value specifying the state of the fill checkBox.
     *
     * @param theFillFlag the state of the fill checkBox
     */
    public void setIsFilled(final boolean theFillFlag) {
        myFillFlag = theFillFlag;
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
