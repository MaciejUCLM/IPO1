package presentation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelMap extends MainPanel {

	private SketchArea sketchMap;
	
	// Sketch fields
	private ImageIcon img;
	private EnumMapMode mode;
	private int x, y;
	private JTextField txtCaption = new JTextField();

	// Cursors and images
	private Toolkit toolkit;
	private Image imgCursorLine;
	private Image imgCursorRectangle;
	private Image imgCursorCaption;
	private Image imgCursorRemove;
	private Cursor cursorPoint;
	private Cursor cursorLine;
	private Cursor cursorRectangle;
	private Cursor cursorCaption;
	private Cursor cursorRemove;
	private Cursor cursorDefault;

	/**
	 * Create the panel.
	 */
	public PanelMap() {
		tools = new JButton[8];
		tools[0] = new JButton(Messages.getString("PanelMap.0")); //$NON-NLS-1$
		tools[0].addActionListener(new BtnClearActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/trash.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[1] = new JButton(Messages.getString("PanelMap.2")); //$NON-NLS-1$
		tools[1].addActionListener(new BtnLoadActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/download.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[2] = new JButton(Messages.getString("PanelMap.4")); //$NON-NLS-1$
		tools[2].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/save.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$
		
		tools[3] = new JButton(Messages.getString("PanelMap.6")); //$NON-NLS-1$
		tools[3].addActionListener(new BtnChangeModeActionListener(EnumMapMode.REMOVE));
		tools[3].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/erase-line.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[4] = new JButton(Messages.getString("PanelMap.8")); //$NON-NLS-1$
		tools[4].addActionListener(new BtnChangeModeActionListener(EnumMapMode.POINT));
		tools[4].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/heart-plus.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[5] = new JButton(Messages.getString("PanelMap.10")); //$NON-NLS-1$
		tools[5].addActionListener(new BtnChangeModeActionListener(EnumMapMode.LINE));
		tools[5].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/ruler.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[6] = new JButton(Messages.getString("PanelMap.12")); //$NON-NLS-1$
		tools[6].addActionListener(new BtnChangeModeActionListener(EnumMapMode.RECTANGLE));
		tools[6].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/rectangle.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[7] = new JButton(Messages.getString("PanelMap.14")); //$NON-NLS-1$
		tools[7].addActionListener(new BtnChangeModeActionListener(EnumMapMode.CAPTION));
		tools[7].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/speech-bubble.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		sketchMap = new SketchArea();
		sketchMap.addMouseListener(new SketchMouseListener());
		sketchMap.addMouseMotionListener(new SketchMouseMotionListener());
		scrollPane.setViewportView(sketchMap);

		// Images and cursors
		toolkit = Toolkit.getDefaultToolkit();
		imgCursorLine = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/ruler.png")); //$NON-NLS-1$
		imgCursorRectangle = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/rectangle.png")); //$NON-NLS-1$
		imgCursorCaption = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/speech-bubble.png")); //$NON-NLS-1$
		imgCursorRemove = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/erase-line.png")); //$NON-NLS-1$

		cursorPoint = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		cursorLine = toolkit.createCustomCursor(imgCursorLine, new Point(0,0), "CURSOR_LINE"); //$NON-NLS-1$
		cursorRectangle = toolkit.createCustomCursor(imgCursorRectangle, new Point(0,0), "CURSOR_RECTANGLE"); //$NON-NLS-1$
		cursorCaption = toolkit.createCustomCursor(imgCursorCaption, new Point(0,0), "CURSOR_CAPTION"); //$NON-NLS-1$
		cursorRemove = toolkit.createCustomCursor(imgCursorRemove, new Point(0,0), "CURSOR_REMOVE"); //$NON-NLS-1$
		cursorDefault = Cursor.getDefaultCursor();

		txtCaption.setBounds(0, 0, 200,30);
		txtCaption.setVisible(false);
		txtCaption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(!txtCaption.getText().equals("")) //$NON-NLS-1$
					sketchMap.addGraphicObject(new GraphicText(x, y, txtCaption.getText(), Color.RED));

				txtCaption.setText(""); //$NON-NLS-1$
				txtCaption.setVisible(false);
				sketchMap.repaint();
			}
		});
		sketchMap.add(txtCaption);
	}

	@Override
	public void onLocaleChange() {
		tools[0].setText(Messages.getString("PanelMap.0")); //$NON-NLS-1$
		tools[1].setText(Messages.getString("PanelMap.2")); //$NON-NLS-1$
		tools[2].setText(Messages.getString("PanelMap.4")); //$NON-NLS-1$
		tools[3].setText(Messages.getString("PanelMap.6")); //$NON-NLS-1$
		tools[4].setText(Messages.getString("PanelMap.8")); //$NON-NLS-1$
		tools[5].setText(Messages.getString("PanelMap.10")); //$NON-NLS-1$
		tools[6].setText(Messages.getString("PanelMap.12")); //$NON-NLS-1$
		tools[7].setText(Messages.getString("PanelMap.14")); //$NON-NLS-1$
	}

	private class BtnClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int v = JOptionPane.showConfirmDialog(getMain().getFrame(), Messages.getString("PanelMap.26"), Messages.getString("PanelMap.27"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			if (v == JOptionPane.YES_OPTION) {
				sketchMap.clear();
				sketchMap.repaint();
			}
		}
	}

	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (img != null) {
				int v = JOptionPane.showConfirmDialog(getMain().getFrame(), Messages.getString("PanelMap.28"), Messages.getString("PanelMap.29"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				if (v != JOptionPane.YES_OPTION)
					return;
			}
			
			Object[] row = ((MainWindow) getMain()).getSelectedRoute();
			if (row == null) {
				JOptionPane.showConfirmDialog(getMain().getFrame(), Messages.getString("PanelMap.30"), Messages.getString("PanelMap.31"), JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				getMain().log(Messages.getString("PanelMap.32")); //$NON-NLS-1$
				return;
			}

			img = (ImageIcon)(row[7]);
			sketchMap.clear();
			sketchMap.setIcon(img);
			sketchMap.repaint();
			getMain().log(Messages.getString("PanelMap.33")); //$NON-NLS-1$
		}
	}

	private class BtnChangeModeActionListener implements ActionListener {
		private EnumMapMode customMode;

		public BtnChangeModeActionListener(EnumMapMode m) {
			super();
			this.customMode = m;
		}

		public void actionPerformed(ActionEvent arg0) {
			if (mode == this.customMode) {
				mode = EnumMapMode.NONE;
			} else {
				mode = this.customMode;
			}

			Cursor c;
			switch (mode) {
			case POINT:
				c = cursorPoint;
				break;
			case LINE:
				c = cursorLine;
				break;
			case RECTANGLE:
				c = cursorRectangle;
				break;
			case CAPTION:
				c = cursorCaption;
				break;
			case REMOVE:
				c = cursorRemove;
				break;
			case NONE:
			default:
				c = cursorDefault;
			}
			getMain().getFrame().setCursor(c);
		}
	}

	private class SketchMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				// right mouse button exits tool
				mode = EnumMapMode.NONE;
				getMain().getFrame().setCursor(cursorDefault);
				return;
			}
			x = e.getX();
			y = e.getY();
			if (img != null)
			{
				switch (mode) {
				case POINT:
					sketchMap.addGraphicObject(new GraphicPoint(x, y, 10, Color.RED));
					sketchMap.repaint();
					break;
				case LINE:
					sketchMap.addGraphicObject(new GraphicLine(x, y, x, y, Color.RED));
					break;
				case RECTANGLE:
					sketchMap.addGraphicObject(new GraphicRectangle(x, y, x, y, Color.RED));
					break;
				case CAPTION:
					txtCaption.setBounds(x, y, 200,30);
					txtCaption.setVisible(true);
					txtCaption.requestFocus();
					break;
				case REMOVE:
					sketchMap.erase(x, y);
					sketchMap.repaint();
					break;
				case NONE:
				default:
					// do nothing
					break;
				}
			}
		}
	}
	private class SketchMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			if (img == null)
				return;
			else if (mode == EnumMapMode.RECTANGLE) {
				((GraphicRectangle)sketchMap.getLastGraphicObject()).setX1(e.getX());
				((GraphicRectangle)sketchMap.getLastGraphicObject()).setY1(e.getY());
				sketchMap.repaint();
			} else if (mode == EnumMapMode.LINE) {
				((GraphicLine)sketchMap.getLastGraphicObject()).setX1(e.getX());
				((GraphicLine)sketchMap.getLastGraphicObject()).setY1(e.getY());
				sketchMap.repaint();
			}
		}
	}
}
