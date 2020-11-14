package presentation;

import java.util.Locale;

public class DefaultController implements IController {
	
	private static IController controller = null;
	
	public static IController getInstance() {
		if (controller == null)
			controller = new DefaultController();
		return controller;
	}

	IAppWindow[] windows;
	
	private DefaultController() {
		EnumWindows[] ws = EnumWindows.values();

		windows = new IAppWindow[ws.length];
		for (EnumWindows e : ws)
			windows[e.ordinal()] = null;
	}

	@Override
	public void changeLocale(Locale rb) {
	}

	@Override
	public void openWindow(EnumWindows win) {
		if (getWindow(win) == null) {
			IAppWindow newWindow;
			switch (win) {
			case MAIN:
				newWindow = new MainWindow();
				break;
			default:
				newWindow = new LoginWindow();
			}
			windows[win.ordinal()] = newWindow;
		}
		getWindow(win).setVisible();
	}

	@Override
	public IAppWindow getWindow(EnumWindows win) {
		return windows[win.ordinal()];
	}

}
