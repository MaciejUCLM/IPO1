package presentation;

import java.util.Locale;

public interface IController {
	
	static String getVersion() {
		return "1.0.0";
	}

	static IController getController() {
		return DefaultController.getInstance();
	}
	
	void disposeAll();
	IAppWindow openWindow(EnumWindows win);
	IAppWindow getWindow(EnumWindows win);

	void changeLocale(Locale rb);
	
}
