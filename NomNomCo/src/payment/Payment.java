package payment;

public abstract class Payment {
	private int total;
	
	public Payment(int total) {
		super();
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
