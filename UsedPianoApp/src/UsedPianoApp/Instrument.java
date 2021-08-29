package UsedPianoApp;

public abstract class Instrument {

	protected String brand;
	protected int ManufacturedYear;
	protected String model;
	
	
	public Instrument() {
		
	}
public Instrument(String brand, int ManufacturedYear, String model) {
	this.brand = brand;
	this.ManufacturedYear =ManufacturedYear;
	this.model = model;
	
	}
	
	public void Play() {
		
	}
}
