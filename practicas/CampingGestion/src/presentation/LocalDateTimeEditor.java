package presentation;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

public class LocalDateTimeEditor extends DefaultCellEditor {

	private JFormattedTextField ftf;

	public LocalDateTimeEditor(DateTimeFormatter pattern) {
		super(new JFormattedTextField());
		ftf = (JFormattedTextField) getComponent();
		ftf.setFormatterFactory(new DefaultFormatterFactory(new JTFormatter(pattern)));
	}
	
	private class JTFormatter extends JFormattedTextField.AbstractFormatter {

	    private final DateTimeFormatter formatter;

	    public JTFormatter(DateTimeFormatter formatter){
	        this.formatter =  formatter;
	    }

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return formatter.parse(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if(value instanceof TemporalAccessor) {
	            return formatter.format((TemporalAccessor) value);
	        } else {
	            throw new ParseException("Not a valid type at", 0);
	        }
	    }

	}
	  
}
