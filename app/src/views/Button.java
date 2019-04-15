package views;

/**
* This <code>Button</code> class handles application buttons.
*
* @version 0.1
*/
public class Button extends javax.swing.JButton
{
	/**
    * Constructor <code>Button</code>.
    * Creates a button with text.
    *
    * @param text the text to set in the button
    */
	public Button(String txt)
	{
		super(txt);
		this.setBorderPainted(true);
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}
}