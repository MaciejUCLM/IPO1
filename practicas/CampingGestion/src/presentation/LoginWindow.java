package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class LoginWindow implements IAppWindow {

	private JFrame frmLogin;
	private JLabel lblUser;
	private JLabel lblPass;
	private JTextField textUser;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblSources;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IController ctl = DefaultController.getInstance();
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
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(51, 102, 204));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Camping Manager - Login");
		frmLogin.setBounds(100, 100, 520, 360);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		lblUser = new JLabel("Username");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(80, 150, 150, 22);
		frmLogin.getContentPane().add(lblUser);
		
		lblPass = new JLabel("Password");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(80, 183, 150, 22);
		frmLogin.getContentPane().add(lblPass);
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUser.setBounds(260, 150, 140, 22);
		frmLogin.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(260, 183, 140, 22);
		frmLogin.getContentPane().add(passwordField);
		
		btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setBounds(311, 216, 89, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		JComboBox comboLanguage = new JComboBox();
		comboLanguage.setModel(new DefaultComboBoxModel(EnumLanguages.values()));
		comboLanguage.setBounds(415, 298, 89, 22);
		frmLogin.getContentPane().add(comboLanguage);
		
		JLabel lblLogo = new JLabel("Camping Manager");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(10, 25, 494, 50);
		frmLogin.getContentPane().add(lblLogo);
		
		lblSources = new JLabel("Icons source: icons8.com");
		lblSources.setForeground(new Color(255, 255, 255));
		lblSources.setBounds(10, 302, 245, 14);
		frmLogin.getContentPane().add(lblSources);
		
		lblMessage = new JLabel("Please enter your credentials");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessage.setBounds(10, 108, 494, 22);
		frmLogin.getContentPane().add(lblMessage);
		
	}
	
	// TODO: darken background on input focus

	@Override
	public void onLocaleChange(Locale rb) {
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.LOGIN;
	}

	@Override
	public void setVisible() {
		frmLogin.setVisible(true);
	}
}
