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

	private JTextArea taWelcome;
	private JTextArea taAccount;
	private JTextArea taReservations;
	private JTextArea taActivities;
	private JTextArea taEmployees;
	private JTextArea taRoutes;
	private JTextArea taMap;

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
		frmHelpOfCamping.setIconImage(Toolkit.getDefaultToolkit().getImage(HelpWindow.class.getResource("/presentation/resources/help.png"))); //$NON-NLS-1$
		frmHelpOfCamping.setTitle(Messages.getString("HelpWindow.frmHelpOfCamping.title")); //$NON-NLS-1$
		frmHelpOfCamping.setBounds(100, 100, 440, 480);
		frmHelpOfCamping.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHelpOfCamping.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane helpWelcome = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.1"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/help.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpWelcome, null);
		
		taWelcome = new JTextArea();
		taWelcome.setWrapStyleWord(true);
		taWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taWelcome.setText(Messages.getString("HelpWindow.taWelcome.text")); //$NON-NLS-1$
		taWelcome.setEditable(false);
		taWelcome.setLineWrap(true);
		helpWelcome.setViewportView(taWelcome);
		
		JScrollPane helpAccount = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.0"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/home.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpAccount, null);
		
		taAccount = new JTextArea();
		taAccount.setWrapStyleWord(true);
		taAccount.setText(Messages.getString("HelpWindow.taAccount.text")); //$NON-NLS-1$
		taAccount.setLineWrap(true);
		taAccount.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taAccount.setEditable(false);
		helpAccount.setViewportView(taAccount);
		
		JScrollPane helpReservations = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.7"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/calendar.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpReservations, null);
		
		taReservations = new JTextArea();
		taReservations.setWrapStyleWord(true);
		taReservations.setText(Messages.getString("HelpWindow.taReservations.text")); //$NON-NLS-1$
		taReservations.setLineWrap(true);
		taReservations.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taReservations.setEditable(false);
		helpReservations.setViewportView(taReservations);
		
		JScrollPane helpActivites = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.2"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/clock.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpActivites, null);
		
		taActivities = new JTextArea();
		taActivities.setWrapStyleWord(true);
		taActivities.setText(Messages.getString("HelpWindow.taActivities.text")); //$NON-NLS-1$
		taActivities.setLineWrap(true);
		taActivities.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taActivities.setEditable(false);
		helpActivites.setViewportView(taActivities);
		
		JScrollPane helpEmployees = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.13"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/search-client.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpEmployees, null);
		
		taEmployees = new JTextArea();
		taEmployees.setWrapStyleWord(true);
		taEmployees.setText(Messages.getString("HelpWindow.taEmployees.text")); //$NON-NLS-1$
		taEmployees.setLineWrap(true);
		taEmployees.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taEmployees.setEditable(false);
		helpEmployees.setViewportView(taEmployees);
		
		JScrollPane helpRoutes = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.16"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/anchor-nodes.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpRoutes, null);
		
		taRoutes = new JTextArea();
		taRoutes.setWrapStyleWord(true);
		taRoutes.setText(Messages.getString("HelpWindow.taRoutes.text")); //$NON-NLS-1$
		taRoutes.setLineWrap(true);
		taRoutes.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		taRoutes.setEditable(false);
		helpRoutes.setViewportView(taRoutes);
		
		JScrollPane helpMap = new JScrollPane();
		tabbedPane.addTab(Messages.getString("HelpWindow.19"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/waypoint-map.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				helpMap, null);
		
		taMap = new JTextArea();
		taMap.setWrapStyleWord(true);
		taMap.setText(Messages.getString("HelpWindow.taMap.text")); //$NON-NLS-1$
		taMap.setLineWrap(true);
		taMap.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
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
		frmHelpOfCamping.setTitle(Messages.getString("HelpWindow.frmHelpOfCamping.title")); //$NON-NLS-1$
		taWelcome.setText(Messages.getString("HelpWindow.taWelcome.text")); //$NON-NLS-1$
		taAccount.setText(Messages.getString("HelpWindow.taAccount.text")); //$NON-NLS-1$
		taReservations.setText(Messages.getString("HelpWindow.taReservations.text")); //$NON-NLS-1$
		taActivities.setText(Messages.getString("HelpWindow.taActivities.text")); //$NON-NLS-1$
		taEmployees.setText(Messages.getString("HelpWindow.taEmployees.text")); //$NON-NLS-1$
		taRoutes.setText(Messages.getString("HelpWindow.taRoutes.text")); //$NON-NLS-1$
		taMap.setText(Messages.getString("HelpWindow.taMap.text")); //$NON-NLS-1$
	}

	@Override
	public JFrame getFrame() {
		return frmHelpOfCamping;
	}

}
