import java.awt.Color;

/**
* The class <code>Player</code> represents or an <code>IA</code> or a real <code>Player</code>
*/
public class Player 
{
	/**
	* Color for the first player.
	*/
	private final static Color _a95934 = new Color(169, 89, 52, 180);

	/**
	* Color for the second player.
	*/
	private final static Color _898603 = new Color(137, 134, 3, 180);

	/**
	* The color for a third player.
	*/
	private final static Color _eB8e02 = new Color(235, 142, 2, 180);

	/**
	* Color for a fourth player
	*/
	private final static Color _005859 = new Color(0, 88, 89, 180);

	/**
	* The table of all possible colors.
	*/
	public static Color[] colors = {_a95934, _898603, _eB8e02, _005859};

	/**
	* The index of this player.
	*/
	private int index;

	/**
	* The number of rugs that belongs to this player.
	*/
	private int coins;

	/**
	* The number of coins that belongs to this player.
	*/
	private int rugs;

	/**
	* Constructor <code>Player</code>.
	* Creates an actor of the game.
	*
	* @param i the index of the actor
	* @param r the nubmer of rugs of the actor
	* @param c the amount of gold of the actor
	*/
	public Player(int i, int r, int c)
	{
		this.index = i;
		this.rugs = r;
		this.coins = c;
	}

	/**
	* Getter for the Color of this player.
	*/
	public Color getColor()
	{
		return this.colors[this.index];
	}

	/**
	* Getter for the number of rugs that player have.
	*/
	public int getRugs()
	{
		return this.rugs;
	}

	/**
	* Sets the number of rugs.
	*/
	public void minusRugs()
	{
		this.rugs--;
	}

	/**
	* getter pour l'indice
	*/
	public int getIndex()
	{
		return this.index;
	}
	
	/**
	* Getter for the amount of coins that remains.
	*/
	public int getCoins()
	{
		return this.coins;
	}

	/**
	* Setter to change the value <i>coins</i>.
	*
	* @param coins the value to add
	*/
	public void setCoins(int coins)
	{
		this.coins += coins; 
	}
	
	@Override
	public String toString()
	{
   		return "[color : "+this.getColor()+"] [rugs : "+this.rugs+"] [coins : "+this.coins+"]";
	}
}