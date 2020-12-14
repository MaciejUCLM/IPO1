package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class PanelAccount extends MainPanel {
	
	private static JButton[] tools;

	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		tools = new JButton[1];
		tools[0] = new JButton("Logout");
		tools[0].addActionListener(new LogoutActionListener());
		// TODO
		//tools[0].setIcon(new ImageIcon(PanelAccount.class.getResource("/presentation/flags/polish.png")));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPhoto = new JLabel("FOTO");
		GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
		gbc_lblPhoto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoto.gridx = 0;
		gbc_lblPhoto.gridy = 0;
		add(lblPhoto, gbc_lblPhoto);
		
		JLabel lblName = new JLabel("Name Surname");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);
		
		JLabel lblLogin = new JLabel("LOGIN");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 1;
		add(lblLogin, gbc_lblLogin);
		
		JButton btnChangeName = new JButton("Change Name");
		GridBagConstraints gbc_btnChangeName = new GridBagConstraints();
		gbc_btnChangeName.insets = new Insets(0, 0, 5, 0);
		gbc_btnChangeName.gridx = 1;
		gbc_btnChangeName.gridy = 1;
		add(btnChangeName, gbc_btnChangeName);
		
		JButton btnChangeAvatar = new JButton("Change Avatar");
		GridBagConstraints gbc_btnChangeAvatar = new GridBagConstraints();
		gbc_btnChangeAvatar.insets = new Insets(0, 0, 5, 0);
		gbc_btnChangeAvatar.gridx = 1;
		gbc_btnChangeAvatar.gridy = 2;
		add(btnChangeAvatar, gbc_btnChangeAvatar);
		
		JButton btnChangePassword = new JButton("Change Password");
		GridBagConstraints gbc_btnChangePassword = new GridBagConstraints();
		gbc_btnChangePassword.insets = new Insets(0, 0, 5, 0);
		gbc_btnChangePassword.gridx = 1;
		gbc_btnChangePassword.gridy = 3;
		add(btnChangePassword, gbc_btnChangePassword);
		
		JLabel lblLastAccess = new JLabel("Last access:");
		GridBagConstraints gbc_lblLastAccess = new GridBagConstraints();
		gbc_lblLastAccess.insets = new Insets(0, 0, 0, 5);
		gbc_lblLastAccess.gridx = 0;
		gbc_lblLastAccess.gridy = 4;
		add(lblLastAccess, gbc_lblLastAccess);
	}

	@Override
	public JButton[] getToolBarButtons() {
		return tools;
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

}
