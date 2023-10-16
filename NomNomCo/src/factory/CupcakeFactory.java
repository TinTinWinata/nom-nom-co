package factory;

import java.util.Vector;

import model.Confectionary;
import model.Cupcake;

public class CupcakeFactory implements ConfectionaryFactory{

	@Override
	public Confectionary createConfectionary(Vector<String> toppings, String name, String softness, String additional,
			float price, String paymentType) {
		// TODO Auto-generated method stub
		return new Cupcake(name, softness, additional, paymentType, toppings, price);
	}
}
