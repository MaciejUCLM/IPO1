package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PanelAccount extends MainPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblPhoto;
	private JPanel panel_2;
	private JLabel lblName;
	private JPanel panel_3;
	private JLabel lblLastAccess;
	private JLabel lblLogin;
	private JButton btnChangeAvatar;

	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		tools = new JButton[1];
		tools[0] = new JButton("Logout");
		tools[0].addActionListener(new LogoutActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/exit.png")), toolBarImageSize, toolBarImageSize));

		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
		setLayout(flowLayout);
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		lblPhoto = new JLabel("");
		panel_1.add(lblPhoto);
		
		btnChangeAvatar = new JButton("Change Avatar");
		panel_1.add(btnChangeAvatar);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		
		lblName = new JLabel("Name Surname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblName);
		
		lblLogin = new JLabel("LOGIN");
		panel_2.add(lblLogin);
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		lblLastAccess = new JLabel("Last access time");
		panel_3.add(lblLastAccess);
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
