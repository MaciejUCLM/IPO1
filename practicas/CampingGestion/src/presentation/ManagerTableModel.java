package presentation;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ManagerTableModel extends AbstractTableModel {
	
	public static ManagerTableModel employeesTableModel() {
		return new ManagerTableModel(new String[] {"Name", "Photo", "Phone", "e-mail", "Languages", "Temporary"});
	}

	protected String[] columnNames;
	protected Vector<Object[]> data;
	
	public ManagerTableModel(String[] columns) {
		super();
		this.data = new Vector<>();
		this.columnNames = columns;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object[] rowData = (Object [])data.elementAt(row);
		return rowData[col];
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (row < getRowCount() && col < getColumnCount()) {
			Object[] rowData = (Object [])data.elementAt(row);
			rowData[col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	public void removeRow(int row) {
		data.remove(row);
	}

	public void addRow(Object[] row) {
		data.add(row);
	}

}
