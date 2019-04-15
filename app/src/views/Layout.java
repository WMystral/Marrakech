package views;

import java.awt.Dimension;

public class Layout extends javax.swing.JPanel
{
	/**
	* The background to draw.
	*/
	private java.awt.Image img = null;
	
	/**
    * Constructor <code>Layout</code>.
    */
	public Layout()
	{
		super();
		Dimension dimension = getToolkit().getScreenSize();
		try
		{
			this.img = javax.imageio.ImageIO.read(new java.io.File("./assets/background/menu_bg.jpg")).getScaledInstance((int) dimension.width, 
			(int) dimension.height, java.awt.Image.SCALE_DEFAULT);
		}
		catch (java.io.IOException e) {}
		this.setSize(dimension);
		this.setLayout(null);
	}

	/**
	* Calls the UI delegate's paint method, if the UI delegate is non-null.
	*
	* @param g the <code>Graphics</code> object to protect
	*/
	@Override
	public void paintComponent(java.awt.Graphics g)
	{
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		int width = (int) this.getSize().width;
		int height = (int) this.getSize().height;
		g2.setColor(java.awt.Color.BLACK);
		g2.fillRect(0, 0, width, height);
		g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
		g2.drawImage(this.img, 0, 0, this);
	}
}