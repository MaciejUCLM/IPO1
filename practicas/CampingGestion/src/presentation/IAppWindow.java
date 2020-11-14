package presentation;

import java.util.Locale;

public interface IAppWindow {

	static IController getController() {
		return DefaultController.getInstance();
	}

	EnumWindows getName();

	void onLocaleChange(Locale rb);

	void setVisible();
	void setVisible(boolean state);

}
