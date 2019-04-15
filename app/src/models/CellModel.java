package models;

import java.awt.Color;

/**
* The class <code>CellModel</code> stores all the relativ data of a cell.
*/
public class CellModel
{
	/**
	* The value of this cell.
	*/
	private int value;
	
	/**
	* The position on the x-axis.
	*/
	private int x;
	
	/**
	* The position on the y-axis.
	*/
	private int y;
	
	/**
	* The background-color of this cell.
	*/
	private Color color;

	/**
	* The rotation to apply on Assam.
	*/
	private static int rotation;

	/**
	* Equals <i>true</i> if the cell contains Assam.
	*/
	private boolean isAssam;

	/**
    * Constructor <code>CellModel</code>.
    * Encapsulates all the data of a cell.
    *
    * @param value the value of the cell
    * @param i the index i in the grid
    * @param i the index j in the grid
    */
	public CellModel(int value, int i, int j)
	{
		this.value = value;
		this.x = i;
		this.y = j;
		this.color = new Color(255, 255, 255, 180);
		this.rotation = 270;
		this.isAssam = false;
	}

	/**
	* Getter to acces the value of the cell.
	*/
	public int getValue()
	{
		return this.value;
	}

	/**
	* Setter for the value of the cell.
	*
	* @param data the new value to set 
	*/
	public void setValue(int data)
    {
        this.value = data;
    }

    /**
    * Getter for the background-color of the cell.
    */
    public Color getColor()
    {
    	return this.color;
    }

    /**
    * Setter for the background-color of the cell.
    *
    * @param newColor the new Color to set
    */
    public void setColor(Color newColor)
    {
    	this.color = newColor;
    }

    /**
    * Getter for the attribut x.
    */
	public int getX()
	{
		return this.x;
	}

	/**
    * Getter for the attribut y.
    */
	public int getY()
	{
		return this.y;
	}

	/**
	* Returns <i>true</i> is the cell contains Assam.
	*/
	public boolean isAssam()
	{
		return this.isAssam;
	}

	/**
	* Getter for the rotation of Assam.
	*/
	public int getRotation()
	{
		return this.rotation;
	}

	/**
	* Changes the state of the cell.
	*/
	public void changeState()
	{
		this.isAssam = !this.isAssam;
	}
}