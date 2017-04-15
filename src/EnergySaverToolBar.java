/**
 * TCSS 360 - Iteration 1: Product v0.1
 */
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

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
public class EnergySaverToolBar extends JToolBar {
    /**
     * Fake serialVersionUID to squelch warning.
     */
    private static final long serialVersionUID = 1000491896841625204L;

    /** A button group for the mutually exclusive tool bar buttons. */
    private final ButtonGroup myGroup;

    /**
     * Constructs the ToolBar.
     */
    public EnergySaverToolBar() {
        super();
        myGroup = new ButtonGroup();
    }

    /**
     * Creates a JToggleButton for the ToolBar and links the passed {@code theAction}.
     *
     * @param theAction action to link with the created JToggleButton
     */
    public void createToggleButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        myGroup.add(toggleButton);
        add(toggleButton);
    }

    /**
     * Returns string representation of the toolBar.
     *
     * @return string representation of the toolBar
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(85);
        sb.append("[button group: ");
        sb.append(myGroup);
        sb.append(']');
        return sb.toString();
    }

}
