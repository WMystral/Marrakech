package views;

import models.CellModel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.image.BufferedImage;

/**
*
*/
public class Cell extends javax.swing.JLabel
{
	/**
	*
	*/
	private CellModel model;
	
	/**
	*
	*/
	private ImageIcon icon;

	/**
	*
	*
	* @param model the data of the cell
	* @param cellSize the size of a cell
	*/
	public Cell(CellModel model, int cellSize)
	{
		super();
		this.icon = new ImageIcon(
			new ImageIcon("./assets/sprites/character.png").getImage().getScaledInstance(
				cellSize, cellSize, java.awt.Image.SCALE_DEFAULT));
		this.model = model;
		this.setOpaque(true);
		this.setValue();
	}

	/**
	*
	*/
	public void setValue()
	{
		CellModel tmp = this.model;
		this.setText(tmp.getValue()+"");
		this.setBackground(tmp.getColor());
		if (tmp.isAssam())
			this.setIcon(this.load());
	}

	/**
	*
	*/
	private Icon load()
	{
        Icon img = this.icon;
        if (this.model.getRotation() % 360 != 0)
        	img = this.rotate(this.icon);
        return img;
	}

	private ImageIcon rotate(ImageIcon icon)
	{
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        int type = BufferedImage.TYPE_INT_ARGB_PRE;
        BufferedImage img = new BufferedImage(height, width, type);
        
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) img.createGraphics();
        double midx = width / 2.0;
        double midy = height / 2.0;

        g2d.rotate((double) Math.toRadians((double) this.model.getRotation()), midx, midy);
        g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(img);
    }
}