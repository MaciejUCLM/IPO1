package presentation;

import java.awt.Color;

public class GraphicPoint extends GraphicObject {

	private Color color;
	private int size;

	public GraphicPoint(int x, int y, int size, Color color) {
		super(x, y);
		this.color = color;
		this.size = size;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}
	public int getSize() {
		return size;
	}

}
