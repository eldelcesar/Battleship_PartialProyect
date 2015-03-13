
public class GameObject {
	protected int[] coord = new int[2];
	protected char symbol;
	
	public GameObject(int row, int column) {
		this.coord[0] = row;
		this.coord[1] = column;
	}
	
	public GameObject(int[] coord){
		this.coord = coord;
	}
	
	public int[] getCoord() {
		return coord;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
	
}