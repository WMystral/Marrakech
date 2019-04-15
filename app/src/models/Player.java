package models;

/**
* The class <code>Player</code> stands for the real player characters
*/
public class Player extends Actor
{
	/**
	* Constructor <code>Player</code>.
	*
	* @param index the index of the actor
	* @param rugsNumber the nubmer of rugs of the actor
	* @param coinsAmount the amount of gold of the actor
	*/
	public Player(int index, int rugsNumber, int coinsAmount)
	{
		super(index, rugsNumber, coinsAmount);
	}

	/**
	* Returns <i>true</i> if this actor is a <code>Player</code>
	*/
	public boolean isPlayer()
	{
		return true;
	}
}