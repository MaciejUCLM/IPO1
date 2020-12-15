package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Locale;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow implements IAppWindow {

	private JFrame frmMain;
	private MainPanel pnlAccount;
	private MainPanel pnlActivities;
	private MainPanel pnlAccomodation;
	private MainPanel pnlEmployees;
	private MainPanel pnlRoutes;
	private MainPanel pnlMap;
	private JTabbedPane tabbedPane;
	private JToolBar toolBar;
	private JLabel lblStatus;
	private JPanel pnlBars;

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
		frmMain.setBounds(100, 100, 800, 500);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblStatus = new JLabel("...");
		lblStatus.setBackground(new Color(255, 255, 255));
		lblStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmMain.getContentPane().add(lblStatus, BorderLayout.SOUTH);
		
		pnlBars = new JPanel();
		frmMain.getContentPane().add(pnlBars, BorderLayout.NORTH);
		pnlBars.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		pnlBars.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new LogoutActionListener());
		mnFile.add(mntmLogout);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.addMouseListener(new MnHelpMouseListener());
		menuBar.add(mnHelp);
		
		JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MnAboutMouseListener());
		menuBar.add(mnAbout);
		
		toolBar = new JToolBar();
		pnlBars.add(toolBar, BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new TabbedPaneChangeListener());
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		pnlAccount = new PanelAccount();
		tabbedPane.addTab("Account", null, pnlAccount, null);
		
		pnlAccomodation = new PanelAccomodation();
		tabbedPane.addTab("Reservations & Accomodation", null, pnlAccomodation, null);
		
		pnlActivities = new PanelManager();
		tabbedPane.addTab("Activities", null, pnlActivities, null);
		
		pnlEmployees = new PanelManager();
		tabbedPane.addTab("Employees", null, pnlEmployees, null);
		
		pnlRoutes = new PanelManager();
		tabbedPane.addTab("Routes", null, pnlRoutes, null);
		
		pnlMap = new PanelMap();
		tabbedPane.addTab("Map", null, pnlMap, null);
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO implement
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.MAIN;
	}

	@Override
	public JFrame getFrame() {
		return frmMain;
	}

	@Override
	public void log(String msg) {
		lblStatus.setText(msg);
	}

	private class TabbedPaneChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			toolBar.removeAll();
			MainPanel tab = (MainPanel) tabbedPane.getSelectedComponent();
			for (JButton b : tab.getToolBarButtons()) {
				toolBar.add(b);
			}
			pnlBars.repaint();
		}
	}

	private class MnHelpMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			IAppWindow.getController().openWindow(EnumWindows.HELP);
			log("Opened help window");
		}
	}
	private class MnAboutMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			IAppWindow.getController().openWindow(EnumWindows.ABOUT);
			log("Opened about window");
		}
	}

}
