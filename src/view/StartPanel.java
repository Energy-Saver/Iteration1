/**
 * TCSS 360 - Iteration1: PowerPaint
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
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartPanel extends JPanel {
    /** Default size for this JPanel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
   
    private JFrame myFrame;
    private Group myGroup;
    private BufferedImage myImage;

    /**
     * Constructs PaintPanel with default values and sets up the panel.
     */
    public StartPanel(JFrame theFrame, Group theGroup) {
        super();

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
    private void addComponentsTopane() {
    	setLayout(new BorderLayout());
    	
    	JButton b1 = new JButton("Login");
    	Color customBlue = new Color(86, 132, 197);
    	b1.setBackground(customBlue);
        b1.setForeground(Color.WHITE);
        this.add(b1, BorderLayout.SOUTH);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
    	super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawImage(myImage, 25, 25, this);
    }
}
