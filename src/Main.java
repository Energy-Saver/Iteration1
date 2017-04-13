import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.sun.glass.events.KeyEvent;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main (String[] args) {
		JFrame frame = new JFrame("EnergySaver");
		frame.setSize(1920,	1080);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		JLabel name = new JLabel("Name:");
		name.setBounds(10,10,80,25);
		panel.add(name);


		JTextField nameEnter = new JTextField(20);
		nameEnter.setBounds(100,10,160,25);
		panel.add(nameEnter);

		//email label
		JLabel email = new JLabel("Email:");
		email.setBounds(10,40,80,25);
		panel.add(email);

		//email text field
		JTextField emailEnter = new JTextField(20);
		emailEnter.setBounds(100,40,160,25);
		panel.add(emailEnter);

		//signup button
		JButton signup = new JButton("Signup");
		signup.setBounds(10,80,80,25);
		panel.add(signup);

		//Menu
		JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("MENU"); // created file menu object
		fileMenu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(fileMenu);
		//  menuBar.setBackground(new Color(251, 215, 100));
		JMenuItem menuAbout = new JMenuItem("About");
		//	menuAbout.setBackground(new Color(251, 215, 100));
		//		menuAbout.setForeground(Color.black);
		menuAbout.setMnemonic(KeyEvent.VK_A);
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null, "TCSS 360 Software "
						+ "Development and Quality Assurance Technniques "
						+ "\n\nSPRING 2017\n"
						+ "\nDEVELOPED AND DESIGNED BY: \nAlex Smith - "
						+ "developer\nDarren Carpenter - front-end developer"
						+ "\nNikolai Carlson - developer\nKeegan Kell - developer"
						+ "\nLola Howell - UX design / front-end developer",
						"About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(menuAbout); //added item(sub menu) to file menu object
		fileMenu.addSeparator();
		JMenuItem menuHELP = new JMenuItem("Help");
		//menuHELP.setBackground(new Color(251, 215, 100));
		//	menuHELP.setForeground(Color.black);
		menuHELP.setMnemonic(KeyEvent.VK_H);
		menuHELP.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				String helpMessage = "Enter your name and email address to sign up.\n";
				JOptionPane.showMessageDialog(null, helpMessage, "Help",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(menuHELP); //added item(sub menu) to file menu object
		fileMenu.addSeparator();
		final JMenuItem quitMenu = new JMenuItem("Exit", KeyEvent.VK_X);
		//   quitMenu.setBackground(new Color(251, 215, 100));
		quitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				frame.dispose();
			}
		});
		fileMenu.add(quitMenu);
		UIManager UI=new UIManager();
		//        UI.put("OptionPane.background", Color.white);
		//       UI.put("Panel.background", Color.white);
		frame.setJMenuBar(menuBar);
		//frame.pack();
		frame.setVisible(true);		
	}
}
