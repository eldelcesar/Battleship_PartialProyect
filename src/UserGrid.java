import javax.swing.*;

public class UserGrid extends Grid{

	public UserGrid() {
		super();
	}
	
	
	private int rowReader(String coord){ //Interpreta los valores de row (A,B... J)
		int rowCoord = 0;
		if (coord.charAt(0) == 'a' || coord.charAt(0) =='A'){
			rowCoord= 0;
		}
		else if (coord.charAt(0) == 'b' || coord.charAt(0) =='B'){
			rowCoord=  1;
		}
		else if (coord.charAt(0) == 'c' || coord.charAt(0) =='C'){
			rowCoord=  2;
		}
		else if (coord.charAt(0) == 'd' || coord.charAt(0) =='D'){
			rowCoord=  3;
		}
		else if (coord.charAt(0) == 'e' || coord.charAt(0) =='E'){
			rowCoord=  4;
		}
		else if (coord.charAt(0) == 'f' || coord.charAt(0) =='F'){
			rowCoord=  5;
		}
		else if (coord.charAt(0) == 'g' || coord.charAt(0) =='G'){
			rowCoord=  6;
		}
		else if (coord.charAt(0) == 'h' || coord.charAt(0) =='H'){
			rowCoord=  7;
		}
		else if (coord.charAt(0) == 'i' || coord.charAt(0) =='I'){
			rowCoord=  8;
		}
		else if (coord.charAt(0) == 'j' || coord.charAt(0) =='J'){
			rowCoord=  9;
		}
		else{
			rowCoord= -1;
		}
		return rowCoord;
	}


	public int columnReader(String coord){//Interpreta los valores de column (1,2... 10)
		int column = (Character.getNumericValue(coord.charAt(1)));
		if(column != 1){
			return column;
		}
		
		else{
			if(coord.length() == 2){
				return column;
			}else if (Character.getNumericValue(coord.charAt(2)) == 0){
				return 10;
			}
		}
		return column;
	}

	
	public String shipsName(int length){
		String typoAvion = "";
		switch(length){
			case 5:
				typoAvion= "Portaviones\n(5 espacios):";
				 break;
			case 4:
				typoAvion= "Acorazado\n(4 espacios):";
				 break;
			case 3:
				typoAvion= "Submarino\n(3 espacios):";
				 break;
			case 2:
				typoAvion= "Buque patrullero\n(2 espacios):";
				 break;
		}
		return typoAvion;
	}
	
	

	public boolean isValidInput(String coord, Grid grid){//Valida la coordenada su longitud y que quepa
		//Valida la coordenada
		if (!(coord.length() == 2 || coord.length() == 3)){
			return false;
		}else{
			int row = this.rowReader(coord);
			int column = (this.columnReader(coord)-1);
			if(!(this.isValidCoord(row, column, grid))){
				return false;
			}
		}
		return true;
	}

	
	public boolean isValidOrientation(String orientationStr){//Valida la orientación
		boolean orientation = false;
		if(orientationStr.equals("h")){
			orientation = true;
		}
		else if (orientationStr.equals("v")){
			orientation = false;
		}
		else{
			JOptionPane.showMessageDialog(null, "Orientación inválida. Intenta de nuevo.");
			orientationStr =JOptionPane.showInputDialog(null, "Inserta la orientación!");
			this.isValidOrientation(orientationStr);
		}
		
		return orientation;
	}
	
	
	public void setShipsInUserGrid(){//Instancía los 4 barcos del usuario
		JOptionPane.showMessageDialog(null, "Es momento de ordenar tus barcos.");
		for (int i=0; i<4; i++){
			boolean flag = false;
			do {
				//Recibe y valida coordenada del barco
				String coord = JOptionPane.showInputDialog("Inserta la coordenada del "+this.shipsName(5-i));
				if (this.isValidInput(coord, this)) {
					int row = this.rowReader(coord);
					int column = (this.columnReader(coord)-1);
				
					//Recibe y valida orientación
					String orientationStr =  JOptionPane.showInputDialog("Inserta su orientación (h/v)");
					boolean orientation = this.isValidOrientation(orientationStr);
					
					//Crea el barco #i con los inputs recibidos
					Ships ship = new Ships(row, column, orientation, 5-i, true);
					flag = this.setGameObject(ship);
					
				} else {
					flag = false;
				}
				
				if (!flag) {
					JOptionPane.showMessageDialog(null, "Entrada inválida. Intenta de nuevo.");
				}
				
				//Muestra tu mapa para ver que barcos has puesto
				System.out.println("Your Board");
				this.printGrid();
			} while(!flag);
		}
			
	}

	
	public void attackPC(Grid grid){//Realizas un ataque al enemigo
		JOptionPane.showMessageDialog(null, "Es hora de atacar! Estás listo?");
		System.out.println("PC Board");
		grid.printGrid();
		boolean flag = false;
		//Recibe la coordenada
		do {
			String coord = JOptionPane.showInputDialog("Inserta la coordenada del ataque");
			if (this.isValidInput(coord, grid)) {
				int row = this.rowReader(coord);
				int column = (this.columnReader(coord)-1);
				
				if (this.attackShip(row, column, grid)){
						JOptionPane.showMessageDialog(null, "Nice shot! Le haz dado a uno de sus barcos");
				} else{
						JOptionPane.showMessageDialog(null, "Uyyy! Haz fallado! Suerte a la próxima");
				}
				System.out.println("PC Board");
				grid.printGrid();
				flag = true;
			}
			
		} while(!flag);
		
	}

}



