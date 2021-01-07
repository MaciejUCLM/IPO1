package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class ImageWindow implements IAppWindow {
	
	public static IAppWindow openImagePreview(ImageIcon img) {
		ImageWindow window = (ImageWindow) IAppWindow.getController().openWindow(EnumWindows.IMAGE);
		window.setImage(img);
		return window;
	}

	private JFrame frmImg;
	private JLabel lblPhoto;

	/**
	 * Create the application.
	 */
	public ImageWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImg = new JFrame();
		//frmImg.setIconImage(Toolkit.getDefaultToolkit().getImage(HelpWindow.class.getResource("/presentation/resources/help.png")));
		frmImg.setTitle("Image Preview");
		frmImg.setBounds(100, 100, 640, 480);
		frmImg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		frmImg.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		lblPhoto = new JLabel("");
		scrollPane.setViewportView(lblPhoto);
	}
	
	public void setImage(ImageIcon img) {
		lblPhoto.setIcon(img);
	}

	@Override
	public EnumWindows getName() {
		return EnumWindows.IMAGE;
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

	@Override
	public JFrame getFrame() {
		return frmImg;
	}

}
