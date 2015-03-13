
public class ShipSection extends GameObject{

	private int location; //Starts to count from ship origin
	private int length;
	
	public ShipSection(int row, int column, int location, boolean user) {
		super(row, column);
		this.location = location;
		//Para visualizar los barcos del enemigo, cambiar la línea 11 a: this.symbol= (user?'b':'b');
		this.symbol = (user?'b':' ');
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public int getLength() {
		return this.length;
	}
	

	
}
