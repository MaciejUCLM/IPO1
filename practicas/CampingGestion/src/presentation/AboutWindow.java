package presentation;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextArea;

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
		frmAboutCampingManager.setIconImage(Toolkit.getDefaultToolkit().getImage(AboutWindow.class.getResource("/presentation/resources/about.png")));
		frmAboutCampingManager.setTitle("About Camping Manager");
		frmAboutCampingManager.setBounds(100, 100, 450, 300);
		frmAboutCampingManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlButtons = new JPanel();
		frmAboutCampingManager.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new BtnOkActionListener());
		pnlButtons.add(btnOk);
		
		JTextArea taAbout = new JTextArea();
		taAbout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taAbout.setText("This application is a project made for the purpose of practice in the subject Human-Computer Interactions 1 on the University of Castilla La Mancha.\r\n\r\nAuthors: Maciej Nalepa, Piotr Maliszewski\r\n\r\nVersion: "+IController.getVersion()+"\r\n\r\nProject uses following sources:\r\n- https://icons8.com/");
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
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
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
