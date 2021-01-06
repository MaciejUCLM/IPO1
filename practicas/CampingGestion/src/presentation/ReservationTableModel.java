package presentation;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class ReservationTableModel extends ManagerTableModel {
	
	private class Reservation {
		public Object[] data;
		public String tag;
		
		public Reservation(Object[] data, String tag) {
			this.data = data;
			this.tag = tag;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	private Vector<Reservation> reservations;
	private String currentTag = "";
	
	public ReservationTableModel() {
		super(
				new String[] {"Client", "Start date", "Finish date", "Occupants", "Phone", "e-mail", "Price", "Comments"},
				new Object[] {"?", new Date(), new Date(), 1, "", "@", 0.0f, ""}
		);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		super.setValueAt(value, row, col);
	}
	
	@Override
	public void removeRow(int row) {
		super.removeRow(row);
	}

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
	}

	public String getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(String currentTag) {
		this.currentTag = currentTag;
	}

}
