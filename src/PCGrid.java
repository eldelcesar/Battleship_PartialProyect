import java.util.Random;

import javax.swing.JOptionPane;


public class PCGrid extends Grid{
	
	private boolean coordTrack;
	private int prevRow,
				prevColumn;
	
	public PCGrid() {
		super();
		this.coordTrack= false;
	}
	
	public void setShipsInPcGrid(){//PC situa sus 4 barcos
		JOptionPane.showMessageDialog(null, "Tu enemigo está ordenando sus barcos.");
		boolean flag = false;
		for (int i=0; i<4; i++){
			do {
				//Genera la coordenada
				
				Random randomInt = new Random();
				int row = (randomInt.nextInt(10-(5-i)));
				int column = (randomInt.nextInt(10-(5-i)));
				
				//Recibe y valida orientación
				Random randomBoolean = new Random();
				boolean orientation = (randomBoolean.nextBoolean());
				
				//Crea el barco #i con los inputs recibidos
				Ships ship = new Ships(row, column, orientation, 5-i, false);
				flag = this.setGameObject(ship);
				
			} while(!flag);
		}
		
		//Muestra el mapa para ver que barcos haz puesto
		System.out.println("PC Board");
		this.printGrid();
	}
	
	private void coordTracker(int prevRow, int prevColumn, Grid grid){
		do{
			this.prevRow= prevRow;
			this.prevColumn= prevColumn;
			Random randomInt = new Random();
			int randomAttack = (randomInt.nextInt(4));
			switch(randomAttack){
				case 0:
					this.prevRow+=1;
					break;
				case 1:
					this.prevColumn+=1;
					break;
				case 2:
					this.prevRow-=1;
					break;
				case 3:
					this.prevColumn-=1;
					break;
			}
		} while(!this.isValidCoord(this.prevRow, this.prevColumn, grid));
		
	}
	
	public void attackUser(Grid grid){//PC ataca al User
		JOptionPane.showMessageDialog(null, "Es el turno del enemigo! Estás listo?");
		
		//Genera la coordenada
		int[] coord=this.generateCoord(grid);
		int row= coord[0];
		int column = coord[1];
		
		if(this.coordTrack){
			this.coordTracker(prevRow, prevColumn, grid);
			row= this.prevRow;
			column= this.prevColumn;
		}
		
		JOptionPane.showMessageDialog(null, "El enemigo ha disparado en "+this.coordTranslator(row, column + 1)+"!");
				
		if (this.attackShip(row, column, grid)){
			JOptionPane.showMessageDialog(null, "Chale! Te han dañado!");
			this.coordTrack=true;
			this.prevRow=row;
			this.prevColumn=column;
		} else{
			JOptionPane.showMessageDialog(null, "Uff, estuvo cerca");
			this.coordTrack=false;
		}
		System.out.println("User Board");
		grid.printGrid(); 
	}
	
	public String coordTranslator(int row, int column){//Transcribe las coordenadas de la PC
		String rowStr = "";
		String columnStr = Integer.toString(column) ;
		
		switch(row){
			case 0:
				rowStr= "A";
				break;
			case 1:
				rowStr= "B";
				break;
			case 2:
				rowStr= "C";
				break;
			case 3:
				rowStr= "D";
				break;
			case 4:
				rowStr= "E";
				break;
			case 5:
				rowStr= "F";
				break;
			case 6:
				rowStr= "G";
				break;
			case 7:
				rowStr= "H";
				break;
			case 8:
				rowStr= "I";
				break;
			case 9:
				rowStr= "J";
				break;
		}
		
		return rowStr+columnStr;
	}
	
	public int[] generateCoord(Grid grid){
		Random randomInt = new Random();
		int row = (randomInt.nextInt(10));
		int column = (randomInt.nextInt(10));
		int[] coord= {row,column};
		
		if(!isValidCoord(row, column, grid)){			
			generateCoord(grid);
		} else{
			if(grid.getGameObject(grid.getGrid()[row][column]) ==  2){
				generateCoord(grid);
			}
		}
			
		return coord;
	}
}



