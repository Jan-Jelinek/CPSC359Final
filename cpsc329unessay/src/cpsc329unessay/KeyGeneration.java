package cpsc329unessay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;


public class KeyGeneration extends JPanel {
	private JTextField txt_prime;
	private JTextField txt_g;
	private JTextField txt_ab;
	private JFrame frame;	
	private int[] primes = {12, 12};
	private JTextField textField_1;
	private int p;
	private int g;
	private int privatekey;
	/**
	 * Create the panel.
	 */
	public KeyGeneration(JFrame frame, int role) {
		setLayout(null);

		// Save the user's screen resolution to variables, used to format GUI correctly
		GraphicsDevice screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenHeight = screenSize.getDisplayMode().getHeight();
		int screenWidth = screenSize.getDisplayMode().getWidth();
		
		// Useful reference points
		int screenWidthMiddle = (screenWidth/2+screenWidth/4)/2;
		int screenHeightMiddle = (screenHeight/2+screenHeight/4)/2;
		
		// Fonts used
		Font headerFont = new Font("Gabriola", Font.BOLD, 56);
		Font textFont = new Font("Gabriola", Font.PLAIN, 38);
		
		// Header
		JLabel header = new JLabel("Step 2: Generate Keys");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 470, 50);
		add(header);


		// Prime:
		JLabel lblPrime = new JLabel("A large prime number, p:");
		lblPrime.setFont(textFont);
		lblPrime.setForeground(Color.WHITE);
		lblPrime.setBounds(screenWidthMiddle-400, screenHeightMiddle-150, 500, 40);
		add(lblPrime);
		
		// Prime number text field
		txt_prime = new JTextField();
		txt_prime.setBounds(screenWidthMiddle-80, screenHeightMiddle-150, 300, 40);
		txt_prime.setFont(textFont);
		add(txt_prime);

		// Prime:
		JLabel lbl_prime2 = new JLabel("For real applications you'd want at least 2048 bits");
		lbl_prime2.setFont(textFont);
		lbl_prime2.setForeground(Color.LIGHT_GRAY);
		lbl_prime2.setBounds(screenWidthMiddle-400, screenHeightMiddle-100, 820, 40);
		add(lbl_prime2);

		
		// g:
		JLabel lbl_g = new JLabel("An integer g where 1 < g < p:");
		lbl_g.setFont(textFont);
		lbl_g.setForeground(Color.WHITE);
		lbl_g.setBounds(screenWidthMiddle-400, screenHeightMiddle-25, 500, 40);
		add(lbl_g);
		
		// g number text field
		txt_g = new JTextField();
		txt_g.setBounds(screenWidthMiddle-50, screenHeightMiddle-25, 275, 40);
		txt_g.setFont(textFont);
		add(txt_g);
		
		// Prime:
		JLabel lblGDescription = new JLabel("Ideally this is a primitive root of p");
		lblGDescription.setFont(textFont);
		lblGDescription.setForeground(Color.LIGHT_GRAY);
		lblGDescription.setBounds(screenWidthMiddle-400, screenHeightMiddle+25, 820, 40);
		add(lblGDescription);
		
		String AorB = ""; // a is Alices private key, b is Bobs private key

