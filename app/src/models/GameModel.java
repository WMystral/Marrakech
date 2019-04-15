package models;

/**
* Class <code>GameModel</code> defines a model in which will be store all game data.
*/
public class GameModel
{
	/**
    * Table of players.
    */
	private Actor[] players;

	/**
	* Table of <code>CellModel</code>.
	*/
	private CellModel[][] grid;

	/**
	* The turn of the current player.
	*/
	private int turn;

	/**
	* Constructor <code>GameModel</code>.
	* Creates a model in which will be store all game data.
	*
	* @param playersNumber the number of players
	* @param gridSize the length of the board
	* @param botsNumber the number of IA
	*/
	public GameModel(int playersNumber, int gridSize, int botsNumber)
	{
		this.playersPopulate(playersNumber, gridSize, botsNumber);
		this.grid = this.gridGeneration(gridSize);
		this.turn = 0;
	}

	/**
	* Getter for the grid.
	*/
	public CellModel[][] getGrid()
	{
		return this.grid;
	}
	
	/**
	* Creates players and populates the table of players.
	*
	* @param playersNumber the number of players
	* @param gridSize the length of the board
	* @param botsNumber the number of IA
	*/
	private void playersPopulate(int playersNumber, int gridSize, int botsNumber)
	{
		Actor[] tmp = new Actor[playersNumber];
		int rugs = gridSize*gridSize/playersNumber;
		int coins = rugs+rugs;
		for (int i = 0 ; i < playersNumber ; i++)
			tmp[i] = new Actor(i, rugs, coins);
		this.players = tmp;
	}

	/**
	* Generates and fills the grid of <code>CellModel</code>.
	*
	* @param gridSize the length of the board
	*/
	private CellModel[][] gridGeneration(int gridSize)
	{
		CellModel[][] tmp = new CellModel[gridSize][gridSize];
		for (int i = 0 ; i < gridSize ; i++)
		{
			for (int j = 0 ; j < gridSize ; j++)
			{
				tmp[i][j] = new CellModel(0, i, j);
				if (gridSize / 2 == i && i == j)
					tmp[i][j].changeState();
			}
		}
		return tmp;
	}

	/**
	* Getter for the table of players.
	*/
	public Actor[] getPlayers()
	{
		return this.players;
	}

	/**
	* Getter for the current turn.
	*/
	public int getTurn()
	{
		return this.turn;
	}
}