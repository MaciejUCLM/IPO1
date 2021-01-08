package presentation;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class PanelAccount extends MainPanel {

	private static final int avatarSize = 96;

	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnChangeAvatar;
	private JLabel lblPhoto;
	private JLabel lblLastAccess;
	private JPanel panel_3;
	private JLabel lblName;
	private JLabel lblLogin;

	/**
	 * Create the panel.
	 */
	public PanelAccount() {
		tools = new JButton[1];
		tools[0] = new JButton(Messages.getString("PanelAccount.0")); //$NON-NLS-1$
		tools[0].addActionListener(new LogoutActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/exit.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 0};
		gridBagLayout.rowHeights = new int[]{64, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(20, 20));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		
		btnChangeAvatar = new JButton(Messages.getString("PanelAccount.btnChangeAvatar.text")); //$NON-NLS-1$
		btnChangeAvatar.addActionListener(new BtnChangeAvatarActionListener());
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(btnChangeAvatar, BorderLayout.SOUTH);
		
		lblPhoto = new JLabel(""); //$NON-NLS-1$
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPhoto);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblLastAccess = new JLabel("Last access time"); //$NON-NLS-1$
		panel_2.add(lblLastAccess, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(15);
		panel_2.add(panel_3, BorderLayout.CENTER);
		
		lblName = new JLabel("Name Surname"); //$NON-NLS-1$
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16)); //$NON-NLS-1$
		panel_3.add(lblName);
		
		lblLogin = new JLabel("LOGIN"); //$NON-NLS-1$
		panel_3.add(lblLogin);
	}

	@Override
	public void onLocaleChange() {
		tools[0].setText(Messages.getString("PanelAccount.0")); //$NON-NLS-1$
		btnChangeAvatar.setText(Messages.getString("PanelAccount.btnChangeAvatar.text")); //$NON-NLS-1$
	}
	
	public void updateUser(User user) {
		lblLastAccess.setText(Messages.getString("PanelAccount.7") + user.getAccessTime().toString()); //$NON-NLS-1$
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
				getMain().log(Messages.getString("PanelAccount.8")); //$NON-NLS-1$
			}
		}
	}
}
