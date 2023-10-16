package factory;

import java.util.Vector;

import model.Confectionary;
import model.Tart;

public class TartFactory implements ConfectionaryFactory {

	@Override
	public Confectionary createConfectionary(Vector<String> toppings, String name, String softness, String additional,
			float price, String paymentType) {
		// TODO Auto-generated method stub
		return new Tart(name, softness, additional, paymentType, toppings, price);
	}
}
