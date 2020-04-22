package cpsc329unessay;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
 * An Interactive Diffie-Hellman Program
 * @author Jan Jelinek, 30075855
 */
public class MainFrame {

	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainFrame() {
		
		// Gets screen size
		GraphicsDevice screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int screenHeight = screenSize.getDisplayMode().getHeight();
		int screenWidth = screenSize.getDisplayMode().getWidth();
				
		frame = new JFrame();
		frame.setBounds((screenWidth/8), (screenHeight/8), (screenWidth/2+screenWidth/4), (screenHeight/2+screenHeight/4));		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Roles panel = new Roles(frame);
		frame.setContentPane(panel);
		frame.revalidate();	}

}
