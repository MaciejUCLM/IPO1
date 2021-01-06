package presentation;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class ReservationTableModel extends ManagerTableModel {
	
	private class Reservation {
		public Object[] data;
		public String[] tags;
		
		public Reservation(Object[] data, String[] tags) {
			this.data = data;
			this.tags = tags;
		}
		
		public String toString() {
			return tags[tags.length - 1];
		}
		
		public boolean tagsMatch() {
			if (currentTags.length > this.tags.length)
				return false;
			String[] minTags = Arrays.stream(this.tags).limit(currentTags.length).toArray(String[]::new);
			return Arrays.equals(minTags, currentTags);
		}
	}
	
	private Vector<Reservation> reservations;
	private String[] currentTags;
	
	public ReservationTableModel() {
		super(
				new String[] {"Client", "Start date", "Finish date", "Occupants", "Phone", "e-mail", "Price", "Comments", "Tags"},
				new Object[] {"?", new Date(), new Date(), 1, "", "@", 0.0f, "", ""}
		);
		reservations = new Vector<>();
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return col != 8;
	}

	@Override
	public void removeRow(int row) {
		Object[] elem = getRow(row);
		Reservation r = Arrays.stream(reservations.toArray(Reservation[]::new)).filter(x -> elem == x.data).findFirst().get();
		reservations.remove(r);
		super.removeRow(row);
	}

	@Override
	public void addRow(Object[] row) {
		String colTags = "";
		String[] tags = getCurrentTags();
		for (int i = tags.length - 1; i >= 0; i--) {
			colTags += tags[i] + ",";
		}
		row[8] = colTags.substring(0, colTags.length() - 1);
		reservations.add(new Reservation(row, getCurrentTags()));
		super.addRow(row);
	}
	
	public void filter() {
		this.data.clear();
		Arrays.stream(reservations.toArray(Reservation[]::new)).filter(x -> x.tagsMatch()).map(x -> x.data).forEach(this.data::add);
		fireTableDataChanged();
	}

	public String[] getCurrentTags() {
		return currentTags;
	}

	public void setCurrentTags(String[] tags) {
		this.currentTags = tags;
	}

}
