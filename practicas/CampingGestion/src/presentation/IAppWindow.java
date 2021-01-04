package presentation;

import java.awt.Image;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public interface IAppWindow {

	static IController getController() {
		return DefaultController.getInstance();
	}

	static ImageIcon resizeImage(ImageIcon src, int w, int h){
		Image resizedImg;
		resizedImg = src.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImg);
	}

	EnumWindows getName();
	
	void log(String msg);

	void onLocaleChange(Locale rb);

	JFrame getFrame();

}