		if (role == 0) { // If Alice
			AorB = "a";
			
			// description
			JLabel lblDescription = new JLabel("Generate p and g, then send them to Bob.");
			lblDescription.setFont(textFont);
			lblDescription.setForeground(Color.WHITE);
			lblDescription.setBounds(screenWidthMiddle-400, screenHeightMiddle-250, 500, 90);
			add(lblDescription);
			
			// Next Step
			JButton btnGenPrime = new JButton("Generate p");
			btnGenPrime.setFont(textFont);
			btnGenPrime.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					// Between which 2 numbers to look for a prime for
					int min = 2049;
					int max = 4096;
					
					Random r = new Random();
					p = r.nextInt(max-min)+min; // Generates a number between 4096 and 8192
					System.out.println(p);
					
					boolean loop = true;
					
					while(loop) {

						if (p%2 == 0) { // If the random number is even
							p += 1;
						}
						
						// Checks through even number half of the random one to see if its prime
						boolean prime = true;
						for (int i = 2; i <= p/2; ++i) {
							if(p%i == 0) {
								prime = false;
								break;
							}
						}
						
						// If its not prime, add 3
						if (prime == false) {
							p += 2;
							
							// Loops aronud if to high of a number
							if (p >= max) {
								p = min;
							}
						}
						else
							loop = false; // Found a prime
					}
					
					txt_prime.setText(Integer.toString(p));
				}
			});
			btnGenPrime.setBounds(screenWidthMiddle+250, screenHeightMiddle-150, 200, 40);
			add(btnGenPrime);
			
			// Generate g
			JButton btnGenG = new JButton("Generate g");
			btnGenG.setFont(textFont);
			btnGenG.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					// 1 < g < p
					if (p > 0) { // If there is a prime number generated (can't make a g if there isn't)
						Random r = new Random();
						g = r.nextInt(p-1);
						txt_g.setText(Integer.toString(g));
					}
				}
			});
			btnGenG.setBounds(screenWidthMiddle+250, screenHeightMiddle-25, 200, 40);
			add(btnGenG);
		}
		
		else if (role == 1) { // Bob Selected
			AorB = "b";
			
			// description
			JLabel description = new JLabel("Have Alice send you p and g.");
			description.setFont(textFont);
			description.setForeground(Color.WHITE);
			description.setBounds(screenWidthMiddle-400, screenHeightMiddle-250, 600, 90);
			add(description);
			
		}
		
		
		// g:
		JLabel lbl_ab = new JLabel("A private Key "+AorB+", where 1 < "+AorB+" < p-1:");
		lbl_ab.setFont(textFont);
		lbl_ab.setForeground(Color.WHITE);
		lbl_ab.setBounds(screenWidthMiddle-400, screenHeightMiddle+100, 700, 40);
		add(lbl_ab);
		
		// g number text field
		txt_ab = new JTextField();
		txt_ab.setBounds(screenWidthMiddle+5, screenHeightMiddle+100, 220, 40);
		txt_ab.setFont(textFont);
		add(txt_ab);
		
		// private key description:
		JLabel lblPKDescription = new JLabel("Keep this private");
		lblPKDescription.setFont(textFont);
		lblPKDescription.setForeground(Color.LIGHT_GRAY);
		lblPKDescription.setBounds(screenWidthMiddle-400, screenHeightMiddle+150, 820, 40);
		add(lblPKDescription);
		
		// Generates a private key
		JButton btnPrivateKey = new JButton("Generate "+AorB);
		btnPrivateKey.setFont(textFont);
		btnPrivateKey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					// Remembers the private key from the text box (needed if Bob selected, or if ALice changed from the one she generated)
					p = Integer.parseInt(txt_prime.getText());
										
					// 1 < p < p-1
					if (p > 0) {
						Random r = new Random();
						privatekey = r.nextInt(p-2);
						txt_ab.setText(Integer.toString(privatekey));
					}
					
					g = Integer.parseInt(txt_g.getText());
				}
				catch (Exception e) {
					// No information entered
				}
			}
		});
		btnPrivateKey.setBounds(screenWidthMiddle+250, screenHeightMiddle+100, 200, 40);
		add(btnPrivateKey);
		
		// Next Step
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.setFont(textFont);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				p = Integer.parseInt(txt_prime.getText());
				g = Integer.parseInt(txt_g.getText());
				privatekey = Integer.parseInt(txt_ab.getText());
				
				// If there is a private key input
				if (p > 0) {
					// If there is a g and that g is less than p
					if (g > 0 && g < p) {
						// If there is a private key and that private key is less than p-1
						if (privatekey > 0 && privatekey < p-1) {
							KeyExchangeP1 panel = new KeyExchangeP1(frame, role, p, g, privatekey); // Opens login window
							frame.setContentPane(panel);
							frame.revalidate();
						}
					}
				}
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
