package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PanelAccount extends MainPanel {

	private static final int avatarSize = 96;

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
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(15);
		panel.add(panel_1, BorderLayout.NORTH);
		
		lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPhoto);
		
		btnChangeAvatar = new JButton("Change Avatar");
		btnChangeAvatar.addActionListener(new BtnChangeAvatarActionListener());
		panel_1.add(btnChangeAvatar);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(15);
		panel.add(panel_2, BorderLayout.CENTER);
		
		lblName = new JLabel("Name Surname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblName);
		
		lblLogin = new JLabel("LOGIN");
		panel_2.add(lblLogin);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(15);
		flowLayout_3.setHgap(15);
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
		setAvatar(user.getAvatar());
		lblName.setText(user.getName());
	}
	
	private void setAvatar(ImageIcon img) {
		lblPhoto.setIcon(IAppWindow.resizeImage(img, avatarSize, avatarSize));
	}

	private class BtnChangeAvatarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc = new JFileChooser();
			int v = fc.showOpenDialog(getMain().getFrame());

			if (v == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				setAvatar(new ImageIcon(file.getAbsolutePath()));
				getMain().log("Updated avatar");
			}
		}
	}
}
