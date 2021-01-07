package presentation;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.awt.BorderLayout;
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
		frmHelpOfCamping.setBounds(100, 100, 440, 480);
		frmHelpOfCamping.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHelpOfCamping.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane helpWelcome = new JScrollPane();
		tabbedPane.addTab("Welcome",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/help.png")), tabImageSize, tabImageSize),
				helpWelcome, null);
		
		JTextArea taWelcome = new JTextArea();
		taWelcome.setWrapStyleWord(true);
		taWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taWelcome.setText("Camping Manager is organized in tabs with different functionalities.\r\nTool bar is associated with each tab and allows context appropriate operations.\r\nMenu bar allows the user to logout or exit but also change current user preferences (name or password).\r\n\r\nEach tab in this help window describes the functionality of a specific tab.");
		taWelcome.setEditable(false);
		taWelcome.setLineWrap(true);
		helpWelcome.setViewportView(taWelcome);
		
		JScrollPane helpAccount = new JScrollPane();
		tabbedPane.addTab("Account",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/home.png")), tabImageSize, tabImageSize),
				helpAccount, null);
		
		JTextArea taAccount = new JTextArea();
		taAccount.setWrapStyleWord(true);
		taAccount.setText("The Account tab is a summary of current user logged in.\r\n\r\n\t\"Change avatar\" button allows to load another user icon from image file.\r\n\r\n\t\"Last access time\" is showing the date of last successful login attempt.\r\n\r\nTo change user name or user password use \"Preferences\" menu at the top.");
		taAccount.setLineWrap(true);
		taAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taAccount.setEditable(false);
		helpAccount.setViewportView(taAccount);
		
		JScrollPane helpReservations = new JScrollPane();
		tabbedPane.addTab("Reservations & Accomodation",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/calendar.png")), tabImageSize, tabImageSize),
				helpReservations, null);
		
		JTextArea taReservations = new JTextArea();
		taReservations.setWrapStyleWord(true);
		taReservations.setText("The Reservations & Accomodation tab provides tools to manage camping structure and reservations.\r\n\r\nThe left hand side tree panel allows to navigate over the camping. New camping objects and categories can be added or removed using \"New object\" and \"Delete object\". When creating a new object a name text field will pop up. Object names can be edited with double-click.\r\nEvery object has its details at the bottom side of the panel. Once an object is selected its details are loaded. On the right side of the panel there is a gallery where you can add, remove or preview images of the selected object using right-mouse click.\r\n\r\nThe center table allows to add, remove and edit current reservations falling into the category of the selected object. New reservation is always added into the selected object in the tree panel.\r\n\r\nAll the funcionality is a available in the popup menus under right-mouse button.");
		taReservations.setLineWrap(true);
		taReservations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taReservations.setEditable(false);
		helpReservations.setViewportView(taReservations);
		
		JScrollPane helpActivites = new JScrollPane();
		tabbedPane.addTab("Activities",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/clock.png")), tabImageSize, tabImageSize),
				helpActivites, null);
		
		JTextArea taActivities = new JTextArea();
		taActivities.setWrapStyleWord(true);
		taActivities.setText("The Activities tab provides a table to manage current activities on the camping.\r\n\r\nTo sort by a specific column click on the column name.\r\n\r\nRight-mouse click opens a shortcut menu.");
		taActivities.setLineWrap(true);
		taActivities.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taActivities.setEditable(false);
		helpActivites.setViewportView(taActivities);
		
		JScrollPane helpEmployees = new JScrollPane();
		tabbedPane.addTab("Employees",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/search-client.png")), tabImageSize, tabImageSize),
				helpEmployees, null);
		
		JTextArea taEmployees = new JTextArea();
		taEmployees.setWrapStyleWord(true);
		taEmployees.setText("The Employees tab provides a table to manage currently hired personel.\r\n\r\nTo sort by a specific column click on the column name.\r\n\r\nRight-mouse click opens a shortcut menu.");
		taEmployees.setLineWrap(true);
		taEmployees.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taEmployees.setEditable(false);
		helpEmployees.setViewportView(taEmployees);
		
		JScrollPane helpRoutes = new JScrollPane();
		tabbedPane.addTab("Routes",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/anchor-nodes.png")), tabImageSize, tabImageSize),
				helpRoutes, null);
		
		JTextArea taRoutes = new JTextArea();
		taRoutes.setWrapStyleWord(true);
		taRoutes.setText("The Routes tab provides a table to manage available routes in the camping.\r\n\r\n*Map* column is integrated with Map tab which allows editing information on the map.\r\n*Monitors* column is integrated with Employees tab and provides selection between current employees.\r\n\r\nTo sort by a specific column click on the column name.\r\n\r\nRight-mouse click opens a shortcut menu.");
		taRoutes.setLineWrap(true);
		taRoutes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taRoutes.setEditable(false);
		helpRoutes.setViewportView(taRoutes);
		
		JScrollPane helpMap = new JScrollPane();
		tabbedPane.addTab("Map",
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/waypoint-map.png")), tabImageSize, tabImageSize),
				helpMap, null);
		
		JTextArea taMap = new JTextArea();
		taMap.setWrapStyleWord(true);
		taMap.setText("The Map tab provides tools to add information on the map images of existing routes.\r\nTo load a route first select it on the Routes panel.\r\nAfter loading the route add your markings and apply the changes using \"Save route\" tool.\r\n\r\nDrawing can be done with the rightmost tools from the toolbar.\r\nTo cancel using a tool use right-mouse click.\r\nTo cancel typing text click Enter keeping the text field empty.");
		taMap.setLineWrap(true);
		taMap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taMap.setEditable(false);
		helpMap.setViewportView(taMap);
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
	public void onLocaleChange() {
		// TODO Auto-generated method stub
	}

	@Override
	public JFrame getFrame() {
		return frmHelpOfCamping;
	}

}
