package controllers;

import models.GameModel;
import static java.lang.Math.random;

/**
* Class <code>DiceController</code> listens to the direction buttons action.
*
* @version 0.1
*/
public class DiceController implements java.awt.event.ActionListener
{
	/**
	* The model that stores all data.
	*/
	private GameModel model;
	
	/**
	* Constructor <code>DiceController</code>.
	*
	* @param model the model in which all the data game are stored
	*/
	public DiceController(GameModel model)
	{
		this.model = model;
	}

	/**
	* Invoked when an action occurs.
	*
	* @param e a ActionEvent object
	*/
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		String command = e.getActionCommand();
		int[] dice =  new int[]{1,2,2,3,3,4};
		int rand = (int) (random() * 6);
		int result = dice[rand];
		new MoveController(command, result, model);
	}
}