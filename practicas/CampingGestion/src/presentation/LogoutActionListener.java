package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutActionListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		IController ctrl = IController.getController();
		ctrl.disposeAll();
		ctrl.openWindow(EnumWindows.LOGIN);
	}
}
