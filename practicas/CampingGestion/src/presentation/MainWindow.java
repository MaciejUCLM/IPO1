package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

public class MainWindow {

	private JFrame frmMain;
	private JPanel panel;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("Camping Manager");
		frmMain.setBounds(100, 100, 700, 500);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frmMain.getContentPane().add(panel, BorderLayout.CENTER);
	}

	public void showComponent(Component c) {
		panel.removeAll();
		panel.add(c);
	}
}
