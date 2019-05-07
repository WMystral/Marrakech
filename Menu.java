import javax.swing.JLabel;
import java.awt.Color;

/**
* This Class <code>Menu</code> deals with showing the menu. 
*
* @author lerouxdu
* @version 0.1
*/
public class Menu extends javax.swing.JPanel
{
	/**
    * References the frame.
    */
	private Frame frame;

	/**
    * Constructor <code>Menu</code>.
    *
    * @param f the frame which created <code>Menu</code>
    */
	public Menu(Frame f)
	{
		super();
		this.frame = f;
		
		int width = f.getWidth();
		int height = f.getHeight();
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		this.addComponents();
	}

	/**
	* Adds components to the pannel.
	*/
	private void addComponents()
	{
		String string = "Nombre de joueurs : ";
		JLabel label = new JLabel(string+3);
		java.awt.Font font = new java.awt.Font("Dialog", java.awt.Font.BOLD, 14);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds((int) (this.getWidth() * 0.45), (int) (this.getHeight() * 0.23), 200, 200);
		this.add(label, java.awt.BorderLayout.CENTER);

		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Menu ",  javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
			javax.swing.border.TitledBorder.DEFAULT_POSITION, font, Color.WHITE));

		java.util.Hashtable<Integer, JLabel> labels = new java.util.Hashtable<Integer, JLabel>();
		for (int i = 2 ; i < 5 ; i++)
		{
			JLabel hashitem = new JLabel(i+"");
			hashitem.setForeground(Color.WHITE);
			labels.put(i, hashitem);
		}

		javax.swing.JSlider nb_players = new javax.swing.JSlider(javax.swing.JSlider.HORIZONTAL, 2, 4, 3);
		nb_players.setLabelTable(labels);
		nb_players.addChangeListener(new javax.swing.event.ChangeListener()
		{
			@Override
			public void stateChanged(javax.swing.event.ChangeEvent e)
			{
				int value = nb_players.getValue();
				label.setText(string+value);
			}
		});
		nb_players.setPaintLabels(true);
		nb_players.setBackground(new Color(255, 255, 255, 0));
		nb_players.setBounds((int) (this.getWidth() * 0.45), (int) (this.getHeight() * 0.4), 170, 50);
		this.add(nb_players, java.awt.BorderLayout.CENTER);

		java.util.List<String> strings = new java.util.ArrayList<>();
		strings.add("Nouveau");
		java.io.File f = new java.io.File("./save.dat");
		if (f.exists() && !f.isDirectory())
			strings.add("Continuer");
		strings.add("Quitter");
		int y = (int) (this.getHeight() * 0.48);
		int offset = 58;
		for (int i = 0 ; i < strings.size() ; i++)
		{
			Button button = new Button(strings.get(i));
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String string = e.getActionCommand();
					if (string.equals("Nouveau"))
					{
						int players = nb_players.getValue();
						frame.push(new Game(frame, players));
					}
					else if (string.equals("Continuer"))
					{
						System.out.println(strings.get(1));
					}
					else if (string.equals("Quitter"))
					{
						frame.dispose();
						System.exit(0);	
					}
				}
			});
			button.setBounds((int) (this.getWidth() * 0.45), y+i*offset, 170, 48);
			this.add(button, java.awt.BorderLayout.CENTER);
		}
	}

	/**
	* Draws onto the component itself. 
	*
	* @param g a <code>Graphics</code> object
	*/
	@Override
	public void paintComponent(java.awt.Graphics g)
	{
		int width = (int) this.getSize().width;
		int height = (int) this.getSize().height;
		
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, width, height);
		try
		{
			java.io.File path = new java.io.File("./assets/background/menu_bg.jpg");
			java.awt.Image img = javax.imageio.ImageIO.read(path).getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT);
			g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
			g2.drawImage(img, 0, 0, this);
		}
		catch (java.io.IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
}