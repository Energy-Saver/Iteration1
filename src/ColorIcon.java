/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
/**
 * Class ColorIcon defines a simple colored square icon that may be set to any color.
 * It's height and width is not changeable and set to 15x15.
 *
 * @author Alex Smith
 * @author Darren Carpenter
 * @author Nikolai Carlson
 * @author Keegan Kell
 * @author Lola Howell
 * @version 16 April 2016 - Iteration 1
 */
public class ColorIcon implements Icon {
    /** Icon Height. */
    private static final int ICON_HEIGHT = 15;

    /** Icon Width. */
    private static final int ICON_WIDTH = 15;

    /** Color of the icon. */
    private Color myColor;

    /**
     * Constructs an icon object.
     *
     * @param theColor color
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Sets myColor to theColor.
     *
     * @param theColor b
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Returns icon height.
     * @return icon height
     */
    @Override
    public int getIconHeight() {
        return ICON_HEIGHT;
    }

    /**
     * Returns icon width.
     *
     * @return icon width
     */
    @Override
    public int getIconWidth() {
        return ICON_WIDTH;
    }

    /**
     * Draw the icon at the specified location. This implementation does nothing with the
     * component passed.
     *
     * @param theC the component - unused
     * @param theG the graphics objects to draw on
     * @param theX the X location to draw the icon
     * @param theY the Y location to draw the icon
     */
    @Override
    public void paintIcon(final Component theC, final Graphics theG, final int theX,
                          final int theY) {
        theG.setColor(myColor);
        theG.fillRect(theX, theY, ICON_HEIGHT, ICON_WIDTH);

        theG.setColor(Color.GRAY);
        theG.drawRect(theX, theY, ICON_HEIGHT, ICON_WIDTH);
    }

}
