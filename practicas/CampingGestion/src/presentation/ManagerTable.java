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
			main = IController.getController().getWindow(EnumWindows.MAIN);
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
						getMain().log(Messages.getString("ManagerTable.0") + contents); //$NON-NLS-1$
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
			getRowSorter().allRowsChanged();
			getMain().log(Messages.getString("ManagerTable.1")); //$NON-NLS-1$
			((MainWindow) getMain()).updateCells();
		}
	}

	public class DeleteRowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int n = getSelectedRow();
			if (n < 0) {
				getMain().log(Messages.getString("ManagerTable.2")); //$NON-NLS-1$
				JOptionPane.showMessageDialog(getMain().getFrame(), Messages.getString("ManagerTable.3"), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				return;
			}

			int v = JOptionPane.showConfirmDialog(getMain().getFrame(), Messages.getString("ManagerTable.5")+mdlTable.getValueAt(n, 0).toString()+"?", Messages.getString("ManagerTable.7"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (v == JOptionPane.YES_OPTION) {
				clearSelection();
				if (n != -1)
					mdlTable.removeRow(n);
				mdlTable.fireTableDataChanged();
				getRowSorter().allRowsChanged();
				getMain().log(Messages.getString("ManagerTable.8") + n); //$NON-NLS-1$
				((MainWindow) getMain()).updateCells();
			}
		}
	}

}
