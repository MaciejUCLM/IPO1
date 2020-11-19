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
import javax.swing.border.LineBorder;
import java.awt.Color;

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
		
		JButton btnLogout = new JButton("Logout");
		toolBar.add(btnLogout);
		
		JLabel lblStatus = new JLabel("...");
		lblStatus.setBackground(new Color(255, 255, 255));
		lblStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmMain.getContentPane().add(lblStatus, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlWelcome = new JPanel();
		tabbedPane.addTab("New tab", null, pnlWelcome, null);
		
		JPanel pnlReservations = new JPanel();
		tabbedPane.addTab("New tab", null, pnlReservations, null);
		
		JPanel pnlCamping = new JPanel();
		tabbedPane.addTab("New tab", null, pnlCamping, null);
		
		JPanel pnlActivities = new JPanel();
		tabbedPane.addTab("New tab", null, pnlActivities, null);
		
		JPanel pnlEmployees = new JPanel();
		tabbedPane.addTab("New tab", null, pnlEmployees, null);
		
		JPanel pnlRoutes = new JPanel();
		tabbedPane.addTab("New tab", null, pnlRoutes, null);
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
