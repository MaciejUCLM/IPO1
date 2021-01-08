package presentation;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ImageWindow implements IAppWindow {
	
	private static final double scaleSpeed = -0.05;
	
	public static IAppWindow openImagePreview(ImageIcon img) {
		ImageWindow window = (ImageWindow) IController.getController().openWindow(EnumWindows.IMAGE);
		window.setImage(img);
		return window;
	}
	
	private double imgScale = 1.0;
	private ImageIcon icon;

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
		frmImg.setTitle(Messages.getString("ImageWindow.frmImg.title")); //$NON-NLS-1$
		frmImg.setBounds(100, 100, 640, 480);
		frmImg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setWheelScrollingEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frmImg.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.addMouseWheelListener(new LblPhotoMouseWheelListener());
		scrollPane.setViewportView(lblPhoto);
	}
	
	public void setImage(ImageIcon img) {
		this.icon = img;
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
	public void onLocaleChange() {
		frmImg.setTitle(Messages.getString("ImageWindow.frmImg.title")); //$NON-NLS-1$
	}

	@Override
	public JFrame getFrame() {
		return frmImg;
	}
	
	private void setImgScale(double scale) {
		imgScale = Math.max(scale, 0.05);
	}

	private class LblPhotoMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			setImgScale(imgScale + (arg0.getWheelRotation() * scaleSpeed));
			int w = (int) (icon.getIconWidth() * imgScale);
			int h = (int) (icon.getIconHeight() * imgScale);
			lblPhoto.setIcon(IAppWindow.resizeImage(icon, w, h));
			lblPhoto.repaint();
		}
	}
}
