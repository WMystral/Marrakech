package views;

import models.GameModel;
import models.CellModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game extends Layout
{
	private Cell[][] cells;
	private InfoBox infoBox;
	/**
	*
	*
	* @param model
	*/
	public Game(GameModel model)
	{
		super();
		this.boardDraw(model);
		this.infoBoxDraw(model);
		this.actionDraw(model);
	}

	/**
	*
	*
	* @param model
	*/
	private void boardDraw(GameModel model)
	{
		final float ratio = 16.75f;
		final int CELL_SIZE = (int) (this.getWidth() / ratio);
		final int CELL_OFFSET = CELL_SIZE + 1;

		CellModel[][] tmp = model.getGrid();
		int sideLength = tmp.length;
		this.cells = new Cell[sideLength][sideLength];

		int xoff = (int) ((this.getWidth() - (sideLength * CELL_OFFSET)) / 8);
		int yoff = (int) ((this.getHeight() - (sideLength * CELL_OFFSET)) / 2);

		for (int row = 0 ; row < sideLength ; row++)
		{
			for (int col = 0 ; col < sideLength ; col++)
			{
				Cell elem = new Cell(tmp[row][col], CELL_SIZE);
				int x = col * CELL_OFFSET + xoff;
				int y = row * CELL_OFFSET + yoff;

                elem.setBounds(x, y, CELL_SIZE, CELL_SIZE);
                this.cells[row][col] = elem;
                this.add(elem);
            }
        }
        
        int boardSize = (sideLength * CELL_OFFSET) + CELL_SIZE + CELL_SIZE - 2;
        JLabel board = new JLabel(new ImageIcon(
        	new ImageIcon("./assets/background/game_bg.png").getImage().getScaledInstance(
        		boardSize, boardSize, java.awt.Image.SCALE_DEFAULT)));
        board.setBounds(xoff-CELL_SIZE, yoff-CELL_SIZE, boardSize, boardSize);
        this.add(board);
	}

	/**
	*
	*
	* @param model
	*/
	private void infoBoxDraw(GameModel model)
	{
		int x = (int) (this.getWidth() * 0.6);
		int y = (int) (this.getHeight() * 0.45);
		int width = 400;
		int height = (int) (this.getHeight() * 0.5);

		InfoBox pan = new InfoBox(model);
		pan.setBackground(java.awt.Color.RED);
		pan.setBounds(x, y, width, height);
		this.infoBox = pan;
		this.add(pan);
	}

	/**
	*
	*
	* @param model
	*/
	private void actionDraw(GameModel model)
	{
		int x = (int) (this.getWidth() * 0.6);
		int y = (int) (this.getHeight() * 0.1);
		int offset = 120;
		int width = 400;
		int height = 60;

		String[] directions = {"Devant", "Gauche", "Droite"};
		for (int i = 0 ; i < directions.length ; i++)
		{
			Button button = new Button(directions[i]);
			button.setBounds(x, y+i*offset, width, height);
			button.addActionListener(new controllers.DiceController(model));
			this.add(button); 
		}
	}

	private void redrawAll()
	{
		for (Cell[] a : this.cells)
			for (Cell elem : a)
				elem.setValue();
	}
}