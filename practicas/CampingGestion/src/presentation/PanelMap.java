package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelMap extends MainPanel {

	private JFrame frame;
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
		tools[0] = new JButton("Clear route");
		tools[0].addActionListener(new BtnClearActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/trash.png")), toolBarImageSize, toolBarImageSize));

		tools[1] = new JButton("Load route");
		tools[1].addActionListener(new BtnLoadActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/download.png")), toolBarImageSize, toolBarImageSize));

		tools[2] = new JButton("Save route");
		tools[2].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/save.png")), toolBarImageSize, toolBarImageSize));
		
		tools[3] = new JButton("Erase");
		tools[3].addActionListener(new BtnChangeModeActionListener(EnumMapMode.REMOVE));
		tools[3].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/erase-line.png")), toolBarImageSize, toolBarImageSize));

		tools[4] = new JButton("Point");
		tools[4].addActionListener(new BtnChangeModeActionListener(EnumMapMode.POINT));
		tools[4].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/heart-plus.png")), toolBarImageSize, toolBarImageSize));

		tools[5] = new JButton("Line");
		tools[5].addActionListener(new BtnChangeModeActionListener(EnumMapMode.LINE));
		tools[5].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/ruler.png")), toolBarImageSize, toolBarImageSize));

		tools[6] = new JButton("Rectangle");
		tools[6].addActionListener(new BtnChangeModeActionListener(EnumMapMode.RECTANGLE));
		tools[6].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/rectangle.png")), toolBarImageSize, toolBarImageSize));

		tools[7] = new JButton("Caption");
		tools[7].addActionListener(new BtnChangeModeActionListener(EnumMapMode.CAPTION));
		tools[7].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/speech-bubble.png")), toolBarImageSize, toolBarImageSize));

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		sketchMap = new SketchArea();
		sketchMap.addMouseListener(new SketchMouseListener());
		sketchMap.addMouseMotionListener(new SketchMouseMotionListener());
		scrollPane.setViewportView(sketchMap);

		// Images and cursors
		toolkit = Toolkit.getDefaultToolkit();
		imgCursorLine = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/ruler.png"));
		imgCursorRectangle = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/rectangle.png"));
		imgCursorCaption = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/speech-bubble.png"));
		imgCursorRemove = toolkit.getImage(getClass().getClassLoader().getResource("presentation/resources/erase-line.png"));

		cursorPoint = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		cursorLine = toolkit.createCustomCursor(imgCursorLine, new Point(0,0), "CURSOR_LINE");
		cursorRectangle = toolkit.createCustomCursor(imgCursorRectangle, new Point(0,0), "CURSOR_RECTANGLE");
		cursorCaption = toolkit.createCustomCursor(imgCursorCaption, new Point(0,0), "CURSOR_CAPTION");
		cursorRemove = toolkit.createCustomCursor(imgCursorRemove, new Point(0,0), "CURSOR_REMOVE");
		cursorDefault = Cursor.getDefaultCursor();

		txtCaption.setBounds(0, 0, 200,30);
		txtCaption.setVisible(false);
		txtCaption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(!txtCaption.getText().equals(""))
					sketchMap.addGraphicObject(new GraphicText(x, y, txtCaption.getText(), Color.RED));

				txtCaption.setText("");
				txtCaption.setVisible(false);
				sketchMap.repaint();
			}
		});
		sketchMap.add(txtCaption);
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}
	
	private JFrame getFrame() {
		if (frame == null)
			frame = IAppWindow.getController().getWindow(EnumWindows.MAIN).getFrame();
		return frame;
	}

	private class BtnClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int v = JOptionPane.showConfirmDialog(getFrame(), "Are you sure you want to remove all map markings?", "Clear route", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (v == JOptionPane.YES_OPTION) {
				sketchMap.clear();
				sketchMap.repaint();
			}
		}
	}

	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// TODO load map from last route selected on PanelRoutes
			// TODO confirm reject unsaved changes
			JFileChooser fc = new JFileChooser();
			int valor = fc.showOpenDialog(getFrame());

			if (valor == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				img = new ImageIcon(file.getAbsolutePath());
				sketchMap.clear();
				sketchMap.setIcon(img);
			}
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
			getFrame().setCursor(c);
		}
	}

	private class SketchMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				// right mouse button exits tool
				mode = EnumMapMode.NONE;
				getFrame().setCursor(cursorDefault);
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
