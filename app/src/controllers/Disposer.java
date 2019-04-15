package controllers;

/**
* Class <code>Disposer</code> sets an adapter that binds <i>ESCAPE</i> to dispose the frame.
*/
public class Disposer
{
    /**
    * Constructor <code>Disposer</code>.
    */
	public Disposer(views.Frame frame)
	{
		frame.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        frame.getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction()
        {
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                frame.dispose();
                System.exit(0);
            }
        });
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}
}