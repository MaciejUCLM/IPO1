package presentation;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;

public class SketchArea extends JLabel {
	private ArrayList<GraphicObject> graphicObjects = new ArrayList<GraphicObject>();

	public SketchArea() {
		super("");
	}
	
	void clear() {
		graphicObjects.clear();
	}

	void addGraphicObject(GraphicObject objg) {
		graphicObjects.add(objg);
	}

	public GraphicObject getLastGraphicObject() {
		return graphicObjects.get(graphicObjects.size() - 1);
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < graphicObjects.size(); i++) {

			GraphicObject objg = graphicObjects.get(i);

			/*if (objg instanceof ImagenGrafico)
				g.drawImage(((ImagenGrafico)objg).getImagen(), objg.getX(), objg.getY(), null);

			else if (objg instanceof RectanguloGrafico) {
				g.setColor(((RectanguloGrafico)objg).getColor());
				int w = ((RectanguloGrafico)objg).getX1() - objg.getX();
				int h = ((RectanguloGrafico)objg).getY1() - objg.getY();
				g.drawRect(objg.getX(),objg.getY(),w,h);
			}

			else if (objg instanceof TextoGrafico) {
				g.setColor(((TextoGrafico)objg).getColor());
				g.drawString(((TextoGrafico)objg).getTexto(),objg.getX(),objg.getY());
			}*/
		} 
	}
}
