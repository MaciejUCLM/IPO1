package presentation;

import java.util.Locale;

import javax.swing.JFrame;

public class AboutWindow implements IAppWindow {

	private JFrame frmAboutCampingManager;

	/**
	 * Create the application.
	 */
	public AboutWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAboutCampingManager = new JFrame();
		frmAboutCampingManager.setTitle("About Camping Manager");
		frmAboutCampingManager.setBounds(100, 100, 450, 300);
		frmAboutCampingManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.ABOUT;
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

	@Override
	public JFrame getFrame() {
		return frmAboutCampingManager;
	}

}
