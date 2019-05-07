/**
* Pseudo-classe qui associe deux cellules.
*/
public class Pair
{
    /**
    * Le noeux central
    */
    private Cell node;

    /**
    * Son voisin
    */
    private Cell neighbour;

    /**
    * Constructeur de <code>Pair</code>
    * Fabrique un couple de cellules
    * @param main_item la cellule principale
    * @param sub_item son binôme
    */
    public Pair(Cell main_item, Cell sub_item)
    {
        this.node = main_item;
        this.neighbour = sub_item;
    }

    /**
    * getter pour le noeux.
    */
    public Cell node()
    {
    	return this.node;
    }

    /**
    * getter pour le voisin.
    */
    public Cell neighbour()
    {
    	return this.neighbour;
    }
}