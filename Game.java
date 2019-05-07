import java.awt.BorderLayout;

/**
* This Class <code>Game</code> is a super-class within all game components and gameloop. 
*
* @author lerouxdu
* @version 0.1
*/
public class Game extends javax.swing.JPanel
{
	/**
    * Table of players.
    */
	private Player[] players;
	
	/**
    * Actual player.
    */
	private int player;
	
	/**
    * References the grid.
    */
	private Grid grid;

	/**
    * References the buttons.
    */
	private Pannel ihm;
	private int turn;
	private boolean looping;
	private javax.swing.JPanel p;

	private java.util.List<Game> previous = new java.util.ArrayList<>();
	private Game next = null;

	/**
    * Constructor <code>Menu</code>.
    *
    * @param f the frame
    * @param n the number of players
    */
	public Game(Frame f, int n)
	{
		super();
		this.populate(n);
		this.turn = 0;
		this.looping = true;
		this.setBounds(0, 0, f.getWidth(), f.getHeight());
		this.setLayout(null);
		this.gen(f);
		this.loop();
	}

	/**
	* Undo
	*/
	public Game step_backward()
	{
		int val = this.previous.size() - 1;
		if (val < 1)
			return this;
		return this.previous.get(val);
	}

	/**
	* test
	*/
	public void p()
	{
		for (Game g : this.previous)
		{
			for (Player p : g.players)
			{
				System.out.println(p);
			}
		}
	}

	/**
    * Fills the table of players.
    *
    * @param range the number of players
    */
	private void populate(int range)
	{
		Player[] tmp = new Player[range];
		int rugs = 24;
		if (range == 3)
			rugs = 15;
		else if (range == 4)
			rugs = 12;
		for (int i = 0 ; i < range ; i++)
			tmp[i] = new Player(i, rugs, 30);
		this.players = tmp;
		this.player = -1;
	}

	/**
	* Génére les composants.
	*
	* @param f la fenêtre
	*/
	private void gen(Frame f)
	{
		Grid g = new Grid(f, this);

		Pannel b = new Pannel(f, g);
		this.add(b);
		this.ihm = b;

		String[] strings = {"UNDO", "REDO"};
		for (int i = 0 ; i < strings.length ; i++)
		{
			Button btn = new Button(strings[i]);
			btn.addActionListener(new java.awt.event.ActionListener()
			{
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					String str = e.getActionCommand();
					if (str == "UNDO")
					{
						//f.push(step_backward());
						p();
					}
				}
			});
			btn.setBounds(825+i*120, 350, 100, 50);
			this.add(btn);
		}

		javax.swing.JPanel pan = new javax.swing.JPanel();
		pan.setBounds(825, 400, 200, this.getHeight()-350);
		pan.setBackground(new java.awt.Color(255, 255, 255, 0));
		this.p = pan;
		this.add(pan);

		this.add(g, BorderLayout.CENTER);
		this.grid = g;
	}

	/**
	* Actualise l'infobox
	*/
	private void setinfo()
	{
		int range = this.players.length;
		this.p.removeAll();
        this.p.revalidate();
        this.p.setLayout(new java.awt.GridLayout(range*6+3, 1));
		int num_turn = this.turn/this.players.length;
		int num_player = this.player + 1;
		String hex = "#"+Integer.toHexString(this.getPlayer().getColor().getRGB()).substring(2);
		String[] table = {"Tour : "+num_turn, "<html>Au joueur <font color='"+hex+"''>"+num_player+"</font> de jouer !</html>"};
		for (int i = 0 ; i < table.length ; i++)
		{
			javax.swing.JLabel label = new javax.swing.JLabel(table[i]);
			label.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 15));
			label.setForeground(java.awt.Color.WHITE);
			this.p.add(label);
		}
		this.p.add(new javax.swing.JLabel());
		for (Player p : this.players)
		{
			int num = p.getIndex()+1;
			int x = this.grid.count(p.getColor());
			String str = "Nombre de cases : "+x;
			if (x < 2)
				str = "Nombre de case : "+x;
			String[] strings = {"Joueur "+num, "Tapis restant : "+p.getRugs(), str, "Dirham : "+p.getCoins()};
			for (int i = 0 ; i < strings.length ; i++)
			{
				javax.swing.JLabel label = new javax.swing.JLabel(strings[i]);
				label.setForeground(java.awt.Color.WHITE);
				label.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 15));
				if (i == 0)
				{
					label.setForeground(new java.awt.Color(p.getColor().getRGB()));
					label.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 15));
				}
				this.p.add(label);
			}
			this.p.add(new javax.swing.JLabel());
		}	
		this.p.repaint();
	}

	/**
	* Game loop
	*/
	public void loop()
	{
		if (this.looping)
		{
			//this.previous.add(this);
			this.turn++;
			this.next();
			this.setinfo();
			this.grid.render(this.player);
			Boolean test = false;
			for (int i = 0 ; i < this.players.length ; i++)
				if (test = this.players[i].getRugs() > 0)
					break;
			if (!test)
				this.scoring();
			this.getPlayer().minusRugs();
			ihm.changeLayout();
		}
	}

	/**
	* Décompte final
	*/
	public void scoring()
	{
		this.looping = false;
		Player winner = this.players[0];
		int cmp = 0;
		for (Player p : this.players)
		{
			int tmp = this.grid.count(p.getColor()) + p.getCoins();
			if (tmp > cmp)
			{
				winner = p;
				cmp = tmp;
			}
		}
		int num_player = winner.getIndex() + 1;
		System.out.println("winner : joueur "+num_player);
	//depreciated si exeq
	}

	/**
	* Le prochain joueur
	*/
	private void next()
	{
		int tmp = this.player + 1;
		if (tmp > this.players.length - 1)
			tmp = 0;
		this.player = tmp;
	}

	/**
	* getter pour le tour
	*/
	public int getTurn()
	{
		return this.turn;
	}

	/**
	* Getter pour le joueur qui joue
	*/
	public Player getPlayer()
	{
		return this.players[this.player];
	}

	/**
	* Getter pour le joueur qui a cette couleur
	*
	* @param c la couleur du joueur qu'on cherche
	*/
	@Overload
	public Player getPlayer(java.awt.Color c)
	{
		Player res = this.players[0];
		for (Player p : this.players)
			if (c.equals(p.getColor()))
				res = p;
		return res;
	}
}