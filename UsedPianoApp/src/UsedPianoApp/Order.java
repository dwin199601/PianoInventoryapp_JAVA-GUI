package UsedPianoApp;

public interface Order {

	final double TAXRATE = 0.12;
	
	
	public double saleTax();
	public double totalPrice();
}
