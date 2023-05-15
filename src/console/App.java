package console;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**

 The App class represents the main application class.

 It contains methods for launching the application, reading game data,

 generating the main menu, and handling menu choices.
 */
public class App {

	/**
	 * Launches the application by generating the main menu and displaying it.
	 */
	public void launch() {
		Menu menu = this.generateMainMenu();
		menu.displayAndWaitChoice();
	}

	/**
	 * Reads game data from the specified file path and returns a Game object.
	 *
	 * @param filePath the path to the game data file
	 * @return the Game object read from the file, or null if an error occurred
	 */
	public Game read(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			Gson gson = new Gson();
			String json = reader.readLine();
			return gson.fromJson(json, Game.class);
		} catch (IOException e) {
			System.err.println("Error reading game file: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Generates the main menu of the application.
	 *
	 * @return the generated main menu
	 */
	public Menu generateMainMenu() {
		Menu menu = new Menu("\n=-- Menu principal --=");
		menu.addItem(new MenuItem("Jouer", this::choice_place));
		menu.addItem(new MenuItem("Continuer", this::last_save));
		menu.addItem(new MenuItem("Langue", this::language));
		menu.addItem(new MenuItem("Credit", this::credit));
		menu.addItem(new MenuItem("Quitter\n", () -> System.out.println("ne dors pas trop longtemps...")));
		return menu;
	}

	/**
	 * Handles the choice of a place from the menu and displays relevant information.
	 */
	public void choice_place() {
		Menu menu = new Menu("\n=-- lieux --=");
		menu.addItem(new MenuItem("villa (facile)", () -> begin(1)));
		menu.addItem(new MenuItem("appartement (moyen)", () -> begin(2)));
		menu.addItem(new MenuItem("petit grenier (difficile)", () -> begin(3)));
		menu.addItem(new MenuItem("retour", this::launch));
		menu.displayAndWaitChoice();
	}

	/**
	 * Displays information about the selected place.
	 *
	 * @param nbr the number of the selected place
	 */
	private void begin(int nbr) {
		Game game = new Game(nbr);
		game.create(nbr);
	}

	/**
	 * Loads the last saved game and displays the daily menu.
	 */
	public void last_save() {
		Game game = read("./game_data.json");
		game.menuDaily();

	}

	/**
	 * Displays the credits.
	 */
	public void credit() {
		typing("\nCredit\n",200);
		System.out.println("Fauré Léo");
		System.out.println("Gregory Armirail");
		System.out.println("Lucas Izildy");
		typing("\nInformations\n",200);
		System.out.println("-c0de 322 est un jeu de simulation et de strategie");
		System.out.println("-Le nom 'c0de_322' fait référence à l'article 323-1 du Code pénal français qui réprime les activités de hacking,");
		System.out.println(" le hacking peut etre un outil bénéfique pour contrer la prise de contrôle de la planète par des robots...");
		typing("\n Version : 0.03a\n",200);

		launch();
	}

	/**
	 * Displays that the languages option coming soon.
	 */
	public void language() {
		System.out.println("Bientot Disponible !\n");
		this.launch();
	}


	/**
	 * Displays a text with a typing effect.
	 *
	 * @param txt  the text to display
	 * * @param time the time delay between characters in milliseconds
	 */
	public void typing(String txt, int time) {
		try {
			for (int i = 0; i < txt.length(); i++) {
				System.out.print(txt.charAt(i));
				Thread.sleep(time);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("La thread a été interrupter pendant la pause");
		}
	}
}

