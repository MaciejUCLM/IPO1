package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManagerTable extends JTable {
	
	private static IAppWindow main;
	private static IAppWindow getMain() {
		if (main == null)
			main = IAppWindow.getController().getWindow(EnumWindows.MAIN);
		return main;
	}
	
	private ManagerTableModel mdlTable;
	private int selectedRow = -1;
	
	public ManagerTable(ManagerTableModel mdl) {
		this.setAutoCreateRowSorter(true);
		this.setRowHeight(60);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.mdlTable = mdl;
		this.setModel(this.mdlTable);
		this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					selectedRow = getSelectedRow();
					if (selectedRow != -1) {
						String contents = mdlTable.getValueAt(selectedRow, 0).toString();
						getMain().log("Selected: " + contents);
					}
				}
			}
		});
	}
	
	public Object[] getRow(int row) {
		Object[] data = mdlTable.getRowTemplate().clone();
		for (int i = 0; i < data.length; i++)
			data[i] = mdlTable.getValueAt(row, i);
		return data;
	}
	
	public int getLength() {
		return mdlTable.getRowCount();
	}
	
	public Object[] getSelectedData() {
		if (selectedRow < 0)
			return null;
		return getRow(selectedRow);
	}

	public class AddRowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mdlTable.addRow(mdlTable.getRowTemplate().clone());
			mdlTable.fireTableDataChanged();
			getMain().log("Added new element");
			((MainWindow) getMain()).updateCells();
		}
	}

	public class DeleteRowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int n = getSelectedRow();
			if (n < 0) {
				getMain().log("ERROR: no row selected");
				JOptionPane.showMessageDialog(getMain().getFrame(), "No entry selected! Make sure to mark row you want to remove.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int v = JOptionPane.showConfirmDialog(getMain().getFrame(), "Are you sure you want to remove "+mdlTable.getValueAt(n, 0).toString()+"?", "Delete entry",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (v == JOptionPane.YES_OPTION) {
				if (n != -1)
					mdlTable.removeRow(getSelectedRow());
				mdlTable.fireTableDataChanged();
				getMain().log("Deleted element " + n);
				((MainWindow) getMain()).updateCells();
			}
		}
	}

}
