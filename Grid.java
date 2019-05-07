/**
 * Cette classe <code>Grid</code> gère la/les grilles de jeu de l'application
 *  
 * @version 0.1
 */

import java.awt.Color;
import static java.lang.Math.abs;

public class Grid extends javax.swing.JPanel 
{
	/**
    * Composante tableau de tableau comprenant des cellules.
    */
	private Cell[][] cells;

	/**
    * Composante tableau pour le nombre de joueurs (de base : 2)
    */
	private int[] player = new int[2]; //ligne/colonne
	
	/**
	* Un tableau de boolean pour compter la dime.
	*/
	private boolean[][] dime;
	
	/**
    * Composante pour la rotation du personnage 
    * 0, 90, 180 (vers le haut) ou 270
    */
	private int rotation;

	/**
	* Le tour
	*/
	private int k;

	/**
	* La classe parent.
	*/
	private Game game;

	/**
    * Constante pour le nombre de carrés sur une ligne
    */
	private final int SIDE_LENTGH = 7; //nombre de carrés sur ligne
	
	/**
    * Constante pour la taille des carrés
    */
	private final int CELL_SIZE = 80; //taille des carrés
	
	/**
    * Constante pour l'écart entre les cellules
    */
	private final int CELL_OFFSET = CELL_SIZE + 1; //écart entre les cellules

	/**
	* La position x avec l'offet horizontal pour l'affichage de la grille.
	*/
	private int xoff;

	/**
	* La position y avec l'offet vertical pour l'affichage de la grille.
	*/
	private int yoff;

	/**
	* Un couple de valuer x, y.
	*/
	private java.awt.Point point;

	/**
	* #define une valeur pour une case contenant un joueur.
	*/
	private final int PLAYER = -1;

	/*
	* #define une valeur pour une case vide.
	*/
	private final int EMPTY = 0;

	/**
	* La liste des voisins et de leurs voisins.
	*/
	private java.util.List<java.util.List<Pair>> super_list = new java.util.ArrayList<>();

	/**
	* Constructeur <code>Grid</code>
	*
	* @param f une fenêtre
	* @param g la grille
	*/
	public Grid(Frame f, Game g)
	{
		super();
		this.game = g;
		javax.swing.RepaintManager.currentManager(this).markCompletelyClean(this);
		this.init(f);
		this.gen();
	}

	/**
    * Initie les composantes graphiques de la grille
    *
    * @param f une fenêtre
    */
	private void init(Frame f)
	{
		this.setBounds(0, 0, f.getWidth(), f.getHeight());
		this.setLayout(null);
	}

	/**
    * Génère une grille de jeu
    */
	private void gen()
	{	
		int xoff = this.getWidth() - (SIDE_LENTGH * (CELL_SIZE + 3));
		xoff = (int) (xoff / 4.0);
		this.xoff = xoff;
		int yoff = this.getHeight() - (SIDE_LENTGH * (CELL_SIZE + 3));
		yoff = (int) (yoff / 2.0);
		this.yoff = yoff;
		
		Cell[][] tmp = new Cell[SIDE_LENTGH][SIDE_LENTGH];
		this.dime = new boolean[SIDE_LENTGH][SIDE_LENTGH];
		for (int i = 0 ; i < SIDE_LENTGH ; i++)
		{
			for (int j = 0 ; j < SIDE_LENTGH ; j++)
			{
				if (i == SIDE_LENTGH / 2 && j == i)
				{
					this.player[0] = 3;
					this.player[1] = 3;
				}
				Cell elem = new Cell(this, EMPTY, i, j);
				int x = j * CELL_OFFSET + xoff;
				int y = i * CELL_OFFSET + yoff;
                elem.setBounds(x, y, CELL_SIZE, CELL_SIZE);
                this.add(elem);
                tmp[i][j] = elem;
			}
		}
		this.cells = tmp;
		this.rotation = 180;
	}

