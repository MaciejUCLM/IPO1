package presentation;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextInputFocusListener extends FocusAdapter {
	private Color colorWhite = new Color(255,255,255);
	private Color colorFocus = new Color(255,255,200);

	@Override
	public void focusGained(FocusEvent e) {
		e.getComponent().setBackground(colorFocus);
	}
	@Override
	public void focusLost(FocusEvent e) {
		e.getComponent().setBackground(colorWhite);
	}
}
