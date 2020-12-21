package presentation;

import java.util.Locale;

public interface IController {
	
	void disposeAll();
	IAppWindow openWindow(EnumWindows win);
	IAppWindow getWindow(EnumWindows win);

	void changeLocale(Locale rb);
	
}
