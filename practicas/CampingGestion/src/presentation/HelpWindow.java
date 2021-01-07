package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class HelpWindow implements IAppWindow {

	private static final int tabImageSize = 16;

	private JFrame frmHelpOfCamping;

	/**
	 * Create the application.
	 */
	public HelpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHelpOfCamping = new JFrame();
		frmHelpOfCamping.setIconImage(Toolkit.getDefaultToolkit().getImage(HelpWindow.class.getResource("/presentation/resources/help.png")));
		frmHelpOfCamping.setTitle("Help Camping Manager");
		frmHelpOfCamping.setBounds(100, 100, 380, 320);
		frmHelpOfCamping.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHelpOfCamping.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel helpWelcome = new JPanel();
		tabbedPane.addTab("Welcome",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/help.png")), tabImageSize, tabImageSize),
				helpWelcome, null);
		helpWelcome.setLayout(new BorderLayout(0, 0));
		
		JTextArea taWelcome = new JTextArea();
		taWelcome.setWrapStyleWord(true);
		taWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taWelcome.setText("Camping Manager is organized in tabs with different functionalities.\r\nTool bar is associated with each tab and allows context appropriate operations.\r\nMenu bar allows the user to logout or exit but also change current user preferences (name or password).\r\n\r\nEach tab in this help window describes the functionality of a specific tab.");
		taWelcome.setEditable(false);
		taWelcome.setLineWrap(true);
		helpWelcome.add(taWelcome);
		
		JPanel helpAccount = new JPanel();
		tabbedPane.addTab("Account",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/home.png")), tabImageSize, tabImageSize),
				helpAccount, null);
		
		JPanel helpReservations = new JPanel();
		tabbedPane.addTab("Reservations & Accomodation",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/calendar.png")), tabImageSize, tabImageSize),
				helpReservations, null);
		
		JPanel helpActivites = new JPanel();
		tabbedPane.addTab("Activities",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/clock.png")), tabImageSize, tabImageSize),
				helpActivites, null);
		
		JPanel helpEmployees = new JPanel();
		tabbedPane.addTab("Employees",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/search-client.png")), tabImageSize, tabImageSize),
				helpEmployees, null);
		
		JPanel helpRoutes = new JPanel();
		tabbedPane.addTab("Routes",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/anchor-nodes.png")), tabImageSize, tabImageSize),
				helpRoutes, null);
		
		JPanel helpMap = new JPanel();
		tabbedPane.addTab("Map",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/waypoint-map.png")), tabImageSize, tabImageSize),
				helpMap, null);
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.HELP;
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

	@Override
	public JFrame getFrame() {
		return frmHelpOfCamping;
	}

}
