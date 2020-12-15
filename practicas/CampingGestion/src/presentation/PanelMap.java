package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
	private static JButton[] tools;
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
		
		// TODO
		tools[0] = new JButton("Clear route");

		tools[1] = new JButton("Load route");
		tools[1].addActionListener(new BtnLoadActionListener());

		tools[2] = new JButton("Save route");
		
		tools[3] = new JButton("Remove");
		tools[3].addActionListener(new BtnChangeModeActionListener(EnumMapMode.REMOVE));
		tools[3].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/erase-line.png")), 24, 24));

		tools[4] = new JButton("Point");
		tools[4].addActionListener(new BtnChangeModeActionListener(EnumMapMode.POINT));
		tools[4].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/heart-plus.png")), 24, 24));

		tools[5] = new JButton("Line");
		tools[5].addActionListener(new BtnChangeModeActionListener(EnumMapMode.LINE));
		tools[5].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/ruler.png")), 24, 24));

		tools[6] = new JButton("Rectangle");
		tools[6].addActionListener(new BtnChangeModeActionListener(EnumMapMode.RECTANGLE));
		tools[6].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/rectangle.png")), 24, 24));

		tools[7] = new JButton("Caption");
		tools[7].addActionListener(new BtnChangeModeActionListener(EnumMapMode.CAPTION));
		tools[7].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/speech-bubble.png")), 24, 24));

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
	}

	@Override
	public JButton[] getToolBarButtons() {
		return tools;
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

	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// TODO confirm reject unsaved changes
			JFileChooser fcAbrir = new JFileChooser();
			int valor = fcAbrir.showOpenDialog(getFrame());

			if (valor == JFileChooser.APPROVE_OPTION) {
				File file = fcAbrir.getSelectedFile();
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
			x = e.getX();
			y = e.getY();
			if (img != null)
			{
				switch (mode) {
				// TODO
				case POINT:
					sketchMap.addGraphicObject(null);
					sketchMap.repaint();
					break;
				case LINE:
					break;
				case RECTANGLE:
					sketchMap.addGraphicObject(new GraphicRectangle(x, y, x, y, Color.RED));
					break;
				case CAPTION:
					txtCaption.setBounds(x, y, 200,30);
					txtCaption.setVisible(true);
					txtCaption.requestFocus();
					txtCaption.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							if(!txtCaption.getText().equals(""))
								// TODO
								//miAreaDibujo.addObjetoGrafico(new TextoGrafico(x, y+15, txtCaption.getText(), Color.RED));
								sketchMap.addGraphicObject(null);

							txtCaption.setText("");
							txtCaption.setVisible(false);
							sketchMap.repaint();
						}
					});
					sketchMap.add(txtCaption);
					break;
				case REMOVE:
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
			}
		}
	}
}
