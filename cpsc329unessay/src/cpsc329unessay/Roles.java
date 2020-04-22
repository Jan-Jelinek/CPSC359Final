package cpsc329unessay;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.Color;
import java.awt.Canvas;

public class Roles extends JPanel {
	private JFrame frame;
	public int role = 2;

	/**
	 * Create the panel.
	 */
	public Roles(JFrame frame) {
		setLayout(null);
		
		// Save the user's screen resolution to variables, used to format GUI correctly
		GraphicsDevice screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenHeight = screenSize.getDisplayMode().getHeight();
		int screenWidth = screenSize.getDisplayMode().getWidth();
		
		// Useful reference points
		int screenWidthMiddle = (screenWidth/2+screenWidth/4)/2;
		int screenHeightMiddle = (screenHeight/2+screenHeight/4)/2;
		
		// Fonts used
		Font titleFont = new Font("Gabriola", Font.BOLD, 72);
		Font headerFont = new Font("Gabriola", Font.BOLD, 56);
		Font textFont = new Font("Gabriola", Font.PLAIN, 38);
		Font boldTextFont = new Font("Gabriola", Font.BOLD, 38);
		
		// Title
		JLabel title = new JLabel("Interactive Diffie-Hellman Program");
		title.setFont(titleFont);
		title.setForeground(Color.WHITE);
		title.setBounds(screenWidthMiddle-480, screenHeightMiddle-300, 960, 68);
		add(title);
		
		// Header
		JLabel header = new JLabel("Step 1: Choose a role");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-200, 470, 50);
		add(header);
		
		// Description
		JLabel description = new JLabel("One person should be Alice, the other should be Bob.");
		description.setFont(textFont);
		description.setForeground(Color.WHITE);
		description.setBounds(screenWidthMiddle-400, screenHeightMiddle-150, 800, 40);
		add(description);
		
		// Alice Chosen
		JLabel lblAliceChosen = new JLabel("Alice - ");
		lblAliceChosen.setFont(boldTextFont);
		lblAliceChosen.setForeground(Color.WHITE);
		lblAliceChosen.setBounds(screenWidthMiddle-400, screenHeightMiddle+50, 470, 50);
		add(lblAliceChosen);
		lblAliceChosen.setVisible(false);
		// Alice Chosen Description
		JLabel lblAliceChosenDescription = new JLabel("As Alice you will come up with a prime number p and a base g.");
		lblAliceChosenDescription.setFont(textFont);
		lblAliceChosenDescription.setForeground(Color.WHITE);
		lblAliceChosenDescription.setBounds(screenWidthMiddle-300, screenHeightMiddle+50, 800, 50);
		add(lblAliceChosenDescription);
		lblAliceChosenDescription.setVisible(false);

		// Bob Chosen
		JLabel lblBobChosen = new JLabel("Bob - ");
		lblBobChosen.setFont(boldTextFont);
		lblBobChosen.setForeground(Color.WHITE);
		lblBobChosen.setBounds(screenWidthMiddle-400, screenHeightMiddle+50, 470, 50);
		add(lblBobChosen);
		lblBobChosen.setVisible(false);
		// Bob Chosen Description
		JLabel lblBobChosenDescription = new JLabel("As Bob you will recive a prime number p and a base g.");
		lblBobChosenDescription.setFont(textFont);
		lblBobChosenDescription.setForeground(Color.WHITE);
		lblBobChosenDescription.setBounds(screenWidthMiddle-320, screenHeightMiddle+50, 800, 50);
		add(lblBobChosenDescription);
		lblBobChosenDescription.setVisible(false);

		// Bob Chosen Description
		JLabel lblError = new JLabel("Choose a role first");
		lblError.setFont(textFont);
		lblError.setForeground(Color.RED);
		lblError.setBounds(screenWidthMiddle+125, screenHeightMiddle+250, 300, 50);
		add(lblError);
		lblError.setVisible(false);

		
		// Choose to be Alice
		JButton btnChooseAlice = new JButton("I wanna be Alice");
		btnChooseAlice.setFont(textFont);
		btnChooseAlice.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				lblError.setVisible(false);
				lblAliceChosen.setVisible(true);
				lblAliceChosenDescription.setVisible(true);
				lblBobChosen.setVisible(false);
				lblBobChosenDescription.setVisible(false);
				role = 0;
			}
		});
		btnChooseAlice.setBounds(screenWidthMiddle-345, screenHeightMiddle-75, 300, 50);
		add(btnChooseAlice);		

		// Choose to be Bob
		JButton btn_bob = new JButton("I wanna be Bob");
		btn_bob.setFont(textFont);
		btn_bob.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				lblError.setVisible(false);
				lblAliceChosen.setVisible(false);
				lblAliceChosenDescription.setVisible(false);
				lblBobChosen.setVisible(true);
				lblBobChosenDescription.setVisible(true);
				role = 1;
			}
		});
		btn_bob.setBounds(screenWidthMiddle+45, screenHeightMiddle-75, 300, 50);
		add(btn_bob);

		
		// Next Step
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.setFont(textFont);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if (role == 0 || role == 1) {
					KeyGeneration panel = new KeyGeneration(frame, role); // Opens login window
					frame.setContentPane(panel);
					frame.revalidate();
				}
				else
					lblError.setVisible(true);
			}
		});
		btnNextStep.setBounds(screenWidthMiddle-100, screenHeightMiddle+250, 200, 40);
		add(btnNextStep);

		// Loads in the background image
		URL url = Roles.class.getResource("/background2.png");
		ImageIcon img = new ImageIcon(url);
		JLabel background_1 = new JLabel("",img,SwingConstants.LEFT);
		background_1.setVerticalAlignment(SwingConstants.TOP);
		background_1.setBounds(0, 0, screenWidth, screenHeight);
		background_1.setVisible(true);
		add(background_1);
		
	}
}
