
public class Ships extends GameObject{
	
	private boolean orientation;
	private int length;
	private ShipSection[] ship;
	private int HP;
	private boolean user;

	public Ships(int row, int column, boolean orientation,int length, boolean user){
		super(row,column);
		this.orientation = orientation;
		this.HP = this.length = length;
		this.ship = new ShipSection[this.length];
		this.user = user;
		setSectionCoords(this.orientation);

	}
	
	private void setSectionCoords(boolean orientation) {
		if(orientation) {
			this.ship[0] = new ShipSection(this.coord[0] , this.coord[1],1,user);
			for (int i = 0; i < this.length - 2; i++) {
				this.ship[i + 1] = new ShipSection(this.coord[0],this.coord[1] + i + 1,i + 2,user);
			}
			this.ship[this.length - 1] = new ShipSection(this.coord[0],this.coord[1] + this.length - 1,this.length, user);
		} else {
			this.ship[0] = new ShipSection(this.coord[0],this.coord[1],1, user);
			for (int i = 0; i < this.length - 2; i++) {
				this.ship[i + 1] = new ShipSection(this.coord[0] + i + 1,this.coord[1],i + 2, user);
			}
			this.ship[this.length - 1] = new ShipSection(this.coord[0] + this.length - 1,this.coord[1],this.length, user);
		}
		
	}

	public boolean getOrientation() {
		return this.orientation;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public ShipSection[] getShip() {
		return this.ship;
	}
	
	public void decreaseHP() {
		this.HP--;
	}
	
	public int getHP() {
		return this.HP;
	}
	

}
