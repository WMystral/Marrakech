package models;

import java.awt.Color;

/**
* The class <code>Actor</code> represents or an <code>IA</code> or a real <code>Player</code>
*/
public class Actor
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
	private int rugs;

	/**
	* The number of coins that belongs to this player.
	*/
	private int coins;
	
	/**
	* Equals <i>true</i> whenever the player is playing.
	*/
	private boolean isPlaying;

	/**
	* Constructor <code>Actor</code>.
	* Creates an actor of the game.
	*
	* @param index the index of the actor
	* @param rugsNumber the nubmer of rugs of the actor
	* @param coinsAmount the amount of gold of the actor
	*/
	public Actor(int index, int rugsNumber, int coinsAmount)
	{
		this.index = index;
		this.rugs = rugsNumber;
		this.coins = coinsAmount;
		this.isPlaying = false;
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

	/**
	* Returns <i>true</i> if the player if the one playing.
	*/
	public boolean isPlaying()
	{
		return this.isPlaying;
	}

	/**
	* Changes the state of the player.
	*/
	public void changeState()
	{
		this.isPlaying = !this.isPlaying;
	}

	/**
	* Sets the number of rugs.
	*/
	public void minusRugs()
	{
		this.rugs--;
	}

	/**
	* Returns a string representation of this component and its values.
	*/
	@Override
	public String toString()
	{
   		return "[index : "+this.index+"] [color : "+this.getColor()+
   			"] [rugs : "+this.rugs+"] [coins : "+this.coins+"]";
	}
}