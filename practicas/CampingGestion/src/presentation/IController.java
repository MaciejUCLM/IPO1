package presentation;

import java.awt.Component;
import java.util.Locale;

public interface IController {

	EnumScreens getCurrentScreen();
	Component getCurrentComponent();

	void showScreen(EnumScreens screen);
	void showScreenComponent(Component c);

	void changeLocale(Locale rb);
}
