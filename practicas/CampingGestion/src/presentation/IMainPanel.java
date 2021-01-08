package presentation;

import javax.swing.JButton;

public interface IMainPanel {
	
	JButton[] getToolBarButtons();

	void onLocaleChange();

}
