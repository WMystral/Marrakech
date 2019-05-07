/**
* Cette classe <code>Cell</code> s'occupe de la gestion d'une cellule 
*
* @version 0.1
*/

import java.awt.Color;

public class Cell extends javax.swing.JLabel
{
    /**
    * Constante pour la couleur blanche par défaut de l'application.
    */
    private static final Color WHITE = new Color(255, 255, 255, 180);

    /**
    * Composante indiquant la valeur d'une cellule (tout où un tapis a été posé)
    * (-1 indique que la cellule est libre).
    */
    private static boolean bool;
    
    /**
    * La couleur de fond de la cellule.
    */
    private Color tmp;

    /**
    * La grille qui contient les cellules.
    */
    private Grid grid;
    
    /**
    * La valeur de la <code>Cell</code>.
    */
    private int value;

    /**
    * La position x de la <code>Cell</code>.
    */
    private int x;

    /**
    * La position y de la <code>Cell</code>.
    */
    private int y;

    /**
    * Un listener <code>MouseAdapter</code> pour sensibiliser cette <code>Cell</code> au clic.
    */
    private java.awt.event.MouseAdapter carpetListener = new java.awt.event.MouseAdapter()
    {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            if (!bool)
                grid.clean(x, y);
            else
                grid.clean2(x, y);
        }
    };

    /**
    * Constructeur <code>Cell</code>
    *
    * @param val la valeur d'une cellule
    */
    public Cell(Grid g, int val, int x, int y)
    {
        super();
        this.grid = g;
        this.value = val;
        this.bool = false;
        this.x = x;
        this.y = y;
        this.tmp = WHITE;
        this.setOpaque(true);
        this.setBackground(WHITE);
    }

    /**
    * Renvoie la valeur d'une cellule.
    *
    * @return la valeur d'une cellule (-1 ou plus)
    */
    public int getValue()
    {
        return this.value;
    }

    /**
    * Modifie la valeur d'une cellule.
    *
    * @param val la valeur de la cellule à modifier
    */
    public void setValue(int val)
    {
        this.value = val;
    }

    /**
    * Applique un effet de surbrillance sur la cellule.
    *
    * @param b change la varuable bool de cette cellule
    */
    public void setHighlight(boolean b)
    {
        this.bool = b;
        if (this.getMouseListeners().length == 0)
        {
            this.tmp = this.getBackground();
            this.addMouseListener(this.carpetListener);
            this.setBackground(new Color(this.tmp.getRGB()));
        }
    }

    /**
    * Retire l'effet de surbrillance de la cellule.
    */
    public void removeHighlight()
    {
        this.setBackground(this.tmp);
        this.grid.repaint(this.grid.getRectangle(this.x, this.y));
        this.removeMouseListener(this.carpetListener);
    }

    /**
    * Change la couleur de fond de la cellule.
    *
    * @param c la nouvelle couleur de fond
    */
    public void setColor(Color c)
    {
        this.tmp = new Color(c.getRed(), c.getGreen(), c.getBlue(), 180);
        this.setText(this.value+"");
    }

    @Override
    public String toString()
    {
        return "[x : "+this.x+"] [y : "+this.y+"] [value : "+this.value+"]";
    }
}