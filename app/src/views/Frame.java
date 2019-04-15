package views;

import controllers.Disposer;
import java.awt.Dimension;

/**
* Class <code>Frame</code> operates on ruling the frame's display. 
*
* @version 0.1
*/
public class Frame extends javax.swing.JFrame
{
	/**
	* Constructor <code>Frame</code>.
	* Constructs a new frame that is initially invisible.
	*/
	public Frame()
	{
		super();
		final int WIDTH = getToolkit().getScreenSize().width;
		final int HEIGHT = getToolkit().getScreenSize().height;
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
		this.setUndecorated(true);
		Disposer handler = new Disposer(this);
		this.initComponents();
		this.pack();
	}

	/**
	* Resizes this component so that it has width equals d.width and height equals d.height. 
	* 
	* @param d the dimension specifying the new size of this component
	*/
	@Override
	public void setSize(Dimension d)
	{
		super.setSize(d);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
	}

	/**
    * Initializes the graphic components within the frame.
    *
    * @see push
    */
    private void initComponents()
    {
    	this.push(new Menu(this));
    }

    /**
    * Add the component to the frame.
    *
    * @param p the component to add
    */
    public void push(javax.swing.JComponent p)
    {
        this.getContentPane().removeAll();
        this.add(p, java.awt.BorderLayout.CENTER);
        this.revalidate();
        this.getContentPane().repaint();
	}
}