package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Locale;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class MainWindow implements IAppWindow {

	private JFrame frmMain;

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
		
		JToolBar toolBar = new JToolBar();
		frmMain.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		frmMain.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

	@Override
	public void onLocaleChange(Locale rb) {
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.MAIN;
	}

	@Override
	public void setVisible() {
		frmMain.setVisible(true);
	}

	@Override
	public void setVisible(boolean state) {
		frmMain.setVisible(state);
	}
}
