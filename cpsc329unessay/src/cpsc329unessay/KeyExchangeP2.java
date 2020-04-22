package cpsc329unessay;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KeyExchangeP2 extends JPanel {
	public int recieved;
	/**
	 * Create the panel.
	 */
	public KeyExchangeP2(JFrame frame, int role, int p, int g, int privatekey, int out) {
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
		JLabel header = new JLabel("Step 4: Key Exchange Part 2");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 800, 50);
		add(header);
		
		String AorB = ""; // a for Alices private key, b for Bobs private key
		String notAorB = "";
		if (role == 0) { // If Alice selected
			AorB = "a";
			notAorB = "b";

			// Description:
			JLabel lbl_send = new JLabel("Send y^a to Bob");
			lbl_send.setFont(textFont);
			lbl_send.setForeground(Color.WHITE);
			lbl_send.setBounds(screenWidthMiddle-400, screenHeightMiddle-175, 300, 40);
			add(lbl_send);

			// Description 2:
			JLabel lbl_g = new JLabel("Recieved y^b from Bob");
			lbl_g.setFont(textFont);
			lbl_g.setForeground(Color.WHITE);
			lbl_g.setBounds(screenWidthMiddle-400, screenHeightMiddle-25, 500, 40);
			add(lbl_g);

		} else { // Bob selected
			AorB = "b";
			notAorB = "a";
			// send:
			JLabel lbl_send = new JLabel("Send y^b to Alice");
			lbl_send.setFont(textFont);
			lbl_send.setForeground(Color.WHITE);
			lbl_send.setBounds(screenWidthMiddle-400, screenHeightMiddle-175, 300, 40);
			add(lbl_send);

			// got:
			JLabel lbl_g = new JLabel("Recieved y^a from Alice");
			lbl_g.setFont(textFont);
			lbl_g.setForeground(Color.WHITE);
			lbl_g.setBounds(screenWidthMiddle-400, screenHeightMiddle-25, 500, 40);
			add(lbl_g);
			

		}
		
		// y^x:
		JLabel lbl_a = new JLabel("y^"+AorB+": ");
		lbl_a.setFont(textFont);
		lbl_a.setForeground(Color.WHITE);
		lbl_a.setBounds(screenWidthMiddle-400, screenHeightMiddle-125, 200, 40);
		add(lbl_a);
		// Prime number text field
		JTextField txt_y = new JTextField();
		txt_y.setEditable(false);
		txt_y.setBounds(screenWidthMiddle-330, screenHeightMiddle-125, 300, 40);
		txt_y.setFont(textFont);
		txt_y.setText(Integer.toString(out));
		add(txt_y);
		
		// label:
		JLabel lbl_b = new JLabel("y^"+notAorB+": ");
		lbl_b.setFont(textFont);
		lbl_b.setForeground(Color.WHITE);
		lbl_b.setBounds(screenWidthMiddle-400, screenHeightMiddle+25, 200, 40);
		add(lbl_b);
		// g number text field
		JTextField txt_recieved = new JTextField();
		txt_recieved.setBounds(screenWidthMiddle-330, screenHeightMiddle+25, 300, 40);
		txt_recieved.setFont(textFont);
		add(txt_recieved);
		
		// Next Step
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.setFont(textFont);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				recieved = Integer.parseInt(txt_recieved.getText());
				
				if (recieved > 0) {
					CalculateKey panel = new CalculateKey(frame, role, p, g, privatekey, recieved); // Opens login window
					frame.setContentPane(panel);
					frame.revalidate();
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
