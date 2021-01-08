package presentation;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class AccomodationTreeNode extends DefaultMutableTreeNode {

	private String[] tags = new String[] {};
	private int capacity = 2;
	private float price = 0.0f;
	private EnumObjectStates state = EnumObjectStates.READY;
	private String features = "";
	private Vector<ImageIcon> images = new Vector<>();
	
	public AccomodationTreeNode(String n) {
		super(n);
		retrieveTags();
	}
	
	public AccomodationTreeNode(Object child) {
		super(child);
		retrieveTags();
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

	public ImageIcon[] getImages() {
		return images.toArray(ImageIcon[]::new);
	}

	public void addImage(ImageIcon img) {
		this.images.add(img);
	}
	
	public void removeImage(ImageIcon img) {
		this.images.remove(img);
	}
	
	private void retrieveTags() {
		if (this.getParent() != null)
			System.err.println("Parent is not null! Problem solved!");
		// TODO check if error persists when executed lazily
		setTags(Arrays.stream(this.getPath()).map(x -> x.toString()).toArray(String[]::new));
	}
	
}
