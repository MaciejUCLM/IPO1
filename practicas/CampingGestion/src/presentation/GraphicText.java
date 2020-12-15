package presentation;

import java.awt.Color;

public class GraphicText extends GraphicObject {

	private String text;
	private Color color;

	public GraphicText(int x, int y, String text, Color color) {
		super(x,y);
		this.text = text;
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}
	public Color getColor() {
		return color;
	}

}
