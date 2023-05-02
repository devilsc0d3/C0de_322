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
		menu.addItem(new MenuItem("Credit", this::credit));
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
		System.out.println("save ?");
	}

	public void credit() {
		try {
			System.out.println("- Credit -");
			Thread.sleep(200);
			System.out.println("Fauré Léo");
			Thread.sleep(200);
			System.out.println("Gregory Armirail");
			Thread.sleep(200);
			System.out.println("Lucas Izildy");


		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("La thread a été interrompue pendant la pause");
		}
	}

	public void language() {
		System.out.println("Bientot Disponible !\n");
		this.launch();
	}

	public void test() {
		try {
			String phrase = "il était une fois, et fin VA FEN COULOT !";
			for (int i = 0; i < phrase.length(); i++) {
				System.out.print(phrase.charAt(i));
				Thread.sleep(20);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("La thread a été interrompue pendant la pause");
		}
	}
}

