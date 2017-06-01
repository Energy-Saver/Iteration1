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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DrawChart extends JPanel {

private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
	private static final Color ES_BLUE = new Color(86, 132, 197);
    private static final int MARGIN = 50;
    private static final int TICK_LENGTH = 5;
    private static final double PERCENT_SPACE_BOTTOM = 0.35;
    
    private Graphics2D g2d;
    private JFrame myFrame;
    private Group myGroup;
	
    public DrawChart(Graphics2D theGraphics, JFrame theFrame, Group theGroup) {
    	g2d = theGraphics;
    	myFrame = theFrame;
    	myGroup = theGroup;
    }
    
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
