package start;

public class App {

	public void launch() {
		Menu menu = this.generateMainMenu();
		menu.displayAndWaitChoice();
	}
	
	public Menu generateMainMenu() {
		Menu menu = new Menu("=-- Menu principal --=");
		menu.addItem(new MenuItem("Jouer", this::choice_place));
		menu.addItem(new MenuItem("Continuer", this::last_save));
		menu.addItem(new MenuItem("Langue", this::language));
		menu.addItem(new MenuItem("Credit", this::credit));
		menu.addItem(new MenuItem("Quitter\n", () -> System.out.println("ne dors pas trop longtemps...")));
		return menu;
	}

	public void choice_part() {
		Menu menu = new Menu("\n=-- Jouer --=");
		menu.addItem(new MenuItem("partie rapide", () -> System.out.println("rapide")));
		menu.addItem(new MenuItem("partie avancer", this::choice_place));
		menu.displayAndWaitChoice();
	}

	public void choice_place() {
		Menu menu = new Menu("\n=-- lieux --=");
		menu.addItem(new MenuItem("villa (facile)", () -> showt(3)));
		menu.addItem(new MenuItem("appartement (moyen)", () -> showt(2)));
		menu.addItem(new MenuItem("petit grenier (difficile)", () -> showt(1)));
		menu.displayAndWaitChoice();
	}

	private void showt(int nbr) {
		System.out.println(nbr);
		Game game = new Game();
		game.create(nbr);
	}


	public void last_save() {
		System.out.println("save ?");
	}

	public void credit() {
		typing("\nCredit\n",200);
		System.out.println("Fauré Léo");
		System.out.println("Gregory Armirail");
		System.out.println("Lucas Izildy");
		typing("\nFIN",200);
	}

	public void language() {
		System.out.println("Bientot Disponible !\n");
		this.launch();
	}

	public void typing(String txt, int time) {
		try {
			for (int i = 0; i < txt.length(); i++) {
				System.out.print(txt.charAt(i));
				Thread.sleep(time);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("La thread a été interrompue pendant la pause");
		}
	}
}

