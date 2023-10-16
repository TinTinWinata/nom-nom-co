package model;

import java.util.Vector;

public abstract class Confectionary {
	private String name, softness, additional, paymentType;
	Vector<String> toppings;
	private float price;
	public Confectionary(String name, String softness, String additional, String paymentType, Vector<String> toppings,
			float price) {
		super();
		this.name = name;
		this.softness = softness;
		this.additional = additional;
		this.paymentType = paymentType;
		this.toppings = toppings;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSoftness() {
		return softness;
	}
	public void setSoftness(String softness) {
		this.softness = softness;
	}
	public String getAdditional() {
		return additional;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Vector<String> getToppings() {
		return toppings;
	}
	public void setToppings(Vector<String> toppings) {
		this.toppings = toppings;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
