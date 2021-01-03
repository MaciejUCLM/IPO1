package presentation;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.JLabel;

public class SketchArea extends JLabel {

	private ArrayList<GraphicObject> graphicObjects = new ArrayList<GraphicObject>();
	private double eraseThreshold = 25.0;

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
	
	public void erase(int x, int y) {
		double distArray[] = new double[graphicObjects.size()];
		for (int i = 0; i < graphicObjects.size(); i++) {
			GraphicObject objg = graphicObjects.get(i);
			distArray[i] = Math.hypot(x - objg.getX(), y - objg.getY());
		}
		double min = Arrays.stream(distArray).min().orElseThrow();
		if (min < eraseThreshold)
			graphicObjects.remove(Arrays.stream(distArray).boxed().collect(Collectors.toList()).indexOf(min));
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (GraphicObject objg : graphicObjects) {

			//g.drawImage(((GraphicImage)objg).getImage(), objg.getX(), objg.getY(), null);

			if (objg instanceof GraphicPoint) {
				int s = ((GraphicPoint)objg).getSize();
				g.setColor(((GraphicPoint)objg).getColor());
				g.fillOval(objg.getX(), objg.getY(), s, s);
			}

			else if (objg instanceof GraphicLine) {
				g.setColor(((GraphicLine)objg).getColor());
				int x1 = ((GraphicLine)objg).getX1();
				int y1 = ((GraphicLine)objg).getY1();
				if (objg instanceof GraphicRectangle) {
					g.drawRect(objg.getX(), objg.getY(), Math.abs(x1 - objg.getX()), Math.abs(y1 - objg.getY()));
				}
				else {
					g.drawLine(objg.getX(), objg.getY(), x1, y1);
				}
			}

			else if (objg instanceof GraphicText) {
				g.setColor(((GraphicText)objg).getColor());
				g.drawString(((GraphicText)objg).getText(), objg.getX(), objg.getY());
			}
		} 
	}
}
