package presentation;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AboutWindow implements IAppWindow {

	private JFrame frmAboutCampingManager;

	private JTextArea taAbout;

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
		frmAboutCampingManager.setIconImage(Toolkit.getDefaultToolkit().getImage(AboutWindow.class.getResource("/presentation/resources/about.png"))); //$NON-NLS-1$
		frmAboutCampingManager.setTitle(Messages.getString("AboutWindow.frmAboutCampingManager.title")); //$NON-NLS-1$
		frmAboutCampingManager.setBounds(100, 100, 450, 300);
		frmAboutCampingManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlButtons = new JPanel();
		frmAboutCampingManager.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK"); //$NON-NLS-1$
		btnOk.addActionListener(new BtnOkActionListener());
		pnlButtons.add(btnOk);
		
		taAbout = new JTextArea();
		taAbout.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		String about = Messages.getString("AboutWindow.aboutA") //$NON-NLS-1$
				+ IController.getVersion() + Messages.getString("AboutWindow.aboutB"); //$NON-NLS-1$
		taAbout.setText(about);
		taAbout.setEditable(false);
		taAbout.setLineWrap(true);
		taAbout.setWrapStyleWord(true);
		frmAboutCampingManager.getContentPane().add(taAbout, BorderLayout.CENTER);
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
	public void onLocaleChange() {
		frmAboutCampingManager.setTitle(Messages.getString("AboutWindow.frmAboutCampingManager.title")); //$NON-NLS-1$
		String about = Messages.getString("AboutWindow.aboutA") //$NON-NLS-1$
				+ IController.getVersion() + Messages.getString("AboutWindow.aboutB"); //$NON-NLS-1$
		taAbout.setText(about);
	}

	@Override
	public JFrame getFrame() {
		return frmAboutCampingManager;
	}

	private class BtnOkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmAboutCampingManager.dispose();
		}
	}
}
