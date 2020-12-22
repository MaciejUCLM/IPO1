package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelManager extends MainPanel {
	
	private ManagerTableModel mdlTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelManager(ManagerTableModel mdl) {
		tools = new JButton[2];
		tools[0] = new JButton("Add");
		tools[0].addActionListener(new AddRowActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize));

		tools[1] = new JButton("Delete");
		tools[1].addActionListener(new DeleteRowActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize));
		
		this.mdlTable = mdl;

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(40);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		table.setModel(this.mdlTable);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int n = table.getSelectedRow();
					if (n != -1)
					{
						String contenido = mdlTable.getValueAt(n, 0).toString();
						getMain().log("Selected: " + contenido);
					}
				}
			}
		});
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}
	
	public JTable getTable() {
		return table;
	}

	private class AddRowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mdlTable.addRow(mdlTable.getRowTemplate().clone());
			mdlTable.fireTableDataChanged();
			getMain().log("Added new element");
		}
	}

	private class DeleteRowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int n = table.getSelectedRow();
			int v = JOptionPane.showConfirmDialog(getMain().getFrame(), "Are you sure you want to remove "+mdlTable.getValueAt(n, 0).toString()+"?", "Delete entry",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (v == JOptionPane.YES_OPTION) {
				if (n != -1)
					mdlTable.removeRow(table.getSelectedRow());
				mdlTable.fireTableDataChanged();
				getMain().log("Deleted element " + n);
			}
		}
	}

}
