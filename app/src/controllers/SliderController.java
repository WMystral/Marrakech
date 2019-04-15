package controllers;

import views.Slider;

/**
* Class <code>SliderController</code> defines an object which listens for ChangeEvents.
*
* @version 0.1
*/
public class SliderController implements javax.swing.event.ChangeListener
{
	/**
	* The slider target.
	*/
	private Slider target;

	/**
	* Constructor <code>SliderController</code>.
	*
	* @param slider the slider target
	*/
	public SliderController(Slider observer)
	{
		this.target = observer;
	}

	/**
	* Invoked when the target of the listener has changed its state.
	*
	* @param e a ChangeEvent object
	*/
	@Override
	public void stateChanged(javax.swing.event.ChangeEvent e)
	{
		int max = ((Slider) e.getSource()).getValue()-1;
		this.target.setMaximum(max);
		if (this.target.getValue() > max)
			this.target.setValue(max-1);
	}
}