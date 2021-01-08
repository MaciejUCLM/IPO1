package presentation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

public class PanelManager extends MainPanel {
	
	private ManagerTable table;
	
	public PanelManager() {
		this(new ManagerTableModel(new String[] {}, new Object[] {}));
	}

	/**
	 * Create the panel.
	 */
	public PanelManager(ManagerTableModel mdl) {
		table = new ManagerTable(mdl);

		tools = new JButton[2];
		tools[0] = new JButton(Messages.getString("PanelManager.add")); //$NON-NLS-1$
		tools[0].addActionListener(table.new AddRowActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[1] = new JButton(Messages.getString("PanelManager.delete")); //$NON-NLS-1$
		tools[1].addActionListener(table.new DeleteRowActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(table);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmAdd = new JMenuItem(Messages.getString("PanelManager.add")); //$NON-NLS-1$
		mntmAdd.addActionListener(table.new AddRowActionListener());
		popupMenu.add(mntmAdd);
		JMenuItem mntmRemove = new JMenuItem(Messages.getString("PanelManager.delete")); //$NON-NLS-1$
		mntmRemove.addActionListener(table.new DeleteRowActionListener());
		popupMenu.add(mntmRemove);
		IAppWindow.addPopup(table, popupMenu);
		IAppWindow.addPopup(scrollPane, popupMenu);
	}

	@Override
	public void onLocaleChange() {
		tools[0].setText(Messages.getString("PanelManager.add")); //$NON-NLS-1$
		tools[1].setText(Messages.getString("PanelManager.delete")); //$NON-NLS-1$
	}
	
	public ManagerTable getTable() {
		return table;
	}

}
