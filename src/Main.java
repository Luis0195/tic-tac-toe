import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A program that simulates the tic-tac-toe game
 * 
 * @author luis0
 *
 */
public class Main {
	public static Scanner console;
	private static Player player1;
	private static Player player2;
	private static Dice dice;
	private static int p1Dice;
	private static int p2Dice;
	private static Board board;
	private static Logger log;
	
	
	public static void main(String[] args) {
		console = new Scanner(System.in);
		player1 = new Player();
		player2 = new Player();
		dice = new Dice();
		board = new Board();
		log = new Logger();
		System.out.print("Vamos a jugar un tres en raya" + "\n" + "Para empezar undique el nombre del jugador 1: ");
		namePlayer(player1);
		System.out.print("Ahora introduzca el nombre del jugador 2: ");
		namePlayer(player2);
		log.writeFile("Van a jugar " + player1.getName() + " y " + player2.getName());
		System.out.println("Ahora se decidirá quien empezará primero, para ello ambos jugadores tirarán un dado, aque que obtenga el numero más alto empezará primero");
		rollDices();
		board.printBoard();
		System.out.println("Para colocar una ficha en una casilla debe de escribir primero la fila y luego la columna ejemplo: 1 A. El numero y la letra parados por un espacio");
		while ((!player1.isWinner() && !player2.isWinner()) && !board.isFull()) {
			if (p1Dice > p2Dice) {
				try {
					setToken(player1);
				} 
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
	                    "Invalid input; re-enter slot number:");
					continue;
				}
				p1Dice = 0;
				p2Dice = 1;
				board.printBoard();
				line(player1);
				board.isFull();
				
			}
			else {
				try {
					setToken(player2	);
				} 
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
	                    "Invalid input; re-enter slot number:");
					continue;
				}
				p1Dice = 1;
				p2Dice = 0;
				board.printBoard();
				line(player2);
				board.isFull();
			}
			
		}
		if ((!player1.isWinner() && !player2.isWinner()) && board.isFull()) {
			System.out.println("Ha sido un empate");
			log.writeFile("No hubo ganadores");
		}
		System.out.println("El juego ha terminado");
		log.writeFile("El juego ha terminado");
		log.closeWirter();
	}
	/**
	 * Rename the player 
	 * @param player - the player selected
	 */
	private static void namePlayer(Player player) {
		player.setName(console.nextLine());
		
	}
	/**
	 * Roll the dices to decide who start first the game
	 */
	public static void rollDices() {
		p1Dice = dice.rollDice();
		p2Dice = dice.rollDice();
		System.out.println(player1.getName() + " tira el dado y saca un: " + p1Dice);
		System.out.println(player2.getName() + " tira el dado y saca un: " + p2Dice);
		while (p1Dice == p2Dice) {
			System.out.println("Es un empate, ambos jugadores volveran a tirar los dados");
			p1Dice = dice.rollDice();
			System.out.println(player1.getName() + " tira el dado y saca un: " + p1Dice);
			p2Dice = dice.rollDice();
			System.out.println(player2.getName() + " tira el dado y saca un: " + p2Dice);
		}
		log.writeFile(player1.getName() + " ha sacado un " + p1Dice);
		log.writeFile(player2.getName() + " ha sacado un " + p2Dice);
		if (p1Dice > p2Dice) {
			System.out.println(player1.getName() + " empezará primero por lo que se le asignarán las fichas 'X' y a " + player2.getName() + " las 'O'");
			player1.setToken("X");
			player2.setToken("O");
			p1Dice = 1;
			p2Dice = 0;
		}
		else {
			System.out.println(player2.getName() + " empezará primero por lo que se le asignarán las fichas 'X' y a " + player1.getName() + " las 'O'");
			player1.setToken("O");
			player2.setToken("X");
			p1Dice = 0;
			p2Dice = 1;
		}
		
	}
	
	/**
	 * Set the token at a given position
	 * Also checks if the position is already taken
	 * @param player
	 */
	public static void setToken(Player player) {
		String place = "";
		int x = 0;
		int y = 0;
		System.out.print(player.getName() + " donde quiere colocar la ficha? ");
		place = console.nextLine();
		// Check the length of the given String. If it's longer than 3 characters enters in the while loop
		while (place.length() > 3) {
			System.out.println("La cadena es introducida no puede tener mas de 3 caracteres.");
			System.out.println("Estos deben de ser un NUMERO un ESPACIO EN BLANCO y una LETRA");
			System.out.print("Introduzca la coordenanda de nuevo por favor: ");
			place = console.nextLine();
		}
		place = place.toUpperCase();
		String[] output = place.split(" ");	
		if (output[0].equals("1")) {
			x = 0;
		}
		
		else if (output[0].equals("2")) {
			x = 1;
		}
		
		else if (output[0].equals("3")) {
			x = 2;
		}
		
		else {
			x = 4;
		}
		
		if (output[1].equals("A")) {
			y = 0;
		}
		
		else if (output[1].equals("B")) {
			y = 1;
		}
		
		else if (output[1].equals("C")) {
			y = 2;
		}
		
		else {
			y = 4;
		}
		
		if (!board.isNotTaken(x, y)) {
			board.setToken(x, y, player.getToken());
			log.writeFile(player.getName() + " ha colocado la ficha en " + place);
		}
		else {
			System.out.println("Esta posicion ya esta ocupada, intentelo de nuevo.");
			setToken(player);
		}
		
	}
	
	/**
	 * Check if the player made a line
	 * @param player
	 */
	public static void line(Player player) {
		int index = 0;
		String line = "";
		while (index < 8) {
			switch (index) {
			case 0:
				line = board.getToken(0, 0) + board.getToken(0, 1) + board.getToken(0, 2);
				index++;
				break;
			
			case 1:
				line = board.getToken(1, 0) + board.getToken(1, 1) + board.getToken(1, 2);
				index++;
				break;
				
			case 2:
				line = board.getToken(2, 0) + board.getToken(2, 1) + board.getToken(2, 2);
				index++;
				break;
			
			case 3:
				line = board.getToken(0, 0) + board.getToken(1, 0) + board.getToken(2, 0);
				index++;
				break;
				
			case 4:
				line = board.getToken(0, 1) + board.getToken(1, 1) + board.getToken(2, 1);
				index++;
				break;
				
			case 5:
				line = board.getToken(0, 2) + board.getToken(1, 2) + board.getToken(2, 2);
				index++;
				break;
				
			case 6:
				line = board.getToken(0, 0) + board.getToken(1, 1) + board.getToken(2, 2);
				index++;
				break;
			
			case 7:
				line = board.getToken(0, 2) + board.getToken(1, 1) + board.getToken(2, 0);
				index++;
				break;
				
			default:
				index++;

			}
			
			if (line.equals("XXX")) {
				player.setWinner(true);
				index = 9;
				System.out.println("El jugador " + player.getName() + " ha ganado!");
				log.writeFile(player.getName() + " ganó la partida");
			}
			else if (line.equals("OOO")) {
				player.setWinner(true);
				index = 9;
				System.out.println("El jugador " + player.getName() + " ha ganado!");
				log.writeFile(player.getName() + " ganó la partida");
			}
		}	
	}
	
	
}
