import javax.swing.UIManager;

public class Main
{
    public static void main(String[] args)
    {
       	javax.swing.SwingUtilities.invokeLater(new Runnable()
       	{
            @Override
            public void run()
            {
            	try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
                javax.swing.JFrame frame = new views.Frame();
            	frame.setVisible(true);
            }
        });
    }
}