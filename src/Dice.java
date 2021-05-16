import java.util.Random;

public class Dice {
	private Random dice;
	
	public Dice() {
		dice = new Random();
	}

	public int rollDice() {
		int diceNumber = dice.nextInt(6) + 1;
		return diceNumber;
	}


}
