import java.awt.*;
import javax.swing.*;

/**
* This class <code>AfficheDe</code> rules the throwing of the dice
*
* @author suty
* @version 0.1
*/

public class AfficheDe extends JComponent {
	/**
	* Number of trial
	*/
	private static int TAILLE = 100;
	/**
	* La position de départ du d\u00E9 dans le tableau
	*/
	private int lance;
	/**
	* La position du d\u00E9 dans le tableau
	*/
	private int num;
	/**
	* Tableau du d\u00E9 pip\u00E9
	*/
	private int[] nums;
	/**
	* Diff\u00E9rente face du d\u00E9
	*/
	private Image img[];
	/**
	* R\u00E9f\u00E9rence vers la grille du jeu
	*/
	private Grid grid;
	/**
	* Stockage de la direction du joueur
	*/
	private String pressed = "";

	/**
	* Constructor <code>AfficheDe</code>
	* @param g la grille du jeu
	*/
	public AfficheDe(Grid g) {
		this.num = 0;
		this.grid = g;
		this.nums = new int[]{1,2,2,3,3,4};
		this.img = new Image[4];
		for(int i = 0 ; i < this.img.length ; i++)
			this.img[i] = Toolkit.getDefaultToolkit().getImage("./assets/dice/"+(i+1)+"_de_babouche.png");
	}

	/**
	* M\u00E9thode pour donner la direction du joueur
	* @param str Direction dans laquelle le joueur veut aller
	*/
	public void donnerBouton(String str)
	{
		this.pressed = str;
	}

	/**
	* Affichage des diff\u00E9rentes face du d\u00E9 en fonction de sa position du tableau
	*/
	@Override
	public void paintComponent(Graphics pinceau)
	{
		Graphics pin = pinceau.create();
		Image m = this.img[this.nums[this.num]-1];
		pin.drawImage(m, 0, 0, (int) m.getWidth(this)/2, (int) m.getHeight(this)/2, this);
	}

	/**
	* Lance la g\u00E9n\u00E9ration du nombre du d\u00E9
	*/
	public void generer()
	{
        this.lance = (int) (Math.random() * TAILLE);
        int _90 = TAILLE - (int)(TAILLE/10);
		
		for (int i=0;i<TAILLE;i++)
		{
	        try
	        {
	        	if (i < _90)
	        		Thread.sleep(10);
	        	else
	        		Thread.sleep((i - _90)*10);
	        }
	        catch(InterruptedException e)
	        {
	        	System.err.println(e.getMessage());
	        }
	        this.num = (this.lance + i)%6;
	        this.repaint();
	    }
	    this.grid.move(this.pressed, this.nums[this.num]);
    }
}

/**
* La classe <code>Affichage</code> est le thread pour la g\u00E9n\u00E9ration du nombre du d\u00E9
*
* @author suty
* @version 0.1
*/
class Affichage implements Runnable {
	/**
	* R\u00E9f\u00E9rence vers l'affichage du d\u00E9
	*/
	private AfficheDe affichage;

	/**
	* Constructeur <code>Affichage</code>
	*/
	public Affichage(AfficheDe ad)
	{
		this.affichage = ad;
	}

	/**
	* Action que le thread doit executer
	*/
	@Override
	public void run()
	{
		this.affichage.generer();
	}
}