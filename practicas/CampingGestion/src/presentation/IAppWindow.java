package presentation;

import java.util.Locale;

import javax.swing.JFrame;

public interface IAppWindow {

	static IController getController() {
		return DefaultController.getInstance();
	}

	EnumWindows getName();
	
	void log(String msg);

	void onLocaleChange(Locale rb);

	JFrame getFrame();

}
