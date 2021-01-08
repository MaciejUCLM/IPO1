package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JToolBar;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class MainWindow implements IAppWindow {
	
	private static final int tabImageSize = 24;

	private User user;

	private JFrame frmMain;
	private MainPanel pnlAccount;
	private MainPanel pnlAccomodation;
	private PanelManager pnlActivities;
	private PanelManager pnlEmployees;
	private PanelManager pnlRoutes;
	private MainPanel pnlMap;
	private JTabbedPane tabbedPane;
	private JToolBar toolBar;
	private JLabel lblStatus;
	private JPanel pnlBars;
	private JMenuItem mntmExit;
	private JMenu mnPreferences;
	private JMenuItem mntmChangeName;
	private JMenuItem mntmChangePassword;
	private JMenu mnFile;
	private JMenuItem mntmLogout;
	private JMenu mnAbout;
	private JMenu mnHelp;

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
		frmMain.setTitle(Messages.getString("MainWindow.frmMain.title")); //$NON-NLS-1$
		frmMain.setBounds(100, 100, 990, 590);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblStatus = new JLabel("..."); //$NON-NLS-1$
		lblStatus.setBackground(new Color(255, 255, 255));
		lblStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmMain.getContentPane().add(lblStatus, BorderLayout.SOUTH);
		
		pnlBars = new JPanel();
		frmMain.getContentPane().add(pnlBars, BorderLayout.NORTH);
		pnlBars.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		pnlBars.add(menuBar, BorderLayout.NORTH);
		
		mnFile = new JMenu(Messages.getString("MainWindow.mnFile.text")); //$NON-NLS-1$
		menuBar.add(mnFile);
		
		mntmLogout = new JMenuItem(Messages.getString("MainWindow.mntmLogout.text")); //$NON-NLS-1$
		mntmLogout.addActionListener(new LogoutActionListener());
		mnFile.add(mntmLogout);
		
		mntmExit = new JMenuItem(Messages.getString("MainWindow.mntmExit.text")); //$NON-NLS-1$
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		mnPreferences = new JMenu(Messages.getString("MainWindow.mnPreferences.text")); //$NON-NLS-1$
		menuBar.add(mnPreferences);
		
		mntmChangeName = new JMenuItem(Messages.getString("MainWindow.mntmChangeName.text")); //$NON-NLS-1$
		mnPreferences.add(mntmChangeName);
		
		mntmChangePassword = new JMenuItem(Messages.getString("MainWindow.mntmChangePassword.text")); //$NON-NLS-1$
		mnPreferences.add(mntmChangePassword);
		
		mnAbout = new JMenu(Messages.getString("MainWindow.mnAbout.text")); //$NON-NLS-1$
		mnAbout.addMouseListener(new MnAboutMouseListener());
		menuBar.add(mnAbout);
		
		mnHelp = new JMenu(Messages.getString("MainWindow.mnHelp.text")); //$NON-NLS-1$
		mnHelp.addMouseListener(new MnHelpMouseListener());
		menuBar.add(mnHelp);
		
		toolBar = new JToolBar();
		pnlBars.add(toolBar, BorderLayout.CENTER);

		// Spinners for cell editors
		JSpinner _spDate = new JSpinner();
		_spDate.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		JSpinner _spCapacity = new JSpinner();
		_spCapacity.setModel(new SpinnerNumberModel(10, 0, null, 1));
		JSpinner _spPrice = new JSpinner();
		_spPrice.setModel(new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new TabbedPaneChangeListener());
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		pnlAccount = new PanelAccount();
		tabbedPane.addTab(Messages.getString("MainWindow.0"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/home.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlAccount, null);
		
		pnlAccomodation = new PanelAccomodation();
		tabbedPane.addTab(Messages.getString("MainWindow.3"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/calendar.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlAccomodation, null);
		
		pnlActivities = new PanelManager(ManagerTableModel.activitiesTableModel());
		tabbedPane.addTab(Messages.getString("MainWindow.5"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/clock.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlActivities, null);
		pnlActivities.getTable().getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor(_spDate));
		pnlActivities.getTable().getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
		pnlActivities.getTable().getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(_spCapacity));
		pnlActivities.getTable().getColumnModel().getColumn(6).setCellEditor(new SpinnerEditor(_spPrice));
		
		pnlEmployees = new PanelManager(ManagerTableModel.employeesTableModel());
		tabbedPane.addTab(Messages.getString("MainWindow.7"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/search-client.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlEmployees, null);

		pnlEmployees.getTable().getColumnModel().getColumn(1).setCellEditor(new PhotoCellEditor());
		try {
			MaskFormatter formatTel;
			formatTel = new MaskFormatter("'(###')' ###' ###' ###"); //$NON-NLS-1$
			formatTel.setPlaceholderCharacter('*');
			pnlEmployees.getTable().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JFormattedTextField(formatTel)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pnlRoutes = new PanelManager(ManagerTableModel.routesTableModel());
		tabbedPane.addTab(Messages.getString("MainWindow.10"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/anchor-nodes.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlRoutes, null);

		JComboBox<EnumDifficulty> cmbDifficulty = new JComboBox<>();
		cmbDifficulty.setModel(new DefaultComboBoxModel<>(EnumDifficulty.values()));
		pnlRoutes.getTable().getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor(_spDate));
		pnlRoutes.getTable().getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
		pnlRoutes.getTable().getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(_spDate));
		pnlRoutes.getTable().getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
		pnlRoutes.getTable().getColumnModel().getColumn(3).setCellEditor(new SpinnerEditor(_spCapacity));
		pnlRoutes.getTable().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cmbDifficulty));
		pnlRoutes.getTable().getColumnModel().getColumn(7).setCellEditor(new PhotoCellEditor());
		
		pnlRoutes.getTable().getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				ManagerTable tab = pnlRoutes.getTable();
				int row = tab.getSelectedRow();
				if (tab.getLength() > 0 && row >= 0) {
					Date start = (Date) tab.getValueAt(row, 1);
					Date finish = (Date) tab.getValueAt(row, 2);
					if (start.after(finish))
						tab.setValueAt(start, row, 2);
				}
			}
		});
		
		pnlMap = new PanelMap();
		tabbedPane.addTab(Messages.getString("MainWindow.12"), //$NON-NLS-1$
				IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/waypoint-map.png")), tabImageSize, tabImageSize), //$NON-NLS-1$
				pnlMap, null);
	}

	@Override
	public void onLocaleChange() {
		MainPanel[] panels = new MainPanel[] {pnlAccount, pnlAccomodation, pnlActivities, pnlEmployees, pnlRoutes, pnlMap};
		for (MainPanel p : panels)
			p.onLocaleChange();
		frmMain.setTitle(Messages.getString("MainWindow.frmMain.title")); //$NON-NLS-1$
		mnFile.setText(Messages.getString("MainWindow.mnFile.text")); //$NON-NLS-1$
		mntmLogout.setText(Messages.getString("MainWindow.mntmLogout.text")); //$NON-NLS-1$
		mntmExit.setText(Messages.getString("MainWindow.mntmExit.text")); //$NON-NLS-1$
		mnPreferences.setText(Messages.getString("MainWindow.mnPreferences.text")); //$NON-NLS-1$
		mntmChangeName.setText(Messages.getString("MainWindow.mntmChangeName.text")); //$NON-NLS-1$
		mntmChangePassword.setText(Messages.getString("MainWindow.mntmChangePassword.text")); //$NON-NLS-1$
		mnAbout.setText(Messages.getString("MainWindow.mnAbout.text")); //$NON-NLS-1$
		mnHelp.setText(Messages.getString("MainWindow.mnHelp.text")); //$NON-NLS-1$
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

		Object[] example = ((ManagerTableModel) pnlEmployees.getTable().getModel()).getRowTemplate().clone();
		example[0] = user.getName();
		((ManagerTableModel) pnlEmployees.getTable().getModel()).addRow(example);
		updateCells();
	}
	
	public Object[] getSelectedRoute() {
		return pnlRoutes.getTable().getSelectedData();
	}
	
	public void updateCells() {
		String[] employees = new String[pnlEmployees.getTable().getLength()];

		for (int i = 0; i < employees.length; i++)
			employees[i] = (String)(pnlEmployees.getTable().getValueAt(i, 0));

		JComboBox<String> cmbMonitors = new JComboBox<>();
		cmbMonitors.setModel(new DefaultComboBoxModel<>(employees));
		pnlRoutes.getTable().getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cmbMonitors));
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
			IController.getController().openWindow(EnumWindows.HELP);
			log(Messages.getString("MainWindow.14")); //$NON-NLS-1$
		}
	}
	private class MnAboutMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			IController.getController().openWindow(EnumWindows.ABOUT);
			log(Messages.getString("MainWindow.15")); //$NON-NLS-1$
		}
	}

}
