package presentation;

import java.util.Locale;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class HelpWindow implements IAppWindow {

	private JFrame frmHelpOfCamping;

	/**
	 * Create the application.
	 */
	public HelpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHelpOfCamping = new JFrame();
		frmHelpOfCamping.setIconImage(Toolkit.getDefaultToolkit().getImage(HelpWindow.class.getResource("/presentation/resources/help.png")));
		frmHelpOfCamping.setTitle("Help Camping Manager");
		frmHelpOfCamping.setBounds(100, 100, 450, 300);
		frmHelpOfCamping.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.HELP;
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
		return frmHelpOfCamping;
	}

}
