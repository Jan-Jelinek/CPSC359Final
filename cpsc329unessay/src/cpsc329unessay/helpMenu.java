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
import javax.swing.SwingConstants;

public class helpMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public helpMenu(JFrame frame, int role, int p, int g, int privatekey) {
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
		
		// Header
		JLabel header = new JLabel("Modular Exponentiation Tutorial");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 800, 50);
		add(header);

		// Text to write
		String[] labels = {"The modular exponentiation algorithm works as follows:",
				"    1. Initialize some starting value x to 1.",
				"    2. Start with the leftmost bit, do:",
				"        -  If the bit is 1, multiply x by the base.",
				"        -  Move to the right by 1 bit and square x.",
				"",
				"Example: Calculate 40^12 (mod 71)",
				"(Note: 12 = 0b1100)",
				"1      (1*40)^2 = 38 (mod 71)",
				"1   (38*40)^2 = 60 (mod 71)",
				"0               60^2 = 50 (mod 71)",
				"0 "};
		
		int offset = 0;
		for (int i = 0; i < labels.length; i++) {

			// Text1:
			JLabel lbl_t1 = new JLabel(labels[i]);
			lbl_t1.setFont(textFont);
			lbl_t1.setForeground(Color.WHITE);
			lbl_t1.setBounds(screenWidthMiddle-400+offset, screenHeightMiddle-225+(38*i), 700, 40);
			add(lbl_t1);
			
			if (i > 6) {
				lbl_t1.setFont(mathFont);
				offset = 100;
			}

		}
		
		// Next Step
		JButton btn_back = new JButton("Back");
		btn_back.setFont(textFont);
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				KeyExchangeP1 panel = new KeyExchangeP1(frame, role, p, g, privatekey); // Opens login window
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btn_back.setBounds(screenWidthMiddle-100, screenHeightMiddle+250, 200, 40);
		add(btn_back);
		
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
