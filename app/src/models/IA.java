package models;

/**
* The class <code>IA</code> stands for the non-player characters
*/
public class IA extends Actor
{
	/**
	* Constructor <code>IA</code>.
	*
	* @param index the index of the actor
	* @param rugsNumber the nubmer of rugs of the actor
	* @param coinsAmount the amount of gold of the actor
	*/
	public IA(int index, int rugsNumber, int coinsAmount)
	{
		super(index, rugsNumber, coinsAmount);
	}

	/**
	* Returns <i>false</i> if this actor is an <code>IA</code>
	*/
	public boolean isPlayer()
	{
		return false;
	}
}