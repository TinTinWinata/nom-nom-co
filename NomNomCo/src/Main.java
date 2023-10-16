import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import adapter.PaymentAdapter;
import factory.ConfectionaryFactory;
import factory.CupcakeFactory;
import factory.TartFactory;
import model.Confectionary;
import singleton.Database;

public class Main {

	Scanner scan = new Scanner(System.in);

	public Main() {
		menu();
	}

	public void cls() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	public char getRandomFrom(String str) {
		int randomIdx = (int) (Math.random() * str.length());
		return str.charAt(randomIdx);
	}
	
	public String generateRandom(String str, int length) {
		String randomable = str;
		String code = "";
		for(int i=0;i<length;i++) {
			char temp = getRandomFrom(randomable);
			code += temp;
		}
		return code;
	}
	
	public void view() {
		Database db = Database.getInstance();
		Vector<Confectionary> confectionaries =  db.getConfectionary();

		if(confectionaries.size() <= 0) {
			System.out.println("No Confectionary Yet...");
			System.out.println("Press enter to continue...");
			scan.nextLine();
		}

		confectionaries.forEach((Confectionary conf) -> {
			String code = "";
			if(conf.getPaymentType().equals("Transfer")) {
				code = generateRandom("1234567890", 10);
			}else if(conf.getPaymentType().equals("Crypto")) {
				code = generateRandom("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM", 12);
			}
			
			System.out.printf("%-10s : %s\n", "Name", conf.getName());
			System.out.printf("%-10s : %s\n", "Softness", conf.getSoftness());
			System.out.printf("%-10s : %s", "Topping", conf.getName());
			if(conf.getToppings().size() <= 0) {
				System.out.println("-");
			}else {
				conf.getToppings().forEach((String topping) -> {
					System.out.print(topping + " ");
				});;
				System.out.println();
			}
			
			System.out.printf("%-10s : %s\n", "PaymentType", conf.getPaymentType());
			
		
			
			System.out.printf("%-10s : $ %.2f", "Price", conf.getPrice());
			
			if(!code.equals("")) {
				if(conf.getPaymentType().equals("Transfer")) {
					System.out.print(" With Account Number : " + code);
				}else if(conf.getPaymentType().equals("Crypto")) {
					System.out.print(" With Address : " + code);
				}
			}
			System.out.println();
			System.out.println("---------------------------");
		});
		System.out.println("[press enter to back]");
		scan.nextLine();
		menu();
	}

	public void insert() {
		String type, name, softness, additional,payment;
		Vector<String> toppings = new Vector<String>();
		float price;
		
		do {			
			System.out.println("Input confectionary type [Cupcake | Tart][case sensitive]: ");
			type = scan.nextLine();
		}while(!type.equals("Cupcake") && !type.equals("Tart"));
		
		do {
			System.out.println("Input confectionary name [length between 5 - 15]: ");
			name = scan.nextLine();
		}while(name.length() < 5 || name.length() > 15);
		
		
		do {
			System.out.println("Input confectionary softness [Fluffy | Medium | Hard] [case sensitive]: ");
			softness= scan.nextLine();
		}while(!softness.equals("Fluffy") && !softness.equals("Medium") && !softness.equals("Hard"));

		do {
			System.out.println("Adding additional topping [Y | N] [case sensitive]: ");
			additional= scan.nextLine();
		}while(!additional.equals("Y") && !additional.equals("N"));
		
		if(additional.equals("Y")) {
			for(int i=1;i<=3;i++) {
				String tempTopping;
				do {
					System.out.println("Input topping " + i + " [length between 1 - 10]: ");
					tempTopping = scan.nextLine();
					toppings.add(tempTopping);
				}while(tempTopping.length() < 1 || tempTopping.length() > 10);
			}
		}
		do {
			System.out.println("Input confectionary price [10.0 - 50.0]: ");
			price = scan.nextFloat();
			scan.nextLine();
		}while(price < 10 || price > 50);
		
		do {
			System.out.println("What kind of payment [Cash | Transfer | Crypto][case sensitive]: ");
			payment = scan.nextLine();
		}while(!payment.equals("Cash") && !payment.equals("Transfer") && !payment.equals("Crypto"));
		PaymentAdapter adapter = new PaymentAdapter(price);
		switch (payment) {
		case "Cash":
			price = adapter.convertCash();
			break;
		case "Transfer":
			price = adapter.convertTransfer();
			break;
		case "Crypto":
			price = adapter.convertCrypto();
			break;
		}
		ConfectionaryFactory factory = null;
		switch (type) {
			case "Cupcake":
				factory = new CupcakeFactory();
				break;
			case "Tart":
				factory = new TartFactory();
				break;
		}
		Confectionary conf =  factory.createConfectionary(toppings, name, softness, additional, price, payment);
		Database db = Database.getInstance();
		db.pushConfectionary(conf);
		
		System.out.println("Confectionary Baked!");
		System.out.println("Press enter to continue...");
		scan.nextLine();
		
		menu();
	}
 
	public void menu() {
		cls();
		System.out.println("Nom Nom Co.");
		System.out.println("=============");
		System.out.println("1. Bake Confectionary");
		System.out.println("2. View Confectionary Order");
		System.out.println("3. Exit");
		int opt = -1;

		do {
			System.out.println(">> ");
			opt = scan.nextInt();
			scan.nextLine();
		} while (opt > 3 || opt < 1);

		switch (opt) {
		case 1:
			insert();
			break;
		case 2:
			view();
			break;
		case 3:
			System.out.println("Thankyou for using nomnom service!");
			System.exit(0);
			break;
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
