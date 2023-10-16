package adapter;


public class PaymentAdapter {
	private float price;
	public PaymentAdapter(float price) {
		this.price = price;
	}
	public float convertCrypto() {
		return this.price / 2;
	}
	
	public float convertCash() {
		return this.price * 1;
	}
	
	public float convertTransfer() {
		return this.price * 1.1f;
	}
}


