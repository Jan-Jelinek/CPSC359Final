package cpsc329unessay;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Diagram extends JPanel {
	private JLabel background_diagram;

	
	
	/**
	 * Create the panel.
	 */
	public Diagram(JFrame frame) {
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
		JLabel header = new JLabel("Here's a diagram showing the process:");
		header.setFont(headerFont);
		header.setForeground(Color.WHITE);
		header.setBounds(screenWidthMiddle-400, screenHeightMiddle-300, 800, 50);
		add(header);
		
		
		// Loads the image of the diagram. 886 x 450
		URL urlDiagram = Roles.class.getResource("/diagram.png");
		ImageIcon img_diagram = new ImageIcon(urlDiagram);
		Image image = img_diagram.getImage();
		background_diagram = new JLabel("",img_diagram,SwingConstants.LEFT);
		background_diagram.setVerticalAlignment(SwingConstants.TOP);
		background_diagram.setBounds(screenWidthMiddle-493, screenHeightMiddle-225, 986, 450);
		background_diagram.setVisible(true);
		background_diagram.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("Alice - image");
			}
		});
		add(background_diagram);

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
