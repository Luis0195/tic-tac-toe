
public class Board {
	private static String[][] board;
	private boolean isNotFull;
	
	public Board() {
		board = new String[][] {{"_","_","_"}, {"_","_","_"}, {"_","_","_"}};
		isNotFull = true;
	}
	
	public static void printBoard() {
		System.out.println("    A   B   C");
		for (int i = 0; i < board.length; i++) { 
			System.out.print((i + 1) + " |");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println(" ");
        }
	}
	
	public static void setToken(int x, int y, String value) {
		board[x][y] = value;
	}
	
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			 for (int j = 0; j < board[i].length; j++) {
	                if (board[i][j].equals("X") || board[i][j].equals("O")) {
	                	isNotFull = false;
	                }
	                else {
	                	isNotFull = true;
	                	break;
	                }
	            }
		}
		return isNotFull;
	}
	
	public boolean isNotTaken(int x, int y) {
		boolean taken = true;
		if (board[x][y].equals("_")) {
			taken = false;
		}
		return taken;
		
	}
	
	public String getToken(int x, int y) {
		return board[x][y];
	}
}
