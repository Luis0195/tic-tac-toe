
public class Player {
	
	private String name;
	private String token;
	private boolean winner;

	public Player() {
		name = "";
		token = "";
		winner = false;
	}
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public void setName(String name) {
			this.name = name;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
		
	public String getName() {
		return name;
	}

	public String getToken() {
		return token;
	}

	
}
