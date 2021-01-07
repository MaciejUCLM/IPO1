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
	public void disposeAll() {
		for (int i = 0; i < windows.length; i++) {
			if (windows[i] != null) {
				windows[i].getFrame().dispose();
				windows[i] = null;
			}
		}
	}

	@Override
	public void changeLocale(Locale rb) {
		// TODO
	}

	@Override
	public IAppWindow openWindow(EnumWindows win) {
		IAppWindow newWindow = getWindow(win);
		if (newWindow == null) {
			switch (win) {
			case MAIN:
				newWindow = new MainWindow();
				break;
			case HELP:
				newWindow = new HelpWindow();
				break;
			case ABOUT:
				newWindow = new AboutWindow();
				break;
			case IMAGE:
				newWindow = new ImageWindow();
				break;
			case LOGIN:
			default:
				newWindow = new LoginWindow();
			}
			windows[win.ordinal()] = newWindow;
		}
		newWindow.getFrame().setVisible(true);
		return newWindow;
	}

	@Override
	public IAppWindow getWindow(EnumWindows win) {
		return windows[win.ordinal()];
	}

}
