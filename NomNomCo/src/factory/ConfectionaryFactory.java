package factory;

import java.util.Vector;

import model.Confectionary;

public interface ConfectionaryFactory {
	public abstract Confectionary createConfectionary(
			Vector<String> toppings, 
			String name, 
			String softness, 
			String additional, 
			float price, 
			String paymentType);
}
