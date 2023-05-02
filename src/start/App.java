package start;

public class App {

	public void launch() {
		Menu menu = this.generateMainMenu();
		menu.displayAndWaitChoice();
	}
	
	public Menu generateMainMenu() {
		Menu menu = new Menu("=-- Menu principal --=");
		menu.addItem(new MenuItem("Jouer", this::choice_part));
		menu.addItem(new MenuItem("Continuer", this::last_save));
		menu.addItem(new MenuItem("Langue", this::language));
		menu.addItem(new MenuItem("Quitter\n", () -> System.out.println("ne dors pas trop longtemps...")));
		return menu;
	}

	public void choice_part() {
		Menu menu = new Menu("\n=-- Jouer --=");
		menu.addItem(new MenuItem("partie rapide", () -> System.out.println("rapide")));
		menu.addItem(new MenuItem("partie avancer", () -> System.out.println("avancer")));
		menu.displayAndWaitChoice();
	}

	public void last_save() {
		try {
			System.out.println("hi?");
			Thread.sleep(500);

			System.out.println("yes");
			Thread.sleep(500);
			System.out.println("oh! hello");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("La thread a été interrompue pendant la pause");
		}
	}

	public void language() {
		System.out.println("Bientot Disponible !\n");
		this.launch();
	}
}

