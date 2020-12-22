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
import javax.swing.text.MaskFormatter;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;

public class MainWindow implements IAppWindow {
	
	private static final int tabImageSize = 24;

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
	private JMenuItem mntmExit;
	
	private User user;
	private JMenu mnPreferences;
	private JCheckBoxMenuItem chmntmDarkMode;
	private JMenuItem mntmChangeName;
	private JMenuItem mntmChangePassword;

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
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		mnPreferences = new JMenu("Preferences");
		menuBar.add(mnPreferences);
		
		chmntmDarkMode = new JCheckBoxMenuItem("Dark mode");
		mnPreferences.add(chmntmDarkMode);
		
		mntmChangeName = new JMenuItem("Change name");
		mnPreferences.add(mntmChangeName);
		
		mntmChangePassword = new JMenuItem("Change password");
		mnPreferences.add(mntmChangePassword);
		
		JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MnAboutMouseListener());
		menuBar.add(mnAbout);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.addMouseListener(new MnHelpMouseListener());
		menuBar.add(mnHelp);
		
		toolBar = new JToolBar();
		pnlBars.add(toolBar, BorderLayout.CENTER);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new TabbedPaneChangeListener());
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		pnlAccount = new PanelAccount();
		tabbedPane.addTab("Account",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/home.png")), tabImageSize, tabImageSize),
				pnlAccount, null);
		
		pnlAccomodation = new PanelAccomodation();
		tabbedPane.addTab("Reservations & Accomodation",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/calendar.png")), tabImageSize, tabImageSize),
				pnlAccomodation, null);
		
		pnlActivities = new PanelManager(ManagerTableModel.activitiesTableModel());
		tabbedPane.addTab("Activities",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/clock.png")), tabImageSize, tabImageSize),
				pnlActivities, null);
		MaskFormatter formatTel;
		try {
			formatTel = new MaskFormatter("'(###')' ###' ###' ###");
			formatTel.setPlaceholderCharacter('*');
			//txtTelefono = new JFormattedTextField(formatTel);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pnlEmployees = new PanelManager(ManagerTableModel.employeesTableModel());
		tabbedPane.addTab("Employees",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/search-client.png")), tabImageSize, tabImageSize),
				pnlEmployees, null);
		((PanelManager) pnlEmployees).getTable().getColumnModel().getColumn(1).setCellEditor(new PhotoCellEditor());
		
		pnlRoutes = new PanelManager(ManagerTableModel.routesTableModel());
		tabbedPane.addTab("Routes",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/anchor-nodes.png")), tabImageSize, tabImageSize),
				pnlRoutes, null);
		
		pnlMap = new PanelMap();
		tabbedPane.addTab("Map",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/waypoint-map.png")), tabImageSize, tabImageSize),
				pnlMap, null);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		((PanelAccount) pnlAccount).updateUser(this.user);
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
