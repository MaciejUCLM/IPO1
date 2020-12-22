package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;

public class PanelAccount extends MainPanel {
	
	private JLabel lblLogin;
	private JLabel lblPhoto;
	private JLabel lblLastAccess;
	private JLabel lblName;

	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		tools = new JButton[1];
		tools[0] = new JButton("Logout");
		tools[0].addActionListener(new LogoutActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/exit.png")), toolBarImageSize, toolBarImageSize));

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPhoto = new JLabel("");
		add(lblPhoto);
		
		lblName = new JLabel("Name Surname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblName);
		
		lblLogin = new JLabel("LOGIN");
		add(lblLogin);
		
		lblLastAccess = new JLabel("Last access time");
		add(lblLastAccess);
		
		JButton btnChangeAvatar = new JButton("Change Avatar");
		add(btnChangeAvatar);
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}
	
	public void updateUser(User user) {
		lblLastAccess.setText("Last access: " + user.getAccessTime().toString());
		lblLogin.setText(user.getLogin());
		lblPhoto.setIcon(user.getAvatar());
		lblName.setText(user.getName());
	}

}
