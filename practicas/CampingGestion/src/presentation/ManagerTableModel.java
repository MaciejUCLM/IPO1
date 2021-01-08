package presentation;

import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ManagerTableModel extends AbstractTableModel {
	
	public static ManagerTableModel employeesTableModel() {
		return new ManagerTableModel(
				new String[] {Messages.getString("ManagerTableModel.0"), Messages.getString("ManagerTableModel.1"), Messages.getString("ManagerTableModel.2"), Messages.getString("ManagerTableModel.3"), Messages.getString("ManagerTableModel.4"), Messages.getString("ManagerTableModel.5")}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				new Object[] {"?", new ImageIcon(MainWindow.class.getClassLoader().getResource("presentation/resources/name-tag.png")), "", "@", "", false} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				);
	}

	public static ManagerTableModel routesTableModel() {
		return new ManagerTableModel(
				new String[] {Messages.getString("ManagerTableModel.11"), Messages.getString("ManagerTableModel.12"), Messages.getString("ManagerTableModel.13"), Messages.getString("ManagerTableModel.14"), Messages.getString("ManagerTableModel.15"), Messages.getString("ManagerTableModel.16"), Messages.getString("ManagerTableModel.17"), Messages.getString("ManagerTableModel.18")}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				new Object[] {Messages.getString("ManagerTableModel.unnamed"), new Date(), new Date(), 10, EnumDifficulty.MEDIUM, Messages.getString("ManagerTableModel.none"), "", new ImageIcon(MainWindow.class.getClassLoader().getResource("presentation/resources/map-cr.jpg"))} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				);
	}

	public static ManagerTableModel activitiesTableModel() {
		return new ManagerTableModel(
				new String[] {Messages.getString("ManagerTableModel.23"), Messages.getString("ManagerTableModel.24"), Messages.getString("ManagerTableModel.25"), Messages.getString("ManagerTableModel.26"), Messages.getString("ManagerTableModel.27"), Messages.getString("ManagerTableModel.28"), Messages.getString("ManagerTableModel.29")}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				new Object[] {Messages.getString("ManagerTableModel.unnamed"), new Date(), 10, "", false, Messages.getString("ManagerTableModel.none"), 0.0f} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				);
	}

	protected Object[] rowTemplate;
	protected String[] columnNames;
	protected Vector<Object[]> data;
	
	public ManagerTableModel(String[] columns, Object[] template) {
		super();
		this.data = new Vector<>();
		this.columnNames = columns;
		this.rowTemplate = template;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	public Object[] getRow(int row) {
		return (Object [])data.elementAt(row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		return getRow(row)[col];
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c) {
		return getRowTemplate()[c].getClass();
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
	
	public Object[] getRowTemplate() {
		return rowTemplate;
	}

}
