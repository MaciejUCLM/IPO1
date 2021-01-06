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
		reservations = new Vector<>();
	}

	@Override
	public void removeRow(int row) {
		Object[] elem = getRow(row);
		Reservation r = Arrays.stream(reservations.toArray(Reservation[]::new)).filter(x -> Arrays.equals(elem, x.data)).findFirst().get();
		reservations.remove(r);
		super.removeRow(row);
	}

	@Override
	public void addRow(Object[] row) {
		reservations.add(new Reservation(row, getCurrentTag()));
		super.addRow(row);
	}
	
	public void filter() {
		this.data.clear();
		Arrays.stream(reservations.toArray(Reservation[]::new)).filter(x -> getCurrentTag().equals(x.tag)).map(x -> x.data).forEach(this.data::add);
	}

	public String getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(String currentTag) {
		this.currentTag = currentTag;
	}

}
