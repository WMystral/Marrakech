/**
* This Class <code>Frame</code> operates on ruling the frame's display. 
*
* @author lerouxdu
* @version 0.1
*/
public class Frame extends javax.swing.JFrame
{
    /**
    * Constructor <code>Frame</code>.
    */
    public Frame()
    {
        super();
        
        final int HEIGHT = 780;
        final int WIDTH = 1100;
        java.awt.Dimension dim = new java.awt.Dimension(WIDTH, HEIGHT);
        
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);

        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        this.initComponents();
        this.handler();

        this.pack();
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
    * Sets an adapter that binds ESCAPE to dispose the frame.
    */
    private void handler()
    {
        this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        this.getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction()
        {
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
                System.exit(0);
            }
        });
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