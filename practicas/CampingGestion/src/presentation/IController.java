package presentation;

import java.util.Locale;

public interface IController {
	
	void disposeAll();
	void openWindow(EnumWindows win);
	IAppWindow getWindow(EnumWindows win);

	void changeLocale(Locale rb);
	
}
