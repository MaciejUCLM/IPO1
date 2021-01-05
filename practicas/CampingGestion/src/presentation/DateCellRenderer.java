package presentation;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
    public Component getTableCellRendererComponent(JTable myTable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(myTable, value, isSelected, hasFocus, row, column);
        if (!(value instanceof Date)) {
            return this;
        }
        setText(DATE_FORMAT.format((Date) value));
        return this;
    }

}
