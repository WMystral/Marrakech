package controllers;

import views.Button;
import views.Slider;
import views.Frame;
 
/**
* Class <code>ButtonController</code> defines when the action event occurs, that object's actionPerformed method is invoked.
*
* @version 0.1
*/
public class ButtonController implements java.awt.event.ActionListener
{
	/**
	* References the frame.
	*/
	private Frame frame;
	
	/**
	* The sliders that store data.
	*/
	private Slider[] data;

	/**
	* Constructor <code>ButtonController</code>.
	*
	* @param frame the frame
	* @param sliders the sliders group.
	*/
	public ButtonController(Frame frame, Slider[] sliders)
	{
		this.frame = frame;
		this.data = sliders;
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
		if (command.equals("Nouveau"))
		{
			int[] values = new int[3];
			for (int i = 0 ; i < 3 ; i++)
				values[i] = this.data[i].getValue();
			this.frame.push(new views.Game(new models.GameModel(values[0], values[1], values[2])));
		}
		else if (command.equals("Continuer"))
		{
			System.out.println(command);
		}
		else if (command.equals("Quitter"))
		{
			this.frame.dispose();
			System.exit(0);	
		}
	}
}