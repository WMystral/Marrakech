package views;

import models.GameModel;

/**
*
*/
public class InfoBox extends javax.swing.JPanel
{
	/**
	*
	*
	* @param model the model of the game
	*/
	public InfoBox(GameModel model)
	{
		super();
		int range = model.getPlayers().length;
		this.setLayout(new java.awt.GridLayout(range*6+3, 1));
		this.initComponents(model);
	}

	/**
	*
	* @param model the model of the game
	*/
	private void initComponents(GameModel model)
	{
		
	}
}