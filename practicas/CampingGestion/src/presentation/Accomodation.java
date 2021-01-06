package presentation;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Accomodation {

	private String[] tags;
	private int capacity;
	private float price;
	private EnumObjectStates state;
	private String features;
	private Vector<ImageIcon> images;
	
	public Accomodation() {
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public EnumObjectStates getState() {
		return state;
	}

	public void setState(EnumObjectStates state) {
		this.state = state;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Vector<ImageIcon> getImages() {
		return images;
	}

	public void setImages(Vector<ImageIcon> images) {
		this.images = images;
	}
	
}
