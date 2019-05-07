public class Main
{
     public static void main(String[] args)
     {
       	javax.swing.SwingUtilities.invokeLater(new Runnable()
       	{
            @Override
            public void run()
            {
            	javax.swing.JFrame frame = new Frame();
            	frame.setVisible(true);
            }
        });
    }
}