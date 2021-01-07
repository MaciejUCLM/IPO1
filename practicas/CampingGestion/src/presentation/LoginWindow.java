package presentation;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.util.Arrays;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LoginWindow implements IAppWindow {
	
	private static final Color bgDefault = new Color(100, 0, 200);
	private static final Color bgError = new Color(160, 0, 0);
	private static final Color bgSucess = new Color(11, 100, 11);

	private JFrame frmLogin;
	private JLabel lblUser;
	private JLabel lblPass;
	private JTextField textUser;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblSources;
	private JLabel lblMessage;
	private JComboBox<ImageIcon> comboLanguage;
	
	private User user = new User("Elvis Presley", new ImageIcon(LoginWindow.class.getResource("/presentation/resources/name-tag.png")));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IController ctl = IController.getController();
					ctl.openWindow(EnumWindows.LOGIN);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(bgDefault);
		frmLogin.setResizable(false);
		frmLogin.setTitle(Messages.getString("LoginWindow.frmLogin.title")); //$NON-NLS-1$
		frmLogin.setBounds(100, 100, 520, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		lblUser = new JLabel(Messages.getString("LoginWindow.lblUser.text")); //$NON-NLS-1$
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(120, 141, 270, 22);
		frmLogin.getContentPane().add(lblUser);
		
		lblPass = new JLabel(Messages.getString("LoginWindow.lblPass.text")); //$NON-NLS-1$
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(120, 195, 270, 22);
		frmLogin.getContentPane().add(lblPass);
		
		textUser = new JTextField();
		textUser.addFocusListener(new TextInputFocusListener());
		textUser.setText(user.getLogin());
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUser.setBounds(120, 162, 270, 22);
		frmLogin.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new TextInputFocusListener());
		passwordField.setText(user.getPassword());
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(120, 215, 270, 22);
		frmLogin.getContentPane().add(passwordField);
		
		btnLogin = new JButton(Messages.getString("LoginWindow.btnLogin.text")); //$NON-NLS-1$
		btnLogin.setIcon(IAppWindow.resizeImage(new ImageIcon(LoginWindow.class.getResource("/presentation/resources/enter.png")), 24, 24));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Arrays.equals(passwordField.getPassword(), user.getPassword().toCharArray()) && textUser.getText().equals(user.getLogin())) {
					log("Login succesful");
					frmLogin.getContentPane().setBackground(bgSucess);
					((MainWindow) IController.getController().openWindow(EnumWindows.MAIN)).setUser(user);
					frmLogin.dispose();
				}
				else {
					log("Incorrect login or password. Try again");
					frmLogin.getContentPane().setBackground(bgError);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setBounds(157, 248, 203, 39);
		frmLogin.getContentPane().add(btnLogin);
		
		comboLanguage = new JComboBox<>();
		comboLanguage.addItemListener(new ComboLanguageItemListener());
		comboLanguage.setModel(new LangComboModel());
		comboLanguage.setBounds(415, 300, 89, 60);
		frmLogin.getContentPane().add(comboLanguage);
		
		JLabel lblLogo = new JLabel(Messages.getString("LoginWindow.lblLogo.text")); //$NON-NLS-1$
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(10, 25, 494, 50);
		frmLogin.getContentPane().add(lblLogo);
		
		lblSources = new JLabel("Version: " + IController.getVersion());
		lblSources.setForeground(new Color(255, 255, 255));
		lblSources.setBounds(10, 346, 245, 14);
		frmLogin.getContentPane().add(lblSources);
		
		lblMessage = new JLabel(Messages.getString("LoginWindow.lblMessage.text")); //$NON-NLS-1$
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage.setBounds(10, 108, 494, 22);
		frmLogin.getContentPane().add(lblMessage);
		
	}
	
	@Override
	public void onLocaleChange() {
		// TODO implement
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.LOGIN;
	}

	@Override
	public JFrame getFrame() {
		return frmLogin;
	}

	@Override
	public void log(String msg) {
		lblMessage.setText(msg);
	}

	private class ComboLanguageItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			IController.getController().changeLocale(EnumLanguages.values()[comboLanguage.getSelectedIndex()]);
		}
	}
	
	private class LangComboModel extends DefaultComboBoxModel<ImageIcon> {
		public LangComboModel() {
			for (EnumLanguages e : EnumLanguages.values()) {
				switch (e) {
				case POLISH:
					this.addElement(new ImageIcon(LoginWindow.class.getResource("/presentation/flags/polish.png")));
					break;
				case SPANISH:
					this.addElement(new ImageIcon(LoginWindow.class.getResource("/presentation/flags/spanish.png")));
					break;
				default:
				case ENGLISH:
					this.addElement(new ImageIcon(LoginWindow.class.getResource("/presentation/flags/english.png")));
				}
			}
		}
	}
}
