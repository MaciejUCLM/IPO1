package presentation;

import java.util.Locale;

public interface IAppWindow {

	EnumWindows getName();

	void onLocaleChange(Locale rb);

	void setVisible();

}
