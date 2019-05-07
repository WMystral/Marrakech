/**
* Cette classe <code>Button</code> gère les boutons de l'application 
*
* @version 0.1
*/

public class Button extends javax.swing.JButton
{
	/**
    * Constructor <code>Button</code>.
    *
    * @param text the text to set in the button
    */
    public Button(String text)
	{
		super(text);
		this.setBorderPainted(false);
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}
}