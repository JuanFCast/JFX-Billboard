package model;

import java.io.Serializable;

public class Billboard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private double width;
	private double height;
	private boolean inUse;
	private String brand;
	
	//Constructor
	public Billboard(double width, double height, boolean inUse, String brand) {
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}
	

	public double getWidth() {
		return width;
	}

	

	public double getHeight() {
		return height;
	}

	

	public boolean isInUse() {
		return inUse;
	}

	
	public String getBrand() {
		return brand;
	}

	
	
	public double calculateArea() {
		return width*height;
	}
}
