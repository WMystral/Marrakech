package views;

import javax.swing.JLabel;
import java.awt.Color;

/**
* This <code>Slider</code> class handles application sliders
*
* @version 0.1
*/
public class Slider extends javax.swing.JSlider
{
	/**
    * Constructor <code>Button</code>.
    * Creates horizontal slider with the specified minimum, maximum, and initial values.
    *
    * @param min the minimum value of the slider
    * @param max the maximum value of the slider
    * @param value the initial value of the slider
    */
	public Slider(int min, int max, int value)
	{
		super(javax.swing.JSlider.HORIZONTAL, min, max, value);
		this.setBackground(new Color(255, 255, 255, 0));
	}

	/**
    * Constructor <code>Button</code>.
    * Creates horizontal slider with the specified minimum, maximum, and initial values.
    *
    * @param min the minimum value of the slider
    * @param max the maximum value of the slider
    * @param value the initial value of the slider
    * @param extent the model's extent
    */
    public Slider(int min, int max, int value, int extent)
    {
    	super(new javax.swing.DefaultBoundedRangeModel(value, extent, min, max));
		this.setBackground(new Color(255, 255, 255, 0));
    }


	/**
	* Used to specify what label will be drawn at any given value.
	*/
	public void setDictionary()
	{
		this.setPaintLabels(true);
		int[] sets = {this.getMinimum(), this.getMaximum()};
		java.util.Hashtable<Integer, JLabel> labels = new java.util.Hashtable<Integer, JLabel>();
		for (int i = 0 ; i < 2 ; i++)
		{	
			JLabel hashItem = new JLabel(sets[i]+"");
			hashItem.setForeground(Color.WHITE);
			labels.put(sets[i], hashItem);
		}
		this.setLabelTable(labels);
	}

	/**
	* Defines the single line of text the <code>Label</code> will display.
	*
	* @param label the component to change
	*/
	public void setLabel(Label label)
	{
		this.addChangeListener(new javax.swing.event.ChangeListener()
		{
			@Override
			public void stateChanged(javax.swing.event.ChangeEvent e)
			{
				label.setValue(getValue());
				setDictionary();
			}
		});
	}
}