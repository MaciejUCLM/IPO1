package presentation;

import java.util.Date;

import javax.swing.ImageIcon;

public class User {

	private String password;
	private String login;
	
	private String name;
	private ImageIcon avatar;
	
	private Date accessTime;
	
	public User(String name, ImageIcon avatar) {
		this("password", "MyLogin", name, avatar);
	}

	public User(String password, String login, String name, ImageIcon avatar) {
		this.setAccessTime(new Date());
		this.setPassword(password);
		this.setLogin(login);
		this.setName(name);
		this.setAvatar(avatar);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageIcon getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageIcon avatar) {
		this.avatar = avatar;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

}
