package presentation;

import java.util.Locale;

import javax.swing.JButton;

public interface IMainPanel {
	
	JButton[] getToolBarButtons();

	void onLocaleChange(Locale rb);

}
