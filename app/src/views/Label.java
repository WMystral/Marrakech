package views;

import java.awt.Font;

/**
* This <code>Label</code> class handles application labels
*
* @version 0.1
*/
public class Label extends javax.swing.JLabel
{
	/**
	* Creates a new <code>Font</code> from the specified name, style and point size.
	*/
	public static final Font FONT = new Font("Dialog", Font.BOLD, 16);

	/**
	* The initial text string that the label always displays.
	*/
	private String text;

	/**
	* Creates a JLabel instance with the specified text.
	*
	* @param text the initial text to be displayed by the label
	* @param value the additional text to display
	*/
	public Label(String text, int value)
	{
		super(text+value);
		this.setFont(FONT);
		this.setForeground(java.awt.Color.WHITE);
		this.text = text;
	}

	/**
	* Defines the single line of text this component will display.
	*
	* @param value the additional text to display
	*/
	public void setValue(int value)
	{
		this.setText(this.text+value);
	}
}