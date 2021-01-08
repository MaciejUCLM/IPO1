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
				new String[] {Messages.getString("ReservationTableModel.0"), Messages.getString("ReservationTableModel.1"), Messages.getString("ReservationTableModel.2"), Messages.getString("ReservationTableModel.3"), Messages.getString("ReservationTableModel.4"), Messages.getString("ReservationTableModel.5"), Messages.getString("ReservationTableModel.6"), Messages.getString("ReservationTableModel.7"), Messages.getString("ReservationTableModel.8")}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				new Object[] {"?", new Date(), new Date(), 1, "", "@", 0.0f, "", ""} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
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
		String colTags = ""; //$NON-NLS-1$
		String[] tags = getCurrentTags();
		for (int i = tags.length - 1; i >= 0; i--) {
			colTags += tags[i] + ","; //$NON-NLS-1$
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
