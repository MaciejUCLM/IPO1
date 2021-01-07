package presentation;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public interface IAppWindow {

	static ImageIcon resizeImage(ImageIcon src, int w, int h){
		Image resizedImg;
		resizedImg = src.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImg);
	}

	static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	EnumWindows getName();
	
	void log(String msg);

	void onLocaleChange(Locale rb);

	JFrame getFrame();

}
