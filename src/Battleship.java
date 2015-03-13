import javax.swing.JOptionPane;


public class Battleship {
	/*Bienvenido a Battleship! Para visualizar los barcos del enemigo,
	 * simplemente vaya a ShipSectio n> línea 11. Disfrute el juego!*/
	public static int eventHandler(Grid user, Grid PC) {
		int result;
		int PCShips = 0;
		int userShips = 0;
		for (int i = 0; i < user.getAliveShips().length; i++) {
			if(user.getAliveShips()[i] instanceof Ships) {
				if (user.getAliveShips()[i].getHP() == 0) {
					user.getAliveShips()[i] = null;
					JOptionPane.showMessageDialog(null, "Que mal! Tu barco se ha hundido. Vamos!");
				}
			}
		}
		for (int i = 0; i < PC.getAliveShips().length; i++) {
			if(PC.getAliveShips()[i] instanceof Ships) {
				if (PC.getAliveShips()[i].getHP() == 0) {
					PC.getAliveShips()[i] = null;
					JOptionPane.showMessageDialog(null, "Eso, has hundido el barco");
				}
			}
		}
		
		for (int i = 0; i < user.getAliveShips().length; i++) {
			if (user.getAliveShips()[i] != null) {
				userShips++;
			}
		}
		
		for (int i = 0; i < PC.getAliveShips().length; i++) {
			if (PC.getAliveShips()[i] != null) {
				PCShips++;
			}
		}
		
		if(userShips == 0) {
			result = 2;
		} else if (PCShips == 0) {
			result = 1;
		} else {
			result = 3;
		}
		
		return result;
	}

	public static void main(String[] args) {
		UserGrid user = new UserGrid();
		PCGrid PC = new PCGrid();
		System.out.println("Let the battle begin!\n");
		JOptionPane.showMessageDialog(null, "Bienvenido a Battleship!");
		System.out.println("Your Board");
		user.printGrid();
		user.setShipsInUserGrid();
		PC.setShipsInPcGrid();
		int ronda=1;
		
		do {  //Game Loop
	
			//Empieza la batalla!
			System.out.println("Ronda "+ronda+"\n");
			
			user.attackPC(PC);
			if (eventHandler(user, PC) == 1){
				JOptionPane.showMessageDialog(null, "Felicidades! Ganaste la partida!");
				System.out.println("Resultado final\n");
				System.out.println("Your Board");
				user.printGrid();
				System.out.println("PC Board");
				PC.printGrid();
			}else{
				PC.attackUser(user);
				if (eventHandler(user, PC) == 2){
					JOptionPane.showMessageDialog(null, "Perdiste! Suerte a la próxima!");
					System.out.println("Resultado final\n");
					System.out.println("Your Board");
					user.printGrid();
					System.out.println("PC Board");
					PC.printGrid();
				}
			}
			
			ronda++;
			
		} while (eventHandler(user, PC) == 3);
		

	}

}