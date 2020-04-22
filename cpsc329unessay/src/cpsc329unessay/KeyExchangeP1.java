package cpsc329unessay;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KeyExchangeP1 extends JPanel {
	private JFrame frame;	
	public int count = 0;
	public 	int[] answers;
	public int out;

	/**
	 * Create the panel.
	 */
	public KeyExchangeP1(JFrame frame, int role, int p, int g, int privatekey) {
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
		Font mathFont = new Font("Cambria Math", Font.PLAIN, 20);
		
		// Array of numbers that need to be input
		answers  = new int[Integer.toBinaryString(privatekey).length()];
		String w = Integer.toBinaryString(privatekey); 
		
		int val = 1;
		for (int i = 0; i < Integer.toBinaryString(privatekey).length(); i++) {

			// If 1, multiply by the base
			if (w.substring(i, i+1).equals("1")) {
				val = val*g; // val = (val*g)
				val = val%p;
			}
			if (i == w.length()-1) {
				// Don't square at end
			} else {	
				// Then square
				val = val*val;
				val = val%p;
			}
			answers[i] = val;
		}
		for (int x : answers) {
			System.out.println("x: "+x);
		}
		
		// Header
		JLabel header = new JLabel("Step 3: Keys Exchange Part 1");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 600, 50);
		add(header);

		// Next Step
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.setFont(textFont);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// button is only visible if you complete the calculation
				if (btnNextStep.isVisible()) {
					KeyExchangeP2 panel = new KeyExchangeP2(frame, role, p, g, privatekey, out); // Opens login window
					frame.setContentPane(panel);
					frame.revalidate();
				}
			}
		});
		btnNextStep.setBounds(screenWidthMiddle-100, screenHeightMiddle+250, 200, 40);
		add(btnNextStep);
		btnNextStep.setVisible(false);

		// HELP button
		JButton btn_help = new JButton("Need help?");
		btn_help.setFont(textFont);
		btn_help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				helpMenu panel = new helpMenu(frame, role, p, g, privatekey); // Opens login window
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btn_help.setBounds(screenWidthMiddle+300, screenHeightMiddle, 200, 40);
		add(btn_help);
		
		String AorB = "";
		if (role == 0) { // If Alice, use a to denote the key
			AorB = "a";
		}
		else if (role == 1) { // If Bob, use b to denote the key
			AorB = "b";
		}

		// description
		JLabel description = new JLabel("Now we need to calculate y    g^"+AorB+" (mod p) using modular exponentiation");
		description.setFont(textFont);
		description.setForeground(Color.WHITE);
		description.setBounds(screenWidthMiddle-400, screenHeightMiddle-250, 900, 40);
		add(description);
		// The font doesn't have congruence symbol so had to use a different one for that
		JLabel congruence = new JLabel("\u2261");
		congruence.setFont(mathFont);
		congruence.setForeground(Color.WHITE);
		congruence.setBounds(screenWidthMiddle-80, screenHeightMiddle-250, 20, 40);
		add(congruence);

		
		// description
		JLabel description2 = new JLabel("Thus calculate y    "+g+"^"+privatekey+" (mod "+p+")");
		description2.setFont(textFont);
		description2.setForeground(Color.WHITE);
		description2.setBounds(screenWidthMiddle-400, screenHeightMiddle-200, 900, 40);
		add(description2);
		// The font doesn't have congruence symbol so had to use a different one for that
		JLabel congruence2 = new JLabel("\u2261");
		congruence2.setFont(mathFont);
		congruence2.setForeground(Color.WHITE);
		congruence2.setBounds(screenWidthMiddle-205, screenHeightMiddle-200, 20, 40);
		add(congruence2);
		
		// privatekey in binary
		JLabel binaryPK = new JLabel("Note: "+AorB+" = 0b"+Integer.toBinaryString(privatekey));
		binaryPK.setFont(textFont);
		binaryPK.setForeground(Color.LIGHT_GRAY);
		binaryPK.setBounds(screenWidthMiddle-400, screenHeightMiddle-165, 450, 40);
		add(binaryPK);

		// Draws the binary numbers, inputs, '=', outputs
		JLabel[] inputLabels = new JLabel[12];
		JLabel[] outputLabels = new JLabel[12];
		for (int i = 0; i < Integer.toBinaryString(privatekey).length(); i++) {

			JLabel binaryDisplayed = new JLabel( Integer.toBinaryString(privatekey).substring(i, i+1) );
			binaryDisplayed.setFont(mathFont);
			binaryDisplayed.setForeground(Color.WHITE);
			binaryDisplayed.setBounds(screenWidthMiddle-300, screenHeightMiddle-120+(30*i), 50, 40);
			add(binaryDisplayed);

			// Empty label that was text written to it after each calculation done correctly
			// Has to be made here since if made after background image loaded in it will be under it aka invisisble
			JLabel in = new JLabel("");
			in.setFont(mathFont);
			in.setForeground(Color.WHITE);
			in.setBounds(screenWidthMiddle-250, screenHeightMiddle-120+(i)*30, 200, 40);
			add(in);
			inputLabels[i] = in;

			JLabel out = new JLabel("");
			out.setFont(mathFont);
			out.setForeground(Color.WHITE);
			out.setBounds(screenWidthMiddle+70, screenHeightMiddle-120+(i)*30, 300, 40);
			add(out);
			outputLabels[i] = out;
			
			String q = Integer.toBinaryString(privatekey);

			// All this so it doesn't write the last '=' when the string ends in 0
			if (q.substring(q.length()-1, q.length()).equals("0") && i != (q.length()-1)) {
				JLabel equals = new JLabel( "=" );
				equals.setFont(mathFont);
				equals.setForeground(Color.WHITE);
				equals.setBounds(screenWidthMiddle, screenHeightMiddle-120+(30*i), 50, 40);
				add(equals);
				
			} else if(q.substring(q.length()-1, q.length()).equals("1")) {
				JLabel equals = new JLabel( "=" );
				equals.setFont(mathFont);
				equals.setForeground(Color.WHITE);
				equals.setBounds(screenWidthMiddle, screenHeightMiddle-120+(30*i), 50, 40);
				add(equals);				
			}
			

		}
				
		// g number text field
		JLabel lbl_output = new JLabel();
		lbl_output.setBounds(screenWidthMiddle+70, screenHeightMiddle-120, 300, 40);
		lbl_output.setFont(mathFont);
		lbl_output.setForeground(Color.WHITE);
		lbl_output.setText("     (mod "+p+")");
		add(lbl_output);
		
		// g number text field
		JTextField txt_input = new JTextField();
		txt_input.setBounds(screenWidthMiddle-250, screenHeightMiddle-115, 200, 30);
		txt_input.setFont(mathFont);
		txt_input.setText("(x*y)^2");
		txt_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				// Reads the calculation input aka (x*y)^2
				try {
					String input = txt_input.getText();
					int output = 0;
						
					boolean square = false; // If you square at the end
					if (input.endsWith("^2")) {
						square = true;
						input = input.substring(0, input.length()-2);
					}

					int brackets = 0;
					if (input.startsWith("(")) {
						input = input.substring(1, input.length());
						brackets += 1;
					}
					if (input.endsWith(")")){
						input = input.substring(0, input.length()-1);
						brackets += 1;
					}
						
					if (input.contains("*")) {

						// If there were brackets on both sides or no square symbol
						if (brackets == 2 || square == false) {
							int times = input.indexOf("*");
							output = Integer.parseInt(input.substring(0, times))*Integer.parseInt(input.substring(times+1, input.length()));
							output = output%p;							
						}
						else {
							// In the form x*y^2. Handle differently
							int times = input.indexOf("*");
							int x = Integer.parseInt(input.substring(0, times));
							int y = Integer.parseInt(input.substring(times+1, input.length()));
								
							output = y*y;
							output = output%p;							

							output = x*output;
							output = output%p;
								
							square = false;								
								
						}
						
					} else {

						output = Integer.parseInt(input);
					}
					
					if (square)
						output = output*output;
					
					output = output%p; // Output mod p
					
					lbl_output.setText(Integer.toString(output)+" (mod "+p+")");

					// If you input something equal to the correct answer
					if (output == answers[count]) {
						
						inputLabels[count].setText(txt_input.getText());
						outputLabels[count].setText(lbl_output.getText());

						count += 1;
						int compare = count;
						String w = Integer.toBinaryString(privatekey);
						if (w.substring(w.length()-1, w.length()).equals("0")) {
							compare += 1;
						}
						
						// If the private key ends in 0, end one line early
						if (compare < Integer.toBinaryString(privatekey).length()) {
						
							txt_input.setBounds(screenWidthMiddle-250, screenHeightMiddle-115+(count)*30, 200, 30);
							txt_input.setText("");

							lbl_output.setBounds(screenWidthMiddle+70, screenHeightMiddle-120+(count)*30, 300, 40);
							lbl_output.setText("     (mod "+p+")");
						}
						else {
							JLabel previousOutput2 = new JLabel( lbl_output.getText() );
							previousOutput2.setFont(textFont);
							previousOutput2.setForeground(Color.WHITE);
							previousOutput2.setBounds(screenWidthMiddle+70, screenHeightMiddle-120+(count-1)*30, 300, 40);
							add(previousOutput2);
							
							txt_input.setVisible(false);
							
							out = output;
							btnNextStep.setVisible(true);
						}
					}
					
						
				}
				catch (Exception exception) {
					
				}
				
			}
		});
		add(txt_input);

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
