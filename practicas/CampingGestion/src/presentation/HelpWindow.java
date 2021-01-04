package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

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
		frmHelpOfCamping.setBounds(100, 100, 660, 480);
		frmHelpOfCamping.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHelpOfCamping.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel helpWelcome = new JPanel();
		tabbedPane.addTab("Welcome",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/help.png")), tabImageSize, tabImageSize),
				helpWelcome, null);
		
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
