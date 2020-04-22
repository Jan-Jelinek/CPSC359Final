package cpsc329unessay;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EncryptDecrypt extends JPanel {
	private JTextField input;
	private JTextField output;

	/**
	 * Create the panel.
	 */
	public EncryptDecrypt(JFrame frame, int sharedkey) {
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
		Font boldTextFont = new Font("Gabriola", Font.BOLD, 38);		
		
		// Header
		JLabel header = new JLabel("Step 5: Key Usage");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 900, 50);
		add(header);

		// description
		JLabel description = new JLabel("Now you and your partner have generated a shared key: "+sharedkey);
		description.setFont(textFont);
		description.setForeground(Color.WHITE);
		description.setBounds(screenWidthMiddle-400, screenHeightMiddle-250, 900, 90);
		add(description);
		// description
		JLabel description2 = new JLabel("So what do you do with this key?");
		description2.setFont(boldTextFont);
		description2.setForeground(Color.WHITE);
		description2.setBounds(screenWidthMiddle-400, screenHeightMiddle-175, 900, 90);
		add(description2);
		// description
		JLabel description3 = new JLabel("You could use the key as a one-time-pad, but that would only work ");
		description3.setFont(textFont);
		description3.setForeground(Color.WHITE);
		description3.setBounds(screenWidthMiddle-400, screenHeightMiddle-125, 900, 90);
		add(description3);
		// description
		JLabel description4 = new JLabel("for one message (or well, you shouldn't use it more than one).");
		description4.setFont(textFont);
		description4.setForeground(Color.WHITE);
		description4.setBounds(screenWidthMiddle-400, screenHeightMiddle-75, 900, 90);
		add(description4);
		// description
		JLabel description5 = new JLabel("Instead you should use it as the key for a symmetric cryptosystem.");
		description5.setFont(textFont);
		description5.setForeground(Color.WHITE);
		description5.setBounds(screenWidthMiddle-400, screenHeightMiddle, 900, 90);
		add(description5);
		// description
		JLabel description6 = new JLabel("The advantage of that is that you can set up a secure channal which");
		description6.setFont(textFont);
		description6.setForeground(Color.WHITE);
		description6.setBounds(screenWidthMiddle-400, screenHeightMiddle+50, 900, 90);
		add(description6);
		// description
		JLabel description7 = new JLabel("can be used over dozens of times without losing security.");
		description7.setFont(textFont);
		description7.setForeground(Color.WHITE);
		description7.setBounds(screenWidthMiddle-400, screenHeightMiddle+100, 900, 90);
		add(description7);

		// Next Step
		JButton btnNextStep = new JButton("See Diagram");
		btnNextStep.setFont(textFont);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Diagram panel = new Diagram(frame); // Opens login window
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnNextStep.setBounds(screenWidthMiddle-100, screenHeightMiddle+250, 200, 40);
		add(btnNextStep);
		
		/*
		 * I mean, it works but it results in weird symbols that might not be sendable over a public channel without it messing up so scrapping the idea
		 * 
		JTextArea input = new JTextArea(16, 58);
		input.setLineWrap(true);
		input.setFont(textFont);
		input.setBounds(screenWidthMiddle-300, screenHeightMiddle-225, 600, 200);
		add(input);
				
		JTextArea output = new JTextArea(16, 58);
		output.setEditable(false);
		output.setLineWrap(true);
		output.setFont(textFont);
		output.setBounds(screenWidthMiddle-300, screenHeightMiddle+75, 600, 200);
		add(output);
		
		JButton encrypt = new JButton("Encrypt / Decrypt");
		encrypt.setFont(textFont);
		encrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String inputStr = input.getText();
			    byte[] inputBytes = inputStr.getBytes();
			    byte[] key = Integer.toString(sharedkey).getBytes();
			    byte[] outputBytes = new byte[inputBytes.length];
			    
			    int i = 0;	// Counter for outputBytes
			    int j = 0;	// Counter for key
			    for (byte b : inputBytes) {
			    	
			    	outputBytes[i] = (byte) (b ^ key[j]);	// XOR the message with the key
			    	i += 1;
			    	j += 1;
			    	
			    	// Cycle through the key.
			    	if (j >= key.length) {
			    		j = 0;
			    	}
			    }

			    String outputStr = new String(outputBytes);
			    output.setText(outputStr);
			}
		});
		encrypt.setBounds(screenWidthMiddle-200, screenHeightMiddle, 400, 40);
		add(encrypt);		
		*/
		
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
