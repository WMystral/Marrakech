package views;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.io.File;

/**
* Class <code>Menu</code> shows the menu. 
*
* @version 0.1
*/
public class Menu extends Layout
{
	/**
	* References the frame.
	*/
	private Frame frame;

	/**
    * Constructor <code>Menu</code>.
    *
    * @param f the frame which had created <code>Menu</code>
    */
	public Menu(Frame f)
	{
		super();
		this.frame = f;
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Menu ", 
			TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Label.FONT, Color.WHITE));
		this.addComponents();
	}

	/**
	* Adds components to the pannel.
	*/
	private void addComponents()
	{
		String[] strings = {"Nombre de joueurs : ", "Largeur de la grille : ", "Nombre d'I.A. : "};
		int[][] pressets = {{2, 4, 3}, {3, 7, 5}, {0, 2, 0}};
		Slider[] sliders = new Slider[strings.length];

		int x = (int) (this.getWidth() * 0.45);
		int y = (int) (this.getHeight() * 0.23);
		int offset = 120;
		int width = 400;
		int height = 60;
		
		int min, max, value;
		for (int i = 0 ; i < strings.length ; i++)
		{
			min = pressets[i][0];
			max = pressets[i][1];
			value = pressets[i][2];

			Label label = new Label(strings[i], value);
			label.setBounds(x, y+i*offset, width, height);
			this.add(label);

			sliders[i] = new Slider(min, max, value);
			if (i == 1)
				sliders[i] = new Slider(min, max, value, 0);
			sliders[i].setDictionary();
			sliders[i].setLabel(label);
			sliders[i].setBounds(x, y+i*offset+50, width, height);
			this.add(sliders[i]);
		}
		sliders[0].addChangeListener(new controllers.SliderController(sliders[2]));

		java.util.List<String> table = new java.util.ArrayList<>();
		table.add("Nouveau");
		File f = new File("./save/save.dat");
		if (f.exists() && !f.isDirectory())
			table.add("Continuer");
		table.add("Quitter");

		y = (int) (this.getHeight() * 0.60);
		offset = 58;
		height = 48;

		for (int i = 0 ; i < table.size() ; i++)
		{
			Button button = new Button(table.get(i));
			button.addActionListener(new controllers.ButtonController(this.frame, sliders));
			button.setBounds(x, y+i*offset, width, height);
			this.add(button);
		}
	}
}