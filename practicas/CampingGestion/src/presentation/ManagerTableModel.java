package presentation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ManagerTableModel extends AbstractTableModel {
	
	public static ManagerTableModel employeesTableModel() {
		return new ManagerTableModel(
				new String[] {"Name", "Photo", "Phone", "e-mail", "Languages", "Temporary"},
				new Object[] {"name", new ImageIcon(MainWindow.class.getClassLoader().getResource("presentation/resources/name-tag.png")), "", "@", "", false}
				);
	}

	public static ManagerTableModel routesTableModel() {
		return new ManagerTableModel(
				new String[] {"Route", "Date", "Start Time", "Finish Time", "Capacity", "Difficulty", "Description", "Monitor", "Map"},
				new Object[] {"title", LocalDate.now(), LocalTime.of(1, 0), LocalTime.of(2, 0), 10, EnumDifficulty.MEDIUM, "none", "", new ImageIcon(MainWindow.class.getClassLoader().getResource("presentation/resources/map-cr.jpg"))}
				);
	}

	public static ManagerTableModel activitiesTableModel() {
		return new ManagerTableModel(
				new String[] {"Activity", "Time", "Capacity", "Location", "Children", "Description", "Price"},
				new Object[] {"title", LocalTime.of(0, 0), 10, "", false, "none", 0.0f}
				);
	}

	private Object[] rowTemplate;
	private String[] columnNames;
	private Vector<Object[]> data;
	
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
	
	public Object[] getRowTemplate() {
		return rowTemplate;
	}

}