	/**
    * Envoi la grille à l'instant t au terminal
    */
	private void gridShow()
	{
		int range = SIDE_LENTGH;
		for (int i = 0 ; i < range ; i++)
		{
			for (int j = 0 ; j < range ; j++)
			{
				Cell elem = this.cells[i][j];
				System.out.print(elem.getValue()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
    * Affiche à l'écran les éléments graphiques
    *
    * @param g un élément de type java.awt.Graphics
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
			java.awt.Image img = javax.imageio.ImageIO.read(path);
			g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
			g2.drawImage(img, 0, 0, width, height, this);
		}
		catch (java.io.IOException e)
		{
			System.err.println(e.getMessage());
		}
		try
		{
			java.io.File path = new java.io.File("./assets/background/game_bg.png");
			java.awt.Image img = javax.imageio.ImageIO.read(path);
			
			g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
			g2.drawImage(img, this.xoff-79, this.yoff-82, 724, 725, this);
		}
		catch (java.io.IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	/**
    * Change l'apparence et la position de Assam
    */
	public void render(int k)
	{
		this.k = k;
		int row = this.player[0];
		int col = this.player[1];
		this.cells[row][col].setIcon(this.load("P"+k));
	}

	/**
	* Charge une image et la rotatione
	*
	* @param file le nom du fichier
	*/
	private javax.swing.Icon load(String file) {
        javax.swing.Icon img = null;
        try
        {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon("./assets/sprites/"+file+".png");
            icon = new javax.swing.ImageIcon(icon.getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, java.awt.Image.SCALE_DEFAULT));
            img = icon;
            if (this.rotation % 360 != 0)
            	img = rotate(icon);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return img;
	}

	/**
	* Tourne une image
	*
	* @param icon l'image à tourner
	*/
	private javax.swing.ImageIcon rotate(javax.swing.ImageIcon icon)
	{
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        int type = java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(height, width, type);
        
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) img.createGraphics();
        double midx = width / 2.0;
        double midy = height / 2.0;

        g2d.rotate((double) Math.toRadians((double) this.rotation), midx, midy);
        g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
        g2d.dispose();

        return new javax.swing.ImageIcon(img);
    }

    /**
    * Applique le déplacement de Assam
    *
    * @param direction la direction
    * @param movement le résultat du dé
    */
	public void move(String direction, int movement)
	{
		int angle = 0;
		int pos=0;
		if (direction.equals("GAUCHE"))
			angle = -90;
		else if (direction.equals("DROITE"))
			angle = 90;
		if (this.rotation % 360 == 0 && direction.equals("GAUCHE"))
			pos = 270;
		else 
			pos = abs(this.rotation + angle);
		
		int row = this.player[0];
		int col = this.player[1];
		this.cells[row][col].setIcon(null);
		int s = SIDE_LENTGH*CELL_SIZE+(SIDE_LENTGH-1)* 3; 
		this.repaint(this.xoff, this.yoff, s, s);
		if (pos % 360 == 0) //en bas
		{
			if (row + movement > SIDE_LENTGH - 1)
			{
				this.player[0] = abs(SIDE_LENTGH - movement + SIDE_LENTGH - row - 1);
				pos = 180;
				if (col % 2 == 0)
				{
					this.player[1] = col - 1;
					if (col == 0)
					{
						this.player[0] = SIDE_LENTGH - 1;
						this.player[1] = abs(SIDE_LENTGH - movement - row);
						pos = 270;
					}
				}
				else
					this.player[1] = col + 1;
			}
			else
			{
				pos = 0;
				this.player[0] = row + movement;
			}
		}
		else if (pos % 360 == 90) //gauche
		{
			if (col - movement < 0)
			{
				this.player[1] = abs(movement - col - 1);
				pos = 270;
				if (row % 2 == 0)
				{
					this.player[0] = row + 1;
					if (row == SIDE_LENTGH - 1)
					{
						this.player[0] = abs(SIDE_LENTGH - movement + col);
						this.player[1] = 0;
						pos = 180;
					}
				}
				else
					this.player[0] = row - 1;
			}
			else
			{
				pos = 90;
				this.player[1] = col - movement;
			}
		}
		else if (pos % 360 == 180) //en haut
		{
			if (row - movement < 0)
			{
				pos = 0;
				this.player[0] = abs(movement - row - 1);
				if (col % 2 == 0)
				{
					this.player[1] = col + 1;
					if (col == SIDE_LENTGH - 1)
					{
						this.player[0] = 0;
						this.player[1] = abs(SIDE_LENTGH - movement + row);
						pos = 90;
					}
				}
				else
					this.player[1] = col - 1;
			}
			else
			{
				pos = 180;
				this.player[0] = row - movement;
			}
		}
		else if (pos % 360 == 270) //à droite
		{
			if (col + movement > SIDE_LENTGH - 1)
			{
				pos = 90;
				this.player[1] = SIDE_LENTGH - movement + SIDE_LENTGH - col - 1;
				if (row % 2 == 0)
				{
					this.player[0] = row - 1;
					if (row == 0)
					{
						this.player[0] = abs(SIDE_LENTGH - movement - col);
						this.player[1] = SIDE_LENTGH - 1;
						pos = 0;
					}
				}
				else
					this.player[0] = row + 1;
			}
			else
			{
				pos = 270;
				this.player[1] = col + movement;
			}
		}
		this.rotation = pos;
		this.render(this.k);
		this.super_list.clear();
		this.locateCarpet(this.player[0], this.player[1]);
	}

	/**
	* Place dans la super_list les "bons" voisins de la case i,j
	*
	* @param i position sur l'axe x
	* @param j position sur l'axe y
	*/
	private void locateCarpet(int i, int j)
	{
		for (int xoff = -1 ; xoff <= 1 ; xoff++)
		{
            for (int yoff = -1 ; yoff <= 1 ; yoff++)
            {
                int ioff = i + xoff;
                int joff = j + yoff;
                if (ioff > -1 && ioff < SIDE_LENTGH && joff > -1 && joff < SIDE_LENTGH
                	&& (ioff == i || joff == j) && !(ioff == i && joff == j))
                {
                	Cell elem = this.cells[ioff][joff];
                	java.util.List<Pair> sub_list = new java.util.ArrayList<>();
                	if (joff > 0 && (j != joff - 1 || i != ioff) && this.isPossible(elem, this.cells[ioff][joff-1]))
                	{
                		this.cells[ioff][joff].setHighlight(false);
                		sub_list.add(new Pair(elem, this.cells[ioff][joff-1]));
                	}
                	if (joff + 1 < SIDE_LENTGH && (j != joff + 1 || i != ioff) && this.isPossible(elem, this.cells[ioff][joff+1]))
                	{
                		this.cells[ioff][joff].setHighlight(false);
                		sub_list.add(new Pair(elem, this.cells[ioff][joff+1]));
                	}
                	if (ioff > 0 && (j != joff || i != ioff - 1) && this.isPossible(elem, this.cells[ioff-1][joff]))
					{
                		this.cells[ioff][joff].setHighlight(false);
                		sub_list.add(new Pair(elem, this.cells[ioff-1][joff]));
                	}               	
                	if (ioff + 1 < SIDE_LENTGH && (j != joff || i != ioff + 1) && this.isPossible(elem, this.cells[ioff+1][joff]))
                	{
                		this.cells[ioff][joff].setHighlight(false);
                		sub_list.add(new Pair(elem, this.cells[ioff+1][joff]));
                	}
                	this.super_list.add(sub_list);
				}
			}
		}
	}

	/**
	* On compare la cellule a et b pour vérifier la condition.
	*
	* @param a une cellule à comparer
	* @param b une autre cellule à comparer
	*/
	private boolean isPossible(Cell a, Cell b)
	{
		int val_a = a.getValue();
		int val_b = b.getValue();
		Color color_a = new Color(a.getBackground().getRGB());
		Color color_b = new Color(b.getBackground().getRGB());

		return (!color_a.equals(color_b) || val_a != val_b || val_b == EMPTY);
	}

	/**
	* Affiche la surbrillance.
	*
	* @param x position sur l'axe x
	* @param y position sur l'axe y
	*/
	public void clean(int x, int y)
	{	
		int posX = this.player[0];
		int posY = this.player[1];
		this.cl(x, y, posX, posY);
		this.point = new java.awt.Point(x, y);
        this.iterator(this.cells[x][y]);
	}

	/**
	* Affiche la surbrillance.
	*
	* @param elem la cellule qui a le focus
	*/
	public void iterator(Cell elem)
	{
		for (int i = 0 ; i < this.super_list.size() ; i++)
		{
			for (int j = 0 ; j < this.super_list.get(i).size() ; j++)
			{
				Pair sub_item = this.super_list.get(i).get(j);
				if (sub_item.node().equals(elem))
					sub_item.neighbour().setHighlight(true);
			}
		}
	}

	/**
	* permet de repaint seulement une partie.
	*
	* @param i position i dans la grille
	* @param j position j dans la grille
	*/
	public java.awt.Rectangle getRectangle(int i, int j)
	{
		return new java.awt.Rectangle(this.xoff + CELL_SIZE * j, this.yoff + CELL_SIZE * i, CELL_SIZE, CELL_SIZE);
	}

	public void clean2(int x, int y)
	{
		this.cl(x, y, (int) this.point.getX(), (int) this.point.getY());
		int i = this.player[0];
		int j = this.player[1];
		Cell elem = this.cells[i][j];
		if (elem.getValue() != EMPTY && !elem.getBackground().equals(Player.colors[this.k]))
		{
			this.parse(i, j, elem.getBackground());
			int k = getDim();
			int tmp = this.game.getPlayer().getCoins() - k;
			this.game.getPlayer().setCoins(-k);
			this.game.getPlayer(elem.getBackground()).setCoins(k);
			if (tmp < 0)
			{
				this.game.getPlayer(elem.getBackground()).setCoins(-tmp);
				this.game.scoring();
			}
		}
		this.game.loop();
	}

	/**
	* Retire la surbrillance de toutes les casses autout de la casse
	*
	* @param x position i dans la grille de la première cellule
	* @param y position j dans la grille de la première cellule
	* @param xx position i dans la grille de la deuxième cellume
	* @param yy position j dans la grille de la deuxième cellume
	*/
	public void cl(int x, int y, int xx, int yy)
	{
		for (int xoff = -1 ; xoff <= 1 ; xoff++)
		{
            for (int yoff = -1 ; yoff <= 1 ; yoff++)
            {
                int ioff = xx + xoff;
                int joff = yy + yoff;
                if (ioff > -1 && ioff < SIDE_LENTGH && joff > -1 && joff < SIDE_LENTGH 
                	&& (ioff == xx || joff == yy) && !(ioff == xx && joff == yy))
                {
					this.cells[ioff][joff].removeHighlight();
				}
			}
		}
		Cell tmp = this.cells[x][y];
		tmp.setValue(this.game.getTurn());
		Color c = Player.colors[this.k];
		Color color = new Color(c.getRed(), c.getGreen(), c.getBlue(), 180);
		tmp.setBackground(color);
		tmp.setColor(color);
	}

	/**
	* Compte la dime.
	* Cherhe une casse voisine dont la couleur correspond.
	*
	* @param posX la position X dans la grille
	* @param posY la position y dans la grille
	* @param color la couleur de la cellule
	*/
	private void parse(int posX, int posY, Color color)
	{
		for (int xoff = -1 ; xoff <= 1 ; xoff++)
		{
            for (int yoff = -1 ; yoff <= 1 ; yoff++)
            {
                int ioff = posX + xoff;
                int joff = posY + yoff;

                if (ioff > -1 && ioff < SIDE_LENTGH && joff > -1 && joff < SIDE_LENTGH 
                	&& (ioff == posX || joff == posY) && !(ioff == posX && joff == posY)
                		&& !this.dime[ioff][joff] && this.cells[ioff][joff].getBackground().equals(color))
                {
					this.dime[ioff][joff] = true;
					this.parse(ioff, joff, color);
				}
			}
		}
	}

	/**
	* Retourne le nombre à payer.
	*/
	private int getDim()
	{
		int res = 0;
		for (int i = 0 ; i < SIDE_LENTGH ; i++)
		{
			for (int j = 0 ; j < SIDE_LENTGH ; j++)
			{
				if (this.dime[i][j])
					res++;
                this.dime[i][j] = false;
			}
		}
		return res == 0 ? 1 : res;
	}

	/**
	* Compte le nombre de tapis posés que possède un joueur.
	*
	* @param color la couleur du joueur
	*/
	public int count(Color color)
	{
		int cmp = 0;
		for (Cell[] item : this.cells)
			for (Cell sub_item : item)
				if (sub_item.getBackground().equals(color))
					cmp++;
		return cmp;
	}
}